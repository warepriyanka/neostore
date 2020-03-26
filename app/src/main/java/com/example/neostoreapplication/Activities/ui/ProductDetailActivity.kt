package com.example.neostoreapplication.Activities.ui

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neostoreapplication.Activities.MyCartActivity
import com.example.neostoreapplication.Adapter.ProductImageAdapter
import com.example.neostoreapplication.Model.Responses.AddToCartResponse
import com.example.neostoreapplication.Model.Responses.ProductDetailResponse
import com.example.neostoreapplication.Model.Responses.ProductImage
import com.example.neostoreapplication.R
import com.example.neostoreapplication.ViewModel.ProductDetailViewModel
import com.example.neostoreapplication.utils.SessionManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.android.synthetic.main.dialog_buy_now.*

class ProductDetailActivity : AppCompatActivity() {

    var imageUrl:String?=null
    private lateinit var productDetailViewModel: ProductDetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        val productId=intent.getIntExtra("productId",0)
        val productName = intent.getStringExtra("productName")

        val factory= ProductDetailViewModel.Factory(this.application)
        productDetailViewModel= ViewModelProviders.of(this,factory).get(ProductDetailViewModel::class.java)

        val text=findViewById<TextView>(com.example.neostoreapplication.R.id.titletext)
        val imgback = findViewById<ImageView>(R.id.backbtn)
        text.setText(productName)

        getProductDetail(productId.toString())

        buyNowButton.setOnClickListener {
            buyKnowDialog(productId)
        }

        shareProductImageView.setOnClickListener{
            shareProduct(imageUrl)
        }

        imgback.setOnClickListener{
            finish()
        }

    }

    fun getProductDetail(productId:String)
    {
        productDetailViewModel.getProductDetail(productId)
        productDetailViewModel.getProductDetailViewModel().observe(this,object: Observer<ProductDetailResponse>
        {
            override fun onChanged(t: ProductDetailResponse?) {
                productNameTextView.text=t?.data?.name
                val imageUrlArray=t?.data?.product_images as ArrayList<ProductImage>
                val adapter= ProductImageAdapter(this@ProductDetailActivity,imageUrlArray)
                val recyclerViewLayOutManager = LinearLayoutManager(applicationContext,
                    LinearLayoutManager.HORIZONTAL,false)
                productImageRecyclerView.layoutManager=recyclerViewLayOutManager
                productImageRecyclerView.adapter=adapter
                producerTextView.text=t.data.producer
                descriptionTextView.text=t.data.description
                val cost=t.data.cost.toString()
                productPriceTextView.text="Rs. $cost"
                imageUrl=t.data.product_images[0].image
                setImage(imageUrl!!)
            }

        })
    }

    fun buyNow(productId: String,quantity:String)
    {
        productDetailViewModel.addtoCart(SessionManager(this).getToken(),productId,quantity)
        productDetailViewModel.getAddToCartResponseVM().observe(this,object: Observer<AddToCartResponse> {
            override fun onChanged(t: AddToCartResponse?) {
                if (t?.status==200)
                {
                    //Toast.makeText(this,t.message,Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@ProductDetailActivity, MyCartActivity::class.java))
                    finish()
                }
            }
        })

    }

    fun setImage(url:String)
    {
        val image=findViewById<ImageView>(R.id.productImageVieww)
        Picasso.get().load(url).into(image)
    }


    fun buyKnowDialog(productId: Int)
    {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog .setContentView(com.example.neostoreapplication.R.layout.dialog_buy_now)

        val  drawable = dialog.quantityDialogEditText.getBackground() as GradientDrawable
        drawable.setStroke(4, Color.parseColor("#00D53A"))
        dialog.productNameDialogTextView.text=producerTextView.text
        Picasso.get().load(imageUrl).into(dialog.productDialogImageView)
        dialog.submitBuyNowDialogButton.setOnClickListener {
            val quantity = dialog.quantityDialogEditText.text.toString()
            if(quantity.equals("")){
                Toast.makeText(this,"Please enter Quantity first",Toast.LENGTH_LONG).show()
            }else {
                buyNow(productId.toString(), quantity)
            }
        }
        dialog.show()

    }

    fun shareProduct(shareString:String?)
    {
        val sharingIntent= Intent(Intent.ACTION_SEND)
        sharingIntent.type=("text/plain")
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here")
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareString)
        startActivity(Intent.createChooser(sharingIntent, "Share via"))
    }
}
