package com.example.neostoreapplication.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.neostoreapplication.R
import com.example.neostoreapplication.ViewModel.OrderDetailViewModel

class OrderDetailsActivity : AppCompatActivity() {

    var imageUrl:String?=null
    private lateinit var orderDetailViewModel: OrderDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)

        val orderId=intent.getIntExtra("oredrId",0)
        val text=findViewById<TextView>(com.example.neostoreapplication.R.id.titletext)
        val imgback = findViewById<ImageView>(R.id.backbtn)
        text.setText("Order ID : "+orderId.toString())
    }
}
