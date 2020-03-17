package com.example.neostoreapplication.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.neostoreapplication.Model.Responses.ChangePasswordResponse
import com.example.neostoreapplication.Model.Responses.getLogin
import com.example.neostoreapplication.utils.NetworkController

class ResetPasswordViewModel :AndroidViewModel{

    private lateinit var resetPasswordResponse:  LiveData<ChangePasswordResponse>

    constructor(application: Application) : super(application)


    fun changePassword(accessToken: String, old_password: String, password: String, confirm_password:String) {
        resetPasswordResponse = NetworkController().getInstance().resetPassword(accessToken, old_password, password, confirm_password)
    }

    fun getChangePasswordResponse(): LiveData<ChangePasswordResponse> {
        return resetPasswordResponse!!
    }

    class Factory(val application: Application): ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return LoginViewModel(application) as T
        }
    }
}