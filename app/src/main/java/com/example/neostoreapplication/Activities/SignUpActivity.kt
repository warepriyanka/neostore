package com.example.neostoreapplication.Activities

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.neostoreapplication.Preference.PreferenceHelper
import com.example.neostoreapplication.R
import com.example.neostoreapplication.ViewModel.SignUpViewModel
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONException
import java.io.IOException

class SignUpActivity : AppCompatActivity() {


    internal var RegisterURL = "https://demonuts.com/Demonuts/JsonTest/Tennis/simpleregister.php"
    private var etFirstName: EditText? = null
    private var etLastName: EditText? = null
    private var etEmail: EditText? = null
    private var etPassword: EditText? = null
    private var etConfPass: EditText? = null
    private var etMobNo: EditText? = null
    private var btnregister: Button? = null

    private var preferenceHelper: PreferenceHelper? = null
    private val RegTask = 1
    private var mProgressDialog: ProgressDialog? = null

    lateinit var gender: String
    lateinit var signUpViewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        preferenceHelper =
            PreferenceHelper(this)
        if (preferenceHelper!!.getIsLogin()) {
            val intent = Intent(this@SignUpActivity, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            this.finish()
        }


        etFirstName = findViewById<View>(R.id.etFirstName) as EditText
        etLastName = findViewById<View>(R.id.etLastName) as EditText
        etEmail = findViewById<View>(R.id.etEmail) as EditText
        etPassword = findViewById<View>(R.id.etPassword) as EditText
        etConfPass = findViewById<View>(R.id.etConfPassword) as EditText
        etMobNo = findViewById<View>(R.id.etMobNo) as EditText
        val radioGroup = findViewById<RadioGroup>(R.id.radioGrp)
        radioGroup?.setOnCheckedChangeListener { group, checkedId ->
            var text = " "
            text += if (R.id.radioMale == checkedId) "male" else "female"
            Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
        }


        btnregister = findViewById<View>(R.id.btnRegister) as Button

        btnregister!!.setOnClickListener {
            try {
                register()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
    }


        @Throws(IOException::class, JSONException::class)
        private fun register(){

            if(radioMale.isChecked){
                gender = "male"
            }else{
                gender = "female"
            }


        }


}
