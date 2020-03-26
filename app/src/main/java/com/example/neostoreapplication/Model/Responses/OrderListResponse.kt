package com.example.neostoreapplication.Model.Responses

data class OrderListResponse(
    val count: Int,
    val data: List<OrderListData>

)

data class OrderListData(
    val id: Int,
    val cost: Int,
    val created: String
)