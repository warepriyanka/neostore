package com.example.neostoreapplication.network.Response

import com.example.neostoreapplication.db.Entities.User

data class AuthResponse (
    val isSuccessful : Boolean?,
    val message: String?,
    val user: User?

)
