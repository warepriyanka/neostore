package com.example.neostoreapplication.Model.Responses

data class OrderDetailResponse(
    val count: Int,
    val data: List<OrderDeailData>

)

data class OrderDeailData(
    val id: Int,
    val cost: Int,
    val created: String,
    val data: List<order_details>
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