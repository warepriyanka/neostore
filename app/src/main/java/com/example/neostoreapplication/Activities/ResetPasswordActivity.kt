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
import com.example.neostoreapplication.Model.Responses.ChangePasswordResponse
import com.example.neostoreapplication.Model.Responses.getLogin
import com.example.neostoreapplication.R
import com.example.neostoreapplication.ViewModel.LoginViewModel
import com.example.neostoreapplication.ViewModel.ResetPasswordViewModel
import com.example.neostoreapplication.utils.SessionManager

class ResetPasswordActivity : AppCompatActivity() {

lateinit var resetPasswordViewModel: ResetPasswordViewModel
    var current_password : EditText? = null
    var new_password : EditText? = null
    var confirm_password : EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        val factory= ResetPasswordViewModel.Factory(this.application)
        resetPasswordViewModel= ViewModelProviders.of(this,factory).get(ResetPasswordViewModel::class.java)
        val resetPassword =findViewById<Button>(R.id.btnResetPassword)
        resetPassword.setOnClickListener{
            Toast.makeText(this,"hi", Toast.LENGTH_LONG).show()
            intent = Intent(applicationContext,

                HomeActivity::class.java)
            startActivity(intent)
        }
        current_password=findViewById<EditText>(R.id.currentPass)
        new_password=findViewById<EditText>(R.id.newPass)
        confirm_password = findViewById<EditText>(R.id.confirmPassword)
        val clicked =findViewById<Button>(R.id.submitBtn)
        clicked.setOnClickListener{
            Toast.makeText(this,"Login button click", Toast.LENGTH_LONG).show()
            changePassword()
        }
    }

    fun changePassword()
    {

        resetPasswordViewModel.changePassword(SessionManager(this).getToken(),current_password?.text.toString(),
            new_password?.text.toString(),confirm_password?.text.toString())
        resetPasswordViewModel.getChangePasswordResponse().observe(this,object: Observer<ChangePasswordResponse> {
            override fun onChanged(t: ChangePasswordResponse?) {
                if (t?.status==200)
                {
                    Toast.makeText(applicationContext,"Password change successfully",Toast.LENGTH_LONG).show()
//                    startActivity(Intent(this@ProductDetailActivity, MyCartActivity::class.java))
                    finish()
                }
            }
        })

    }

}
