package com.example.neostoreapplication.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.neostoreapplication.Model.Responses.OrderDetailResponse
import com.example.neostoreapplication.Model.Responses.OrderListResponse
import com.example.neostoreapplication.utils.NetworkController

class OrderListViewModel: AndroidViewModel {

    private  lateinit var orderListResponse: LiveData<OrderListResponse>
    private lateinit var orderDetailResponse: LiveData<OrderDetailResponse>

    constructor(application: Application):super(application)

    fun getCartList(accessToken:String)
    {
        orderListResponse= NetworkController().getInstance().getOrderList(accessToken)
    }

    fun getOrderResponse(): LiveData<OrderListResponse>
    {
        return orderListResponse
    }

    fun getOrderDetail(accessToken: String, orderId: Int) {
        orderDetailResponse = NetworkController().getInstance().getOrderDetails(accessToken, orderId)
    }

    fun getOrderDetailResponse(): LiveData<OrderDetailResponse> {
        return orderDetailResponse!!
    }

    class Factory(val application: Application): ViewModelProvider.NewInstanceFactory() {


        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return OrderListViewModel(application) as T
        }
    }
}