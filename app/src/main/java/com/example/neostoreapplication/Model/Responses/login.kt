package com.example.neostoreapplication.Model.Responses

data class getLogin(
    val message: String,
    val status: Int,
    val user_msg: String,
    val data: LoginData

)

data class LoginData(
    val access_token: String,
    val country_id: Any?,
    val created: String,
    val dob: Any?,
    val email: String,
    val first_name: String,
    val gender: String,
    val id: Int,
    val is_active: Boolean,
    val last_name: String,
    val modified: String,
    val phone_no: String,
    val profile_pic: Any?,
    val role_id: Int,
    val username: String
)