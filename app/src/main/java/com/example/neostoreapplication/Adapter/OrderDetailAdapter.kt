package com.example.neostoreapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.neostoreapplication.Model.Responses.OrderDeailData
import com.example.neostoreapplication.R

class OrderDetailAdapter (innerContext: Context, innerCartItemList: List<OrderDeailData>):
    RecyclerView.Adapter<OrderDetailAdapter.MyViewHolder>() {

    val context=innerContext
    val orderDetaillist=innerCartItemList
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_order_detail, parent, false)
        return MyViewHolder(v)
    }

    fun removeItem(position: Int) {
        orderDetaillist.drop(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, orderDetaillist.size)
    }

    override fun getItemCount(): Int {
        if (orderDetaillist!=null)
        {return orderDetaillist.size}
        else
        {return 0}
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val orderData=orderDetaillist[position]
//        val subTotalPrice = orderData.cost.toString()
//        val orderId = orderData.id.toString()
//        val orderDate = orderData.created.toString()
//        holder.itemView.oredrIdTextView.text=("Order ID : "+"$orderId")
//        holder.itemView.orderDateTextView.text=("Ordered Date : "+"$orderDate")
//        holder.itemView.costTextView.text=("₹ $subTotalPrice"+".OO")


    }

}
