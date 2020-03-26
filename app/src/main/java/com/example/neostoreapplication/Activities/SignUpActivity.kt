package com.example.neostoreapplication.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.neostoreapplication.Model.Responses.registerUser
import com.example.neostoreapplication.R
import com.example.neostoreapplication.ViewModel.SignUpViewModel
import com.example.neostoreapplication.utils.SessionManager
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity: AppCompatActivity() {
    lateinit var signUpViewModel: SignUpViewModel
    var firstName: EditText? = null
    var lastName: EditText? = null
    var email: EditText? = null
    var password: EditText? = null
    var confirmPassword: EditText? = null
    var mobileNumber: EditText? = null


    lateinit var gender: String
    // var clicked : Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val signUPFactory = SignUpViewModel.Factory(this.application)
        signUpViewModel = ViewModelProviders.of(this, signUPFactory).get(SignUpViewModel::class.java)
        firstName = findViewById<EditText>(R.id.firstName)
        lastName = findViewById<EditText>(R.id.lastName)
        email = findViewById<EditText>(R.id.email)
        password = findViewById<EditText>(R.id.password)
        confirmPassword = findViewById<EditText>(R.id.confirmPassword)
        mobileNumber = findViewById<EditText>(R.id.mobileNumber)
        val clicked = findViewById<Button>(R.id.clicked)

        clicked.setOnClickListener {
            registerUser()
        }

    }

    fun registerUser() {

        if (maleRadioBtn.isChecked) {
            gender = "male"
            Toast.makeText(this, "male", Toast.LENGTH_LONG).show()
        } else {
            gender = "female"
        }
        signUpViewModel.signUpUser(
            firstName?.text.toString(), lastName?.text.toString(),
            email?.text.toString(), password?.text.toString(), confirmPassword?.text.toString(),
            gender, mobileNumber?.text.toString()
        )
        signUpViewModel.getSignUpResponse().observe(this, object : Observer<registerUser> {
            override fun onChanged(t: registerUser?) {
                if (t?.status == 200) {
                    val sessioManger = SessionManager(applicationContext)
                    sessioManger.createNewUserSession(
                        t.data.access_token,
                        t.data.first_name + t.data.last_name,
                        t.data.email,
                        t.data.phone_no
                    )
                    intent = Intent(
                        this@SignUpActivity,
                        HomeActivity::class.java
                    )
                    startActivity(intent)
                    finish()
                }
            }
        })

    }

}
