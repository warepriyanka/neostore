package com.example.neostoreapplication.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.neostoreapplication.Model.Responses.AddToCartResponse
import com.example.neostoreapplication.Model.Responses.ProductDetailResponse
import com.example.neostoreapplication.utils.NetworkController

class ProductDetailViewModel: AndroidViewModel {

    var productDetail: LiveData<ProductDetailResponse>? = null
    var addToCartResponse: LiveData<AddToCartResponse>? = null

    constructor(application: Application) : super(application)

    fun getProductDetail(productId: String) {
        productDetail = NetworkController().getInstance().getProductDetails(productId)
    }

    fun addtoCart(accessToken: String, productId: String, quantity: String) {
        addToCartResponse = NetworkController().getInstance().addToCart(accessToken, productId, quantity)
    }

    fun getProductDetailViewModel(): LiveData<ProductDetailResponse> {
        return productDetail!!
    }

    fun getAddToCartResponseVM(): LiveData<AddToCartResponse> {
        return addToCartResponse!!
    }


    class Factory(val application: Application) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ProductDetailViewModel(application) as T
        }
    }

}