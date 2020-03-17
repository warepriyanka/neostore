package com.example.neostoreapplication.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.neostoreapplication.Model.Responses.getLogin
import com.example.neostoreapplication.R
import com.example.neostoreapplication.ViewModel.LoginViewModel
import com.example.neostoreapplication.utils.SessionManager

class LoginActivity: AppCompatActivity() {

    lateinit var loginViewModel: LoginViewModel

    var userName : EditText? = null
    var password : EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val factory=LoginViewModel.Factory(this.application)
        loginViewModel= ViewModelProviders.of(this,factory).get(LoginViewModel::class.java)
        val signUp =findViewById<ImageButton>(R.id.signUpButton)
        signUp.setOnClickListener{
            Toast.makeText(this,"hi", Toast.LENGTH_LONG).show()
            intent = Intent(applicationContext,

                HomeActivity::class.java)
            startActivity(intent)
        }
        userName=findViewById<EditText>(R.id.userNameEditTextView)
        password=findViewById<EditText>(R.id.passwordEditTextView)
        val clicked =findViewById<Button>(R.id.submitBtn)
        clicked.setOnClickListener{
            Toast.makeText(this,"Login button click",Toast.LENGTH_LONG).show()
            login()
        }
    }

    fun login()
    {

        loginViewModel.loginUser(userName?.text.toString(),password?.text.toString())
        loginViewModel.getUserLoginDetail().observe(this, object : Observer<getLogin> {
            override fun onChanged(t: getLogin?) {

                val sessioManger = SessionManager(applicationContext)
                sessioManger.createNewUserSession(
                    t!!.data.access_token,t.data.first_name+t.data.last_name,t.data.email,t.data.phone_no
                )

                Toast.makeText(this@LoginActivity, "Login Successful", Toast.LENGTH_LONG).show()
                intent = Intent(this@LoginActivity,
                    HomeActivity::class.java)
                startActivity(intent)
                finish()
            }

        })

    }
}