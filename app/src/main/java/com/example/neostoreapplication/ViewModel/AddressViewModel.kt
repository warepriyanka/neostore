package com.example.neostoreapplication.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.neostoreapplication.AddressNote
import com.example.neostoreapplication.AddressRepository

class AddressViewModel(private var repository: AddressRepository) : ViewModel() {

    private var allNotes: LiveData<List<AddressNote>> = repository.getAllNotes()

    fun insert(note: AddressNote) {
        repository.insert(note)
    }

    fun delete(note: AddressNote?) {
        repository.delete(note)
    }

    fun deleteAllNotes() {
        repository.deleteAllNotes()
    }

    fun getAllNotes(): LiveData<List<AddressNote>> {
        return allNotes
    }

}