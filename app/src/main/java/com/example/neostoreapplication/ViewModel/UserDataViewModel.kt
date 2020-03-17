package com.example.neostoreapplication.ViewModel

import android.app.Application
import android.app.DatePickerDialog
import android.content.Context
import androidx.lifecycle.*
import com.example.neostoreapplication.Model.Responses.getUserData
import com.example.neostoreapplication.utils.NetworkController
import java.util.*

class UserDataViewModel(application: Application): AndroidViewModel(application) {
    val context=application
    private lateinit var userDataResponse: LiveData<getUserData>
    private var calender= Calendar.getInstance()
    val year = calender.get(Calendar.YEAR)
    val month = calender.get(Calendar.MONTH)
    val day = calender.get(Calendar.DAY_OF_MONTH)
    var dateSelected= MutableLiveData<String>()
    fun getUserData(accessToken:String)
    {
        userDataResponse=NetworkController().getInstance().getUserData(accessToken)
    }

    fun getUserDataResponse(): LiveData<getUserData>
    {
        return userDataResponse
    }

    fun date(getContext: Context)
    {

        val datePickerDialog= DatePickerDialog(getContext,
            DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
                val month=month+1
                dateSelected?.postValue(" $dayOfMonth-$month-$year ")
            },year,month,day)
        datePickerDialog.show()
    }

    fun getDate(): MutableLiveData<String>
    {
        return dateSelected
    }


    class Factory(val application: Application): ViewModelProvider.NewInstanceFactory() {


        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return UserDataViewModel(application) as T
        }
    }


}