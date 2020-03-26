package com.example.neostoreapplication.Activities.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.neostoreapplication.R
import com.example.neostoreapplication.ViewModel.AddressViewModel
import kotlinx.android.synthetic.main.activity_add_address.*

class AddAddressActivity : AppCompatActivity() {

    companion object {
        const val Address = "address"
        const val Landmark = "landmark"
        const val City = "city"
        const val State = "state"
        const val Country = "country"
        const val ZipCode = "zipCode"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_address)

        val backbtn = findViewById<ImageView>(R.id.backbtn)
        val saveAddress = findViewById<Button>(R.id.saveAddress)

        backbtn.setOnClickListener {
            finish()
        }

        saveAddress.setOnClickListener {
            saveNote()
        }


    }

    fun saveNote(){
        val address: String = etAddress.text.toString()
        val landmark: String = etLandmark.text.toString()
        val city: String = etCity.text.toString()
        val state: String = etState.text.toString()
        val country: String = etCountry.text.toString()
        val zipCode: String = etZipCode.text.toString()

        if (address.isBlank()) {
            Toast.makeText(this, "Can not insert empty adrress field!", Toast.LENGTH_SHORT).show()
            return
        }

        val data = Intent().apply {
            putExtra(Address, address)
            putExtra(Landmark, landmark)
            putExtra(City, city)
            putExtra(State, landmark)
            putExtra(Country, address)
            putExtra(ZipCode, zipCode)
        }

        setResult(Activity.RESULT_OK, data)
        finish()
    }


}
