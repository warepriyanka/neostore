package com.example.neostoreapplication.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.neostoreapplication.Activities.OrderDetailsActivity
import com.example.neostoreapplication.Model.Responses.OrderListData
import com.example.neostoreapplication.R
import kotlinx.android.synthetic.main.item_order_list.view.*

class OrderListAdapter (innerContext: Context, innerCartItemList: List<OrderListData>):
    RecyclerView.Adapter<OrderListAdapter.MyViewHolder>(){

    val context=innerContext
    val orderList=innerCartItemList
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_order_list, parent, false)
        return MyViewHolder(v)
    }

    fun removeItem(position: Int) {
        orderList.drop(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, orderList.size)
    }

    override fun getItemCount(): Int {
        if (orderList!=null)
        {return orderList.size}
        else
        {return 0}
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val orderData=orderList[position]
        val subTotalPrice = orderData.cost.toString()
        val orderId = orderData.id.toString()
        val orderDate = orderData.created.toString()
        holder.itemView.oredrIdTextView.text=("Order ID : "+"$orderId")
        holder.itemView.orderDateTextView.text=("Ordered Date : "+"$orderDate")
        holder.itemView.costTextView.text=("₹ $subTotalPrice"+".OO")
        holder.itemView.setOnClickListener {

            val intent = Intent(context, OrderDetailsActivity::class.java)
            intent.putExtra("oredrId",orderData.id)
            context?.startActivity(intent)

      }

    }

}
