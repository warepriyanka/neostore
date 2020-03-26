package com.example.neostoreapplication.Activities.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.*
import android.location.Address
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.neostoreapplication.Activities.OrderListActivity
import com.example.neostoreapplication.Adapter.AddressAdapter
import com.example.neostoreapplication.AddressNote
import com.example.neostoreapplication.Model.Responses.ChangePasswordResponse
import com.example.neostoreapplication.R
import com.example.neostoreapplication.ViewModel.AddressViewModel
import com.example.neostoreapplication.ViewModel.PlaceOredrViewModel
import com.example.neostoreapplication.utils.SessionManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_address_list.*
import kotlinx.android.synthetic.main.activity_my_cart.*
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddressListActivity : AppCompatActivity() {

    private val noteViewModel: AddressViewModel by inject()
    lateinit var placeOrderViewModel: PlaceOredrViewModel
    private val adapter: AddressAdapter by inject()
    var pref: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address_list)
        val factory=PlaceOredrViewModel.Factory(this.application)
        placeOrderViewModel= ViewModelProviders.of(this@AddressListActivity,factory).get(PlaceOredrViewModel::class.java)
        val backbtn = findViewById<ImageView>(R.id.backbtn)
        val placeOrder = findViewById<Button>(R.id.placeOrder)

        backbtn.setOnClickListener {
            finish()
        }

        placeOrder.setOnClickListener {
            pref = this@AddressListActivity!!.getSharedPreferences("sharedPrefFile", Context.MODE_PRIVATE)
            editor = pref!!.edit()
            val Address: String = pref!!.getString("address","defaultname")
//            editor!!.clear()
//            editor!!.apply()
            placeNewOrder(Address)
        }

        noteViewModel.getAllNotes().observe(this,
            Observer<List<AddressNote>> { list ->
                list?.let {
                    adapter.setNotes(it)
                }
            })

        setupButtonAddNote()
        setupRecyclerView()
        enableSwipe()
    }


    fun placeNewOrder(address: String)
    {
        Toast.makeText(applicationContext,"Order placed successfully",Toast.LENGTH_LONG).show()

        placeOrderViewModel.placeOrder(SessionManager(this).getToken(),address)
        placeOrderViewModel.getResponse().observe(this,object: Observer<ChangePasswordResponse> {
            override fun onChanged(t: ChangePasswordResponse?) {
                if (t?.status==200)
                {
                    Toast.makeText(applicationContext,"Order placed successfully",Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@AddressListActivity, OrderListActivity::class.java))
                    finish()
                }
            }
        })

    }


    private fun setupButtonAddNote() {
        ivAdd.setOnClickListener{
            intent = Intent(this@AddressListActivity,
                AddAddressActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun setupRecyclerView() {
        addressRecyclerView.layoutManager = LinearLayoutManager(this)
        addressRecyclerView.setHasFixedSize(true)
        addressRecyclerView.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_NOTE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val newNote = AddressNote(
                data.getStringExtra(AddAddressActivity.Address),
                data.getStringExtra(AddAddressActivity.Landmark),
                data.getStringExtra(AddAddressActivity.City),
                data.getStringExtra(AddAddressActivity.State),
                data.getStringExtra(AddAddressActivity.Country),
                data.getStringExtra(AddAddressActivity.ZipCode)

            )
            noteViewModel.insert(newNote)

            Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Note not saved!", Toast.LENGTH_SHORT).show()
        }
    }

    fun  enableSwipe(){
        val simpleItemTouchCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT ) {

                override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition


                }


            }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(myCartRecyclerView)
    }

    companion object{
        private const val ADD_NOTE_REQUEST = 1
    }
}
