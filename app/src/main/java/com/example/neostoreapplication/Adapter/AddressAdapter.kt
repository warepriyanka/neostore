package com.example.neostoreapplication.Adapter

import android.content.Context
import android.content.SharedPreferences
import android.location.Address
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.neostoreapplication.Activities.MyCartActivity
import com.example.neostoreapplication.Activities.ui.AddressListActivity
import com.example.neostoreapplication.AddressNote
import com.example.neostoreapplication.R
import com.example.neostoreapplication.ViewModel.AddressViewModel
import kotlinx.android.synthetic.main.address_item.view.*
import kotlinx.android.synthetic.main.item_cart_list.view.*

class AddressAdapter(innerContext: Context) : RecyclerView.Adapter<AddressAdapter.NoteHolder>() {
    private var notes: List<AddressNote> = ArrayList()
    var pref: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    val context=innerContext

    class NoteHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.address_item, parent, false)
        return NoteHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val currentNote = notes[position]
        holder.itemView.header.text = "Address"
        holder.itemView.address.text = currentNote.address
        holder.itemView.deleteAddress.setOnClickListener {
//            notes.dr;
//            students.remove(student);
            notifyItemRemoved(position);
        }

        holder.itemView.img.setOnClickListener {
            holder.itemView.img.setBackgroundResource(R.drawable.chky);
        }

        holder.itemView.setOnClickListener {
//            (context as AddressListActivity).addreess(currentNote.address.toString())
            holder.itemView.img.setImageDrawable(
                ContextCompat.getDrawable(
                context, // Context
                R.drawable.chky ))
            pref = context!!.getSharedPreferences("sharedPrefFile",Context.MODE_PRIVATE)
            editor = pref!!.edit()
            editor!!.putString("address",currentNote.address.toString())
            editor!!.apply()
            editor!!.commit()
        }

    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun setNotes(notes: List<AddressNote>) {
        this.notes = notes
        notifyDataSetChanged()
    }

//    inner class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var lineHeader: TextView = itemView.findViewById(R.id.header)
//        var lineAddress: TextView = itemView.findViewById(R.id.address)
//
//    }
}