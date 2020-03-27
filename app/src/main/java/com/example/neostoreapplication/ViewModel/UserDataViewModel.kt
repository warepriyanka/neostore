package com.example.neostoreapplication.ViewModel

import android.app.Application
import android.app.DatePickerDialog
import android.content.Context
import android.provider.ContactsContract
import androidx.lifecycle.*
import com.example.neostoreapplication.Model.Responses.UserData
import com.example.neostoreapplication.Model.Responses.getUserData
import com.example.neostoreapplication.utils.NetworkController
import java.util.*

class UserDataViewModel(application: Application): AndroidViewModel(application) {
    val context=application
    private lateinit var userDataResponse: LiveData<getUserData>
    private lateinit var saveUserDataResponse: LiveData<UserData>
    private var calender= Calendar.getInstance()
    val year = calender.get(Calendar.YEAR)
    val month = calender.get(Calendar.MONTH)
    val day = calender.get(Calendar.DAY_OF_MONTH)
    var dateSelected= MutableLiveData<String>()

    fun getUserData(accessToken:String)
    {
        userDataResponse=NetworkController().getInstance().getUserData(accessToken)
    }

    fun updateUserData(accessToken: String, email: String, dob: String, phone_number: String, profile_pic:String)
    {
        saveUserDataResponse = NetworkController().getInstance().saveUserData(accessToken,email,dob,phone_number, profile_pic)
    }

    fun getUserDataResponse(): LiveData<getUserData>
    {
        return userDataResponse
    }

    class Factory(val application: Application): ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return UserDataViewModel(application) as T
        }
    }

}