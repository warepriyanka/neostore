package com.example.neostoreapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.neostoreapplication.Activities.ui.ProductDetailActivity
import com.example.neostoreapplication.Model.Responses.ProductImage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_product_image.view.*

class ProductImageAdapter(innerContext: Context,innerProductImageList:ArrayList<ProductImage>):RecyclerView.Adapter<ProductImageAdapter.MyViewHolder>() {
    val context=innerContext
    val productImageList=innerProductImageList

    //DiffUtil

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(com.example.neostoreapplication.R.layout.item_product_image, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return productImageList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val productImageUrl =productImageList[position]
        Picasso.get().load(productImageUrl.image).into(holder.itemView.detailProductImageView)
        holder.itemView.setOnClickListener{
            (context as ProductDetailActivity).setImage(productImageUrl.image)
        }
    }

    class MyViewHolder(view: View):RecyclerView.ViewHolder(view)
}