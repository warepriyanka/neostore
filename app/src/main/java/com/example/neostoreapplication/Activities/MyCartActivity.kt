package com.example.neostoreapplication.Activities

import android.app.Dialog
import android.content.Intent
import android.graphics.*
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.neostoreapplication.Activities.ui.AddressListActivity
import com.example.neostoreapplication.Adapter.MyCartAdapter
import com.example.neostoreapplication.Model.Responses.AddToCartResponse
import com.example.neostoreapplication.Model.Responses.CartItemListData
import com.example.neostoreapplication.Model.Responses.GetCartItemResponse
import com.example.neostoreapplication.R
import com.example.neostoreapplication.ViewModel.MyCartViewModel
import com.example.neostoreapplication.utils.SessionManager
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_my_cart.*
import kotlinx.android.synthetic.main.dialog_buy_now.*

class MyCartActivity : AppCompatActivity(){
    lateinit var myCartViewModel: MyCartViewModel
    var myCartAdapter: MyCartAdapter?=null
    var cartItemListData:ArrayList<CartItemListData> = ArrayList()
    private val p = Paint()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_cart)

        val imgback = findViewById<ImageView>(R.id.backbtn)
        val btnOrderNow = findViewById<Button>(R.id.btnOrdereNow)
        btnOrderNow.setOnClickListener{
            intent = Intent(this@MyCartActivity,
                AddressListActivity::class.java)
            startActivity(intent)
            finish()
        }
        imgback.setOnClickListener{
            finish()
        }

        val myCartViewModelFactory= MyCartViewModel.Factory(this.application)
        myCartViewModel= ViewModelProviders.of(this,myCartViewModelFactory).get(MyCartViewModel::class.java)
        getMyCartList()
        val recyclerViewLayOutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL,false)
        myCartRecyclerView.layoutManager=recyclerViewLayOutManager
        myCartRecyclerView.itemAnimator
        enableSwipe()

    }

    fun  enableSwipe(){
        val simpleItemTouchCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT ) {

                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition

                    if (direction == ItemTouchHelper.LEFT) {

                        val productId:String
                        productId= cartItemListData.get(position).product_id.toString()
                        deleteProduct(productId)
                        val deletedModel = cartItemListData!![position]
                        myCartAdapter!!.removeItem(position)
                        // showing snack bar with Undo option
                        val snackbar = Snackbar.make(
                            window.decorView.rootView,
                            " removed from Recyclerview!",
                            Snackbar.LENGTH_LONG
                        )
//                        snackbar.setAction("UNDO") {
//                            // undo is selected, restore the deleted item
//                            myCartAdapter!!.restoreItem(deletedModel, position)
//                        }
                        snackbar.setActionTextColor(Color.YELLOW)
                        snackbar.show()
                    } else {
                        val deletedModel = cartItemListData!![position]
                        myCartAdapter!!.removeItem(position)
                        // showing snack bar with Undo option
                        val snackbar = Snackbar.make(
                            window.decorView.rootView,
                            " removed from Recyclerview!",
                            Snackbar.LENGTH_LONG
                        )
//                        snackbar.setAction("UNDO") {
//                            // undo is selected, restore the deleted item
//                            myCartAdapter!!.restoreItem(deletedModel, position)
//                        }
                        snackbar.setActionTextColor(Color.YELLOW)
                        snackbar.show()
                    }
                }

                override fun onChildDraw(
                    c: Canvas,
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    dX: Float,
                    dY: Float,
                    actionState: Int,
                    isCurrentlyActive: Boolean
                ) {

                    val icon: Bitmap
                    if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                        val itemView = viewHolder.itemView
                        val height = itemView.bottom.toFloat() - itemView.top.toFloat()
                        val width = height / 3

                        if (dX > 0) {
                            p.color = Color.parseColor("#388E3C")
                            val background =
                                RectF(itemView.left.toFloat(), itemView.top.toFloat(), dX, itemView.bottom.toFloat())
                            c.drawRect(background, p)
                            icon = BitmapFactory.decodeResource(resources, R.drawable.delete)
                            val icon_dest = RectF(
                                itemView.left.toFloat() + width,
                                itemView.top.toFloat() + width,
                                itemView.left.toFloat() + 2 * width,
                                itemView.bottom.toFloat() - width
                            )
                            c.drawBitmap(icon, null, icon_dest, p)
                        } else {
                            p.color = Color.parseColor("#D32F2F")
                            val background = RectF(
                                itemView.right.toFloat() + dX,
                                itemView.top.toFloat(),
                                itemView.right.toFloat(),
                                itemView.bottom.toFloat()
                            )
                            c.drawRect(background, p)
                            icon = BitmapFactory.decodeResource(resources, R.drawable.delete)
                            val icon_dest = RectF(
                                itemView.right.toFloat() - 2 * width,
                                itemView.top.toFloat() + width,
                                itemView.right.toFloat() - width,
                                itemView.bottom.toFloat() - width
                            )
                            c.drawBitmap(icon, null, icon_dest, p)
                        }
                    }
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                }
            }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(myCartRecyclerView)
    }

        fun getMyCartList()
        {

            myCartViewModel.getCartList(SessionManager(this).getToken())
            myCartViewModel.getMyCartResponse().observe(this,object : Observer<GetCartItemResponse> {
                override fun onChanged(t: GetCartItemResponse?) {
                    cartItemListData= t!!.data as ArrayList<CartItemListData>

                    Log.e("hhh",cartItemListData?.size.toString())
                    val totalPrice=t?.total.toString()
                    totalPriceTextView.text="₹ $totalPrice"
                    myCartAdapter = MyCartAdapter(this@MyCartActivity,cartItemListData)
                    myCartRecyclerView.adapter = myCartAdapter
                }
            })

        }


        fun editProductDialog(productId: Int, imageUrl:String, productName:String, productQuantity:String)
        {
            val dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog .setContentView(com.example.neostoreapplication.R.layout.dialog_buy_now)

            val  drawable = dialog.quantityDialogEditText.getBackground() as GradientDrawable
            drawable.setStroke(4, Color.parseColor("#00D53A"))
            dialog.productNameDialogTextView.text=productName
            Picasso.get().load(imageUrl).into(dialog.productDialogImageView)
            dialog.quantityDialogEditText.setText(productQuantity)
            dialog.submitBuyNowDialogButton.setOnClickListener {
                val quantity = dialog.quantityDialogEditText.text.toString()
                editProduct(productId.toString(),quantity,dialog)
            }

            dialog.show()

        }


        fun deleteProduct(productId: String)
        {
            myCartViewModel.deleteProduct(SessionManager(this).getToken(),productId)
            myCartViewModel.deleteProductResponse().observe(this,object : Observer<AddToCartResponse> {
                override fun onChanged(t: AddToCartResponse?) {
                    if (t?.status==200)
                    {
                        getMyCartList()
                        myCartAdapter?.notifyDataSetChanged()

                    }
                }
            })

        }


        fun editProduct(productId: String,quantity:String,dialog: Dialog)
        {
            myCartViewModel.editQuantity(SessionManager(this).getToken(),productId,quantity)
            myCartViewModel.editQuantityResponse().observe(this,object : Observer<AddToCartResponse> {
                override fun onChanged(t: AddToCartResponse?) {
                    if (t?.status==200)
                    {
                        getMyCartList()
                        myCartAdapter?.notifyDataSetChanged()
                        dialog.dismiss()

                    }
                }
            })

        }

}
