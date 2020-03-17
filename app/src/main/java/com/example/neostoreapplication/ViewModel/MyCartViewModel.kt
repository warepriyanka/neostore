package com.example.neostoreapplication.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.neostoreapplication.Model.Responses.AddToCartResponse
import com.example.neostoreapplication.Model.Responses.GetCartItemResponse
import com.example.neostoreapplication.utils.NetworkController

class MyCartViewModel :AndroidViewModel{


    private  lateinit var getCartItemResponse: LiveData<GetCartItemResponse>
    private lateinit var editCartResponse: LiveData<AddToCartResponse>
    private lateinit var deleteCartResponse: LiveData<AddToCartResponse>

    constructor(application: Application):super(application)

    fun getCartList(accessToken:String)
    {
        getCartItemResponse=NetworkController().getInstance().getMyCartList(accessToken)
    }

    fun editQuantity(accessToken:String,productId: String,quantity:String)
    {
        editCartResponse=NetworkController().getInstance().editCart(accessToken,productId,quantity)
    }

    fun deleteProduct(accessToken: String, productId: String){
        deleteCartResponse = NetworkController().getInstance().deleteCart(accessToken,productId)
    }

    fun getMyCartResponse(): LiveData<GetCartItemResponse>
    {
        return getCartItemResponse
    }
    fun editQuantityResponse(): LiveData<AddToCartResponse>
    {
        return editCartResponse
    }

    fun deleteProductResponse(): LiveData<AddToCartResponse>
    {
        return deleteCartResponse
    }
    class Factory(val application: Application): ViewModelProvider.NewInstanceFactory() {


        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MyCartViewModel(application) as T
        }
    }


}
