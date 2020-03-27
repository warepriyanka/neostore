package com.example.neostoreapplication.Activities

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neostoreapplication.Adapter.OrderListAdapter
import com.example.neostoreapplication.Model.Responses.OrderListData
import com.example.neostoreapplication.Model.Responses.OrderListResponse
import com.example.neostoreapplication.R
import com.example.neostoreapplication.ViewModel.OrderListViewModel
import com.example.neostoreapplication.utils.SessionManager
import com.example.neostoreapplication.utils.Validation
import kotlinx.android.synthetic.main.activity_order_list.*


class OrderListActivity : AppCompatActivity() {

    lateinit var orderListViewModel: OrderListViewModel
    var orderListAdapter: OrderListAdapter?=null
    var orderListData:ArrayList<OrderListData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_list)

        val imgback = findViewById<ImageView>(R.id.backbtn)

        imgback.setOnClickListener{
            finish()
        }

        val orderListViewModelFactory= OrderListViewModel.Factory(this.application)
        orderListViewModel= ViewModelProviders.of(this,orderListViewModelFactory).get(OrderListViewModel::class.java)
        getOrderList()
        val recyclerViewLayOutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL,false)
        orderRecyclerView.layoutManager=recyclerViewLayOutManager
        orderRecyclerView.itemAnimator
        orderRecyclerView.addItemDecoration(
            DividerItemDecoration(
                orderRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL
            )
        )

    }

    fun getOrderList()
    {
        if(Validation.isNetworkAvailable(this)) {

            orderListViewModel.getOrderList(SessionManager(this).getToken())
            orderListViewModel.getOrderResponse()
                .observe(this, object : Observer<OrderListResponse> {
                    override fun onChanged(t: OrderListResponse?) {
                        orderListData = t!!.data as ArrayList<OrderListData>

                        Log.e("hhh", orderListData?.size.toString())
//                val totalPrice=t?.total.toString()
//                totalPriceTextView.text="₹ $totalPrice"
                        orderListAdapter = OrderListAdapter(this@OrderListActivity, orderListData)
                        orderRecyclerView.adapter = orderListAdapter
                    }
                })
        }else{
            Toast.makeText(this, "Please Check Internet connection", Toast.LENGTH_SHORT).show()
        }

    }
}
