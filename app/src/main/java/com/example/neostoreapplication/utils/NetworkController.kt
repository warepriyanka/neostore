package com.example.neostoreapplication.utils

import android.media.session.MediaSession
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.neostoreapplication.Interface.Api
import com.example.neostoreapplication.Model.Responses.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkController {
    var service: Api?= null
    constructor(){
            val retrofit = Retrofit.Builder()
                .baseUrl(Service.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        service = retrofit.create(Api::class.java)
    }
    @Synchronized
    fun getInstance():NetworkController{
        val networkController = NetworkController()
        return networkController
    }

    fun signUp(firstName:String,lastName:String,email:String,password:String,cnfPassword:String,gender:String,phoneNumber:String): LiveData<registerUser>
    {
        var data = MutableLiveData<registerUser>()
        service?.registerUser(firstName,lastName,email, password,cnfPassword,gender,phoneNumber)?.enqueue(object :
            Callback<registerUser> {
            override fun onFailure(call: Call<registerUser>?, t: Throwable) {
                t.printStackTrace()
                data.value = (null)
            }

            override fun onResponse(call: Call<registerUser>?, response: Response<registerUser>?) {
                data.value=response?.body()
            }
        })
        return data
    }

    fun login(email:String,password:String):LiveData<getLogin>
    {
        var data =MutableLiveData<getLogin>()
        service?.login(email, password)?.enqueue(object :Callback<getLogin>{
            override fun onFailure(call: Call<getLogin>?, t: Throwable) {
                t.printStackTrace()
                data.value = (null)
            }

            override fun onResponse(call: Call<getLogin>?, response: Response<getLogin>?) {
                data.value=response?.body()
            }
        })
        return data
    }

    fun getProductList(productCategoryId: String, limit: String, page: String):LiveData<getProduct>
    {
        var data=MutableLiveData<getProduct>()
        service?.productList(productCategoryId,limit.toInt(),page.toInt())?.enqueue(object :Callback<getProduct>{
            override fun onFailure(call: Call<getProduct>?, t: Throwable) {
                t.printStackTrace()
                data.value = (null)
            }

            override fun onResponse(call: Call<getProduct>?, response: Response<getProduct>?) {
                data.value=response?.body()
            }
        })
        return data
    }

    fun getProductDetails(product_id: String):LiveData<ProductDetailResponse>{
        var data = MutableLiveData<ProductDetailResponse>()
        service?.getDetail(product_id)?.enqueue(object:Callback<ProductDetailResponse>{
            override fun onFailure(call: Call<ProductDetailResponse>?, t: Throwable) {
                t.printStackTrace()
                data.value = (null)
            }

            override fun onResponse(call: Call<ProductDetailResponse>?, response: Response<ProductDetailResponse>?) {
            data.value= response?.body()

            }
        })
        return data
    }

    fun addToCart(accessToken:String,productId:String,quantity:String):LiveData<AddToCartResponse>
    {
        var data=MutableLiveData<AddToCartResponse>()
        service?.addToCart(accessToken,productId.toInt(),quantity.toInt())?.enqueue(object :Callback<AddToCartResponse>{
            override fun onFailure(call: Call<AddToCartResponse>?, t: Throwable) {
                t.printStackTrace()
                data.value = (null)
            }

            override fun onResponse(
                call: Call<AddToCartResponse>?,
                response: Response<AddToCartResponse>?
            ) {
                data.value=response?.body()
            }
        })

        return data
    }

    fun getMyCartList(accessToken: String):LiveData<GetCartItemResponse>
    {

            var data = MutableLiveData<GetCartItemResponse>()
            try {
                service?.getMycart(accessToken)?.enqueue(object : Callback<GetCartItemResponse> {
                    override fun onFailure(call: Call<GetCartItemResponse>?, t: Throwable) {
                        t.printStackTrace()
                        data.value = (null)
                    }

                    override fun onResponse(
                        call: Call<GetCartItemResponse>?,
                        response: Response<GetCartItemResponse>?
                    ) {
                        data.value = response?.body()
                    }
                })

            }catch (e: Exception){
                e.printStackTrace()
            }
            return data
    }

    fun getOrderList(accessToken: String):LiveData<OrderListResponse>
    {
        var data=MutableLiveData<OrderListResponse>()
        try {

            service?.getOrder(accessToken)?.enqueue(object :Callback<OrderListResponse>{
            override fun onFailure(call: Call<OrderListResponse>?, t: Throwable) {
                t.printStackTrace()
                data.value = (null)
            }

            override fun onResponse(call: Call<OrderListResponse>?, response: Response<OrderListResponse>?
            ) {
                data.value=response?.body()
            }
        })
        }catch (e: Exception){
            e.printStackTrace()
        }
        return data
    }


    fun getOrderDetails(accessToken: String, orderId: Int):LiveData<OrderDetailResponse>
    {
        var data=MutableLiveData<OrderDetailResponse>()
        try {

            service?.getOrderDetail(accessToken, orderId)?.enqueue(object :Callback<OrderDetailResponse>{
                override fun onFailure(call: Call<OrderDetailResponse>?, t: Throwable) {
                    t.printStackTrace()
                    data.value = (null)
                }

                override fun onResponse(call: Call<OrderDetailResponse>?, response: Response<OrderDetailResponse>?
                ) {
                    data.value=response?.body()
                }
            })
        }catch (e: Exception){
            e.printStackTrace()
        }
        return data
    }

    fun editCart(accessToken:String,productId:String,quantity:String):LiveData<AddToCartResponse>
    {
        var data=MutableLiveData<AddToCartResponse>()
        service?.editCart(accessToken,productId.toInt(),quantity.toInt())?.enqueue(object :Callback<AddToCartResponse>{
            override fun onFailure(call: Call<AddToCartResponse>?, t: Throwable) {
                t.printStackTrace()
                data.value = (null)
            }

            override fun onResponse(
                call: Call<AddToCartResponse>?,
                response: Response<AddToCartResponse>?
            ) {
                data.value=response?.body()
            }
        })

        return data

    }

    fun deleteCart(accessToken:String,productId:String):LiveData<AddToCartResponse>
    {
        var data=MutableLiveData<AddToCartResponse>()
        service?.deleteCart(accessToken,productId.toInt())?.enqueue(object :Callback<AddToCartResponse>{
            override fun onFailure(call: Call<AddToCartResponse>?, t: Throwable) {
                t.printStackTrace()
                data.value = (null)

            }

            override fun onResponse(call: Call<AddToCartResponse>?, response: Response<AddToCartResponse>?
            ) {
                data.value=response?.body()
            }
        })

        return data
    }

    fun getUserData(accessToken: String):LiveData<getUserData>
    {
        var data=MutableLiveData<getUserData>()
        service?.getUserData(accessToken)?.enqueue(object :Callback<getUserData>{
            override fun onFailure(call: Call<getUserData>?, t: Throwable) {
                t.printStackTrace()
                data.value = (null)
            }

            override fun onResponse(
                call: Call<getUserData>?,
                response: Response<getUserData>?
            ) {
                data.value=response?.body()
            }
        })
        return data
    }


    fun saveUserData(accessToken: String, email: String, dob: String, phoneNumber: String, profile_pic: String):LiveData<UserData>
    {
        var data=MutableLiveData<UserData>()
        service?.updateUserData(accessToken, email, dob, phoneNumber, profile_pic)?.enqueue(object :Callback<UserData>{
            override fun onFailure(call: Call<UserData>?, t: Throwable) {
                t.printStackTrace()
                data.value = (null)
            }

            override fun onResponse(
                call: Call<UserData>?,
                response: Response<UserData>?
            ) {
                data.value=response?.body()
            }
        })
        return data
    }


    fun resetPassword(accessToken:String,old_password:String,password:String, confirm_password:String):LiveData<ChangePasswordResponse>
    {
        var data=MutableLiveData<ChangePasswordResponse>()
        service?.changePassword(accessToken,old_password,password, confirm_password)?.enqueue(object :Callback<ChangePasswordResponse>{
            override fun onFailure(call: Call<ChangePasswordResponse>?, t: Throwable) {
                t.printStackTrace()
                data.value = (null)
            }

            override fun onResponse(
                call: Call<ChangePasswordResponse>?,
                response: Response<ChangePasswordResponse>?
            ) {
                data.value=response?.body()
            }
        })

        return data
    }


    fun placeOrder(accessToken:String, address:String):LiveData<ChangePasswordResponse>
    {
        var data=MutableLiveData<ChangePasswordResponse>()
        service?.placeOrder1(accessToken,address)?.enqueue(object :Callback<ChangePasswordResponse>{
            override fun onFailure(call: Call<ChangePasswordResponse>?, t: Throwable) {
                t.printStackTrace()
                data.value = (null)
            }

            override fun onResponse(
                call: Call<ChangePasswordResponse>?,
                response: Response<ChangePasswordResponse>?
            ) {
                data.value=response?.body()
            }
        })

        return data
    }

}