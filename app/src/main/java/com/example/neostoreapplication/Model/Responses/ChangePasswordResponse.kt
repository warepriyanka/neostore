package com.example.neostoreapplication.Model.Responses

data class ChangePasswordResponse(
    val status: Int,
    val message: String,
    val user_msg: String
)
