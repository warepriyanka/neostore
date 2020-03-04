package com.example.neostoreapplication.Repositories

import com.example.neostoreapplication.db.Entities.AppDatabase
import com.example.neostoreapplication.network.Response.BaseAPI
import com.example.neostoreapplication.network.Response.AuthResponse
import com.example.neostoreapplication.network.Response.SafeApiRequest
import com.example.neostoreapplication.db.Entities.User

class UserRepository (
    private val api: BaseAPI,
    private val db: AppDatabase
) : SafeApiRequest() {

    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest { api.userLogin(email, password) }
    }

    suspend fun userSignup(
        name: String,
        email: String,
        password: String
    ) : AuthResponse {
        return apiRequest{ api.userSignup(name, email, password)}
    }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getuser()

}