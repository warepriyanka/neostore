package com.example.neostoreapplication.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.neostoreapplication.Model.Responses.ChangePasswordResponse
import com.example.neostoreapplication.utils.NetworkController

class PlaceOredrViewModel: AndroidViewModel{


private lateinit var placeOredrResponse: LiveData<ChangePasswordResponse>

constructor(application: Application) : super(application)

fun placeOrder(accessToken: String, address: String) {
    placeOredrResponse = NetworkController().getInstance().placeOrder(accessToken, address)
}

fun getResponse(): LiveData<ChangePasswordResponse> {
    return placeOredrResponse!!
}

class Factory(val application: Application): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlaceOredrViewModel(application) as T
    }
}

}