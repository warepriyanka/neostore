package com.example.neostoreapplication.ui.Auth

import com.example.neostoreapplication.db.Entities.User

interface AuthListener {

    fun onStarted()
    fun onSuccess(user: User)
    fun onFailure(message: String)
}