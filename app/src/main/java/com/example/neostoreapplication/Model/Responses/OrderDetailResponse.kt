package com.example.neostoreapplication.Model.Responses

data class OrderDetailResponse(
    val status: Int,
    val data: OrderDetailData?

)

data class OrderDetailData(
    val id: Int,
    val cost: Int,
    val created: String,
    val orderDetail: List<order_details>
)

data class order_details(
    val id: Int,
    val order_id: Int,
    val product_id: Int,
    val quantity: Int,
    val total: Int,
    val prod_name: String,
    val prod_cat_name: String,
    val prod_image: String

)