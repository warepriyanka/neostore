package com.example.neostoreapplication

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.neostoreapplication.Activities.AddressDao

class AddressRepository (private val noteDao: AddressDao) {

    private val allNotes: LiveData<List<AddressNote>> = noteDao.getAllNotes()

    fun insert(note: AddressNote) {
        InsertNoteAsyncTask(
            noteDao
        ).execute(note)
    }

    fun deleteAllNotes() {
        DeleteAllNotesAsyncTask(
            noteDao
        ).execute()
    }

    fun delete(note: AddressNote?){
        DeleteNoteAsyncTask(noteDao)
            .execute(note)
    }

    fun getAllNotes(): LiveData<List<AddressNote>> {
        return allNotes
    }

    private class InsertNoteAsyncTask(val noteDao: AddressDao) : AsyncTask<AddressNote, Unit, Unit>() {

        override fun doInBackground(vararg note: AddressNote?) {
            noteDao.insert(note[0]!!)
        }
    }

    private class DeleteNoteAsyncTask(val noteDao: AddressDao) : AsyncTask<AddressNote, Unit, Unit>() {

        override fun doInBackground(vararg note: AddressNote?) {
            noteDao.delete(note[0])
        }
    }

//    private class DeleteNoteAsyncTask private constructor(noteDao: AddressDao) :
//        AsyncTask<AddressNote?, Void?, Void?>() {
//        private val noteDao: AddressDao
//        override fun doInBackground(vararg notes: AddressNote) {
//            noteDao.delete(notes[0])
//        }
//
//        init {
//            this.noteDao = noteDao
//        }
//    }

    private class DeleteAllNotesAsyncTask(val noteDao: AddressDao) : AsyncTask<Unit, Unit, Unit>() {

        override fun doInBackground(vararg p0: Unit?) {
            noteDao.deleteAllNotes()
        }
    }




}