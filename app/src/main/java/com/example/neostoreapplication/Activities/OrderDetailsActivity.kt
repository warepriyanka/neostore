package com.example.neostoreapplication.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neostoreapplication.Adapter.OrderDetailAdapter
import com.example.neostoreapplication.Adapter.ProductImageAdapter
import com.example.neostoreapplication.Model.Responses.*
import com.example.neostoreapplication.R
import com.example.neostoreapplication.ViewModel.OrderDetailViewModel
import com.example.neostoreapplication.ViewModel.OrderListViewModel
import com.example.neostoreapplication.utils.SessionManager
import kotlinx.android.synthetic.main.activity_my_cart.*
import kotlinx.android.synthetic.main.activity_my_cart.totalPriceTextView
import kotlinx.android.synthetic.main.activity_order_details.*
import kotlinx.android.synthetic.main.activity_order_list.*
import kotlinx.android.synthetic.main.activity_product_detail.*

class OrderDetailsActivity : AppCompatActivity() {

    var imageUrl:String?=null
    private lateinit var orderDetailViewModel: OrderListViewModel
    var orderDetailData: ArrayList<OrderDetailData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)

        val orderId=intent.getIntExtra("oredrId",0)
        val text=findViewById<TextView>(com.example.neostoreapplication.R.id.titletext)
        val imgback = findViewById<ImageView>(R.id.backbtn)
        text.setText("Order ID : "+orderId.toString())
        imgback.setOnClickListener{
            finish()
        }

        val orderDetailViewModelFactory= OrderListViewModel.Factory(this.application)
        orderDetailViewModel = ViewModelProviders.of(this,orderDetailViewModelFactory).get(OrderListViewModel::class.java)
        val recyclerViewLayOutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL,false)
        orderDetailRecyclerView.layoutManager=recyclerViewLayOutManager
        orderDetailRecyclerView.itemAnimator

        getOrderDetail(orderId)
    }

    fun getOrderDetail(oderId: Int)
    {
        orderDetailViewModel.getOrderDetail(SessionManager(this).getToken(),oderId)
        orderDetailViewModel.getOrderDetailResponse().observe(this,object: Observer<OrderDetailResponse>
        {
            override fun onChanged(t: OrderDetailResponse?) {
//                productNameTextView.text=t?.data?.name
                val totalPrice=t?.data?.cost.toString()
                totalPriceTextView.text="₹ $totalPrice"
                val orderDetailArray = t?.data?.orderDetail as ArrayList<order_details>
                val adapter= OrderDetailAdapter(this@OrderDetailsActivity,orderDetailArray)
                val recyclerViewLayOutManager = LinearLayoutManager(applicationContext,
                LinearLayoutManager.HORIZONTAL,false)
                orderDetailRecyclerView.adapter=adapter

            }

        })
    }


}
