package com.example.neostoreapplication.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.neostoreapplication.Model.Responses.registerUser
import com.example.neostoreapplication.utils.NetworkController

class SignUpViewModel:AndroidViewModel {
    private lateinit var signUpResponse:LiveData<registerUser>
    constructor(application: Application):super(application)

    fun signUpUser(firstName:String,lastName:String,email:String,password:String,cnfPassword:String,gender:String,mobile:String){
        signUpResponse= NetworkController().getInstance().signUp(firstName,lastName,email,password,cnfPassword,gender,mobile)

    }

    fun getSignUpResponse():LiveData<registerUser>
    {
        return signUpResponse
    }


    class Factory(val application: Application): ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SignUpViewModel(application) as T
        }
    }
}