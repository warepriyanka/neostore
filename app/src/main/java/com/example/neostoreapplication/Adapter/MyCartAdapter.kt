package com.example.neostoreapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.neostoreapplication.Activities.MyCartActivity
import com.example.neostoreapplication.Model.Responses.CartItemListData
import com.example.neostoreapplication.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_cart_list.view.*

class MyCartAdapter(innerContext: Context, innerCartItemList: List<CartItemListData>):
    RecyclerView.Adapter<MyCartAdapter.MyViewHolder>() {

    val context=innerContext
    val cartItemList=innerCartItemList
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_cart_list, parent, false)
        return MyViewHolder(v)
    }

    fun removeItem(position: Int) {
        cartItemList.drop(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, cartItemList.size)
    }

    override fun getItemCount(): Int {
        if (cartItemList!=null)
        {return cartItemList.size}
        else
        {return 0}
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val productData=cartItemList[position]
        val productImgUrl=productData.product.product_images

        Picasso.get().load(productImgUrl).fit().into(holder.itemView.productImageMyCartImageView)
        holder.itemView.productNameMyCartTextView.text=productData.product.name
        val category=productData.product.product_category
        holder.itemView.productCategoryMyCartTextView.text="($category)"
        val subTotalPrice=productData.product.sub_total
        holder.itemView.productPriceMyCartTextView.text=("₹ $subTotalPrice")
        holder.itemView.quantityMyCartTextView.setOnClickListener {
            (context as MyCartActivity).editProductDialog(productData.product_id,productData.product.product_images,productData.product.name,productData.quantity.toString())
        }
        holder.itemView.quantityMyCartTextView.text= productData.quantity.toString()

    }

}
