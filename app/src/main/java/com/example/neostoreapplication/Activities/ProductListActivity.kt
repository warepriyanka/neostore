package com.example.neostoreapplication.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neostoreapplication.Adapter.ProductListAdapter
import com.example.neostoreapplication.Model.Responses.ProductData
import com.example.neostoreapplication.Model.Responses.getProduct
import com.example.neostoreapplication.R
import com.example.neostoreapplication.ViewModel.ProductListViewModel
import com.example.neostoreapplication.utils.Validation
import kotlinx.android.synthetic.main.activity_product_list.*

class ProductListActivity : AppCompatActivity() {

    lateinit var  productListViewModel: ProductListViewModel
    var productListAdapter: ProductListAdapter?= null
    var productDetailList: ArrayList<ProductData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        val productName = intent.getStringExtra("product_name")
        val text=findViewById<TextView>(com.example.neostoreapplication.R.id.titletext)
        text.setText(productName)
        val imgback = findViewById<ImageView>(R.id.backbtn)
        imgback.setOnClickListener{
            finish()
        }

        val productListViewModelFactory =ProductListViewModel.Factory(intent.getStringExtra("product_id"),10,1,this.application)
        productListViewModel= ViewModelProviders.of(this,productListViewModelFactory).get(ProductListViewModel::class.java)
        fetchProductList()
        val recyclerViewLayOutManager = LinearLayoutManager(applicationContext)
        productRecyclerView.layoutManager=recyclerViewLayOutManager
    }

    fun fetchProductList()
    {
        if(Validation.isNetworkAvailable(this)) {
            productListViewModel.getProductListData().observe(this, object : Observer<getProduct> {
                override fun onChanged(t: getProduct?) {
                    productDetailList = t?.data as ArrayList<ProductData>
                    productListAdapter =
                        ProductListAdapter(this@ProductListActivity, productDetailList)
                    productRecyclerView.adapter = productListAdapter
                }
            })
        }else{
            Toast.makeText(this, "Please Check Internet connection", Toast.LENGTH_SHORT).show()
        }

    }
}
