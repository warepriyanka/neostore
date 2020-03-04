package com.example.neostoreapplication.Preference

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper(private val context: Context) {

    private val INTRO = "intro"
    private val FirstName = "firstName"
    private val LastName = "lastName"
    private val Email = "Email"
    private val Password = "password"
    private val Gender = "gender"
    private val MobNo = "mobNo"

    private val app_prefs: SharedPreferences

    init {
        app_prefs = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
    }

    fun putIsLogin(loginorout: Boolean) {
        val edit = app_prefs.edit()
        edit.putBoolean(INTRO, loginorout)
        edit.commit()
    }

    fun getIsLogin(): Boolean {
        return app_prefs.getBoolean(INTRO, false)
    }

    fun putfirstName(loginorout: String) {
        val edit = app_prefs.edit()
        edit.putString(FirstName, loginorout)
        edit.commit()
    }

    fun getfirstNames(): String? {
        return app_prefs.getString(FirstName, "")
    }

    fun putLastName(loginorout: String) {
        val edit = app_prefs.edit()
        edit.putString(LastName, loginorout)
        edit.commit()
    }

    fun getLastName(): String? {
        return app_prefs.getString(LastName, "")
    }

    fun putEmail(loginorout: String) {
        val edit = app_prefs.edit()
        edit.putString(Email, loginorout)
        edit.commit()
    }

    fun getEmail(): String? {
        return app_prefs.getString(Email, "")
    }

    fun putPassword(loginorout: String) {
        val edit = app_prefs.edit()
        edit.putString(Password, loginorout)
        edit.commit()
    }

    fun getPassword(): String? {
        return app_prefs.getString(Password, "")
    }

    fun putMobNo(loginorout: String) {
        val edit = app_prefs.edit()
        edit.putString(MobNo, loginorout)
        edit.commit()
    }

    fun getMobNo(): String? {
        return app_prefs.getString(MobNo, "")
    }

    fun putGender(loginorout: String) {
        val edit = app_prefs.edit()
        edit.putString(Gender, loginorout)
        edit.commit()
    }

    fun getGender(): String? {
        return app_prefs.getString(Gender, "")
    }

}