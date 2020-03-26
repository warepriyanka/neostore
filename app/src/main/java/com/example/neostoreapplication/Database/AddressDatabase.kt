package com.example.neostoreapplication.Database

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.neostoreapplication.Activities.AddressDao
import com.example.neostoreapplication.AddressNote

@Database(entities = [AddressNote::class], version = 1)
abstract class AddressDatabase: RoomDatabase() {

    abstract fun noteDao(): AddressDao

    companion object {
        private var instance: AddressDatabase? = null
       fun getInstance(context: Context): AddressDatabase? {
        if (instance == null) {
            synchronized(AddressDatabase::class) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AddressDatabase::class.java, "address_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()
            }
        }
        return instance
    }

    fun destroyInstance() {
        instance = null
    }

    private val roomCallback = object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            PopulateDbAsyncTask(
                instance
            )
                .execute()
        }
    }

}
    class PopulateDbAsyncTask(db: AddressDatabase?) : AsyncTask<Unit, Unit, Unit>() {
        private val noteDao = db?.noteDao()

        override fun doInBackground(vararg p0: Unit?) {
            noteDao?.insert(
                AddressNote(
                    "B-305, Manisha Paradise, Behind Vitthal Krupa Appartment, Mharal",
                    "Kalyan",
                    "Kalyan",
                    "Maharashtra",
                    "India",
                    "421301"
                )
            )

        }

        }
}