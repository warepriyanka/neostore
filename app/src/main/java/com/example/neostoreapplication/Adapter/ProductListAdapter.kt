package com.example.neostoreapplication.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.neostoreapplication.Activities.ui.ProductDetailActivity
import com.example.neostoreapplication.Model.Responses.ProductData
import com.example.neostoreapplication.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_product_listing.view.*

class ProductListAdapter (innerContext: Context, innerProductDataList: ArrayList<ProductData>):
    RecyclerView.Adapter<ProductListAdapter.MyViewHolder>() {

    val context=innerContext
    val productDataList=innerProductDataList

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val productNameTextView=view.findViewById<TextView>(R.id.productNameTextView)
        val sellerNameTextView=view.findViewById<TextView>(R.id.productNameTextView)
        val productImageView=view.findViewById<ImageView>(R.id.producttImageView)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_product_listing, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return productDataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val productData=productDataList[position]
        val productImgUrl=productData.product_images
        Log.e("hi",productImgUrl)

        Picasso.get().load(productData.product_images).fit().into(holder.productImageView)
        holder.itemView.productNameTextView.text=productData.name
        val price= productData.cost
        holder.itemView.sellerNameTextView.text=productData.producer
        holder.itemView.priceTextView.text="Rs. $price"
        val size=productDataList.size
        Log.e("kk","Total Size: $size")
        holder.itemView.setOnClickListener{
            val intent= Intent(context, ProductDetailActivity::class.java)
            intent.putExtra("productId",productData.id)
            intent.putExtra("productName",productData.name)
            context.startActivity(intent)
        }
        //holder.itemView.priceTextView.text= productData.cost.toString()

    }



}