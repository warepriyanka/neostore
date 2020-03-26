package com.example.neostoreapplication.Activities

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.neostoreapplication.AddressNote

@Dao
interface AddressDao {

    @Insert
    fun insert(note: AddressNote)

    @Delete
    fun delete(note: AddressNote?)

    @Query("DELETE FROM address_table")
    fun deleteAllNotes()

    @Query("SELECT * FROM address_table ")
    fun getAllNotes(): LiveData<List<AddressNote>>
}