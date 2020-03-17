package com.example.neostoreapplication.utils

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.example.neostoreapplication.Activities.HomeActivity
import com.example.neostoreapplication.Activities.LoginActivity

class SessionManager {

    var pref: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var context: Context? = null
    val PRIVATE_MODE = 0
    val PREF_NAME = "AndroidHivePref"
    val IS_LOGIN = "IsLoggedIn"
    val KEY_NAME = "firstName"
    val KEY_LAST_NAME = "lastName"
    val KEY_EMAIL = "email"
    val KEY_MOBILE = "mobile"
    val KEY_ACCESS_TOKEN = "accessToken"

    constructor(constructContext: Context) {
        context = constructContext
        pref = context!!.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = context!!.getSharedPreferences(PREF_NAME, PRIVATE_MODE).edit()
    }

    fun createNewUserSession(accessToken: String, name: String, email: String, mobile: String) {
        editor?.run {
            putBoolean(IS_LOGIN, true)
            putString(KEY_NAME, name)
            putString(KEY_EMAIL, email)
            putString(KEY_MOBILE, mobile)
            putString(KEY_ACCESS_TOKEN, accessToken)
            commit()
        }
    }


    fun checkLogin() {
        // Check login status
        if (!this.isLogin()) {
            // user is not logged in redirect him to Login Activity
            val i = Intent(context, LoginActivity::class.java)
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            // Add new Flag to start new Activity
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK

            // Staring Login Activity
            context?.startActivity(i)


        } else {
            val i = Intent(context, HomeActivity::class.java)
            context?.startActivity(i)


        }


    }

    fun logout() {
        editor?.clear()
        editor?.commit()
    }

    fun getToken(): String {
        return pref!!.getString(KEY_ACCESS_TOKEN, "")!!
    }

    fun isLogin(): Boolean = pref!!.getBoolean(IS_LOGIN, false)



}