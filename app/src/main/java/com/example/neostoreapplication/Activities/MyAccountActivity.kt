package com.example.neostoreapplication.Activities

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.neostoreapplication.Model.Responses.getUserData
import com.example.neostoreapplication.R
import com.example.neostoreapplication.ViewModel.UserDataViewModel
import com.example.neostoreapplication.utils.SessionManager
import kotlinx.android.synthetic.main.activity_my_account.*
import kotlinx.android.synthetic.main.activity_sign_up.email
import kotlinx.android.synthetic.main.activity_sign_up.firstName
import kotlinx.android.synthetic.main.activity_sign_up.lastName
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*


class MyAccountActivity : AppCompatActivity() {

    lateinit var fetchUserDataViewModel: UserDataViewModel
    lateinit var type:String
    var dobtext: TextView? = null
    var cal = Calendar.getInstance()
    val RESULT_LOAD_IMAGE = 1
    val imagepath: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_account)

        dobtext = this.dobText
        dobtext!!.text = "--/--/----"

        val imgback = findViewById<ImageView>(R.id.backbtn)
        imgback.setOnClickListener{
            finish()
        }

        btnResetPassword.setOnClickListener{
            val tableIntent = Intent(this, ResetPasswordActivity::class.java)
            this?.startActivity(tableIntent)
        }

        val myCartViewModelFactory=UserDataViewModel.Factory(this.application)
        fetchUserDataViewModel= ViewModelProviders.of(this,myCartViewModelFactory).get(UserDataViewModel::class.java)
        getUserData()
        disableEditText()
        userImage.setOnClickListener{
            getImageGallery()
        }

        // create an OnDateSetListener
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }

        btnEditProfile.setOnClickListener {

            firstName.isFocusableInTouchMode=true
            firstName.isFocusable=true
            firstName.isEnabled=true

            lastName.isFocusableInTouchMode=true
            lastName.isFocusable=true
            lastName.isEnabled=true

            email.isFocusableInTouchMode=true
            email.isFocusable=true
            email.isEnabled=true

            mobileEditText.isFocusableInTouchMode=true
            mobileEditText.isFocusable=true
            mobileEditText.isEnabled=true
            btnEditProfile.setText("Submit")
            titletext.setText("Edit Profile")
            btnResetPassword.visibility = View.GONE
            // when you click on the button, show DatePickerDialog that is set with OnDateSetListener
            dobtext!!.setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View) {
                    DatePickerDialog(this@MyAccountActivity,
                        dateSetListener,
                        // set DatePickerDialog to point to today's date when it loads up
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)).show()
                }

            })

            userImage.setOnClickListener {
                getImageFromAlbum();
            }

            updateUserData()
        }
    }

    private fun getImageFromAlbum() {
        try {
            val i = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(i, RESULT_LOAD_IMAGE)
        } catch (exp: Exception) {
            Log.i("Error", exp.toString())
        }
    }


    public override fun onActivityResult(requestCode:Int, resultCode:Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        /* if (resultCode == this.RESULT_CANCELED)
         {
         return
         }*/
        if (requestCode == RESULT_LOAD_IMAGE)
        {
            if (data != null)
            {
                val contentURI = data!!.data
                try
                {
                    val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)
//                    val path = saveImage(bitmap)
                    Toast.makeText(this@MyAccountActivity, "Image Saved!", Toast.LENGTH_SHORT).show()
                    userImage!!.setImageBitmap(bitmap)

                }
                catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(this@MyAccountActivity, "Failed!", Toast.LENGTH_SHORT).show()
                }

            }

        }

    }

    private fun updateDateInView() {
        val myFormat = "dd-mm-yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        dobtext!!.text = sdf.format(cal.getTime())
    }

    fun getImageGallery()
    {
        startActivityForResult(
            Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI),1234)
    }

    fun getUserData()
    {
        fetchUserDataViewModel.getUserData(SessionManager(this).getToken())
        fetchUserDataViewModel.getUserDataResponse().observe(this,object: Observer<getUserData> {
            override fun onChanged(t: getUserData?) {
                firstName.setText(t!!.data.user_data.first_name)
                lastName.setText(t.data.user_data.last_name)
                email.setText(t.data.user_data.email)
                mobileEditText.setText(t.data.user_data.phone_no)
                dobText.setText(t.data.user_data.dob)
            }
        })
    }

    fun updateUserData()
    {
        fetchUserDataViewModel.updateUserData(SessionManager(this).getToken(), email?.text.toString(),dobtext?.text.toString(),mobileEditText?.text.toString(),mobileEditText?.text.toString())
        fetchUserDataViewModel.getUserDataResponse().observe(this,object: Observer<getUserData> {
            override fun onChanged(t: getUserData?) {
                firstName.setText(t!!.data.user_data.first_name)
                lastName.setText(t.data.user_data.last_name)
                email.setText(t.data.user_data.email)
                mobileEditText.setText(t.data.user_data.phone_no)
                dobText.setText(t.data.user_data.dob)
            }
        })
    }


    fun disableEditText()
    {
        firstName.isFocusableInTouchMode=false
        firstName.isFocusable=false
        firstName.isEnabled=false

        lastName.isFocusableInTouchMode=false
        lastName.isFocusable=false
        lastName.isEnabled=false

        email.isFocusableInTouchMode=false
        email.isFocusable=false
        email.isEnabled=false

        mobileEditText.isFocusableInTouchMode=false
        mobileEditText.isFocusable=false
        mobileEditText.isEnabled=false

    }

}
