package com.example.neostoreapplication.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.neostoreapplication.Model.Responses.OrderDetailResponse
import com.example.neostoreapplication.utils.NetworkController

class OrderDetailViewModel: AndroidViewModel {

    private lateinit var orderDetailResponse: LiveData<OrderDetailResponse>

    constructor(application: Application):super(application)

    fun getOrderDetail(accessToken: String, orderId: Int) {
        orderDetailResponse = NetworkController().getInstance().getOrderDetails(accessToken, orderId)
    }

    fun getOrderDetailResponse(): LiveData<OrderDetailResponse> {
        return orderDetailResponse!!
    }
}