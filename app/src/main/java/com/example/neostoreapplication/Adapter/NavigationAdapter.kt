package com.example.neostoreapplication.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.neostoreapplication.Activities.MyAccountActivity
import com.example.neostoreapplication.Activities.MyCartActivity
import com.example.neostoreapplication.Activities.OrderListActivity
import com.example.neostoreapplication.Activities.ProductListActivity
import com.example.neostoreapplication.Model.Responses.NavigationModel
import com.example.neostoreapplication.R

class NavigationAdapter (innerContext: Context, innerNavModelList: ArrayList<NavigationModel>) : RecyclerView.Adapter<NavigationAdapter.MyViewHolder>() {

    var context = innerContext
    // var navModelList:List<NavModel>?=null
    val navListData = innerNavModelList
    //    constructor(innerContext: Context,innerNavModelList: ArrayList<NavModel>)
//    {
//        context= innerContext
//        navModelList=innerNavModelList
//    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_nav_drawer, parent, false)
        return MyViewHolder(v)
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val naveTextView = view.findViewById<TextView>(R.id.naveTextView)
        val navCountTextView = view.findViewById<TextView>(R.id.navCountTextView)
        val navImagee = view.findViewById<ImageView>(R.id.navImageView)
    }


    override fun getItemCount(): Int {
        return navListData.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val  navData = navListData[position]
        holder.naveTextView.text=navData.navText
        if (position==0)
        {
            holder.navCountTextView.text=navData.count
        }
        else
        {
            holder.navCountTextView.visibility = View.INVISIBLE
        }

        holder.navImagee.setImageResource(navData.image)
        holder.itemView.setOnClickListener{

            when(position)
            {
                0 ->{ val tableIntent = Intent(context, MyCartActivity::class.java)
                    context?.startActivity(tableIntent)
                }
                1 ->{ val tableIntent = Intent(context, ProductListActivity::class.java)
                    tableIntent.putExtra("product_id","1")
                    tableIntent.putExtra("product_name", "Tables")
                    context?.startActivity(tableIntent)
                }
                2 ->{ val tableIntent = Intent(context, ProductListActivity::class.java)
                    tableIntent.putExtra("product_id","3")
                    tableIntent.putExtra("product_name", "Sofas")
                    context?.startActivity(tableIntent)
                }
                3 ->{ val tableIntent = Intent(context, ProductListActivity::class.java)
                    tableIntent.putExtra("product_id","2")
                    tableIntent.putExtra("product_name", "Chairs")
                    context?.startActivity(tableIntent)
                }
                4 ->{ val tableIntent = Intent(context, ProductListActivity::class.java)
                    tableIntent.putExtra("product_id","4")
                    tableIntent.putExtra("product_name", "Cupboards")
                    context?.startActivity(tableIntent)
                }
                5 ->{ val tableIntent = Intent(context, MyAccountActivity::class.java)

                    context?.startActivity(tableIntent)
                }
                7 ->{val tableIntent = Intent(context,OrderListActivity::class.java)

                    context?.startActivity(tableIntent)
                }


            }
//            if(position==1)
//            {
//                val i = Intent(context, ProductListActivity::class.java)
//                context?.startActivity(i)
//            }
        }

    }

}


