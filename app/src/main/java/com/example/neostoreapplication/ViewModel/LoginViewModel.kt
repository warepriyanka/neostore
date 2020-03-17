package com.example.neostoreapplication.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.neostoreapplication.Model.Responses.getLogin
import com.example.neostoreapplication.utils.NetworkController

class LoginViewModel: AndroidViewModel {

    private lateinit var loginResponse: LiveData<getLogin>

    constructor(application: Application) : super(application)

    fun loginUser(email:String,password:String)
    {
        loginResponse=NetworkController().getInstance().login(email,password)
    }

    fun getUserLoginDetail(): LiveData<getLogin>
    {
        return loginResponse
    }

    class Factory(val application: Application): ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return LoginViewModel(application) as T
        }
    }
}