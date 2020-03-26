package com.example.neostoreapplication.Interface

import com.example.neostoreapplication.Model.Responses.*
import com.example.neostoreapplication.ViewModel.UserDataViewModel
import retrofit2.Call
import retrofit2.http.*

interface Api {
    @FormUrlEncoded
    @POST("users/register")
    fun registerUser(@Field("first_name") firstName: String ,@Field("last_name") last_name: String ,
                     @Field("email") email: String ,@Field("password") password: String
                     ,@Field("confirm_password") confirm_password: String,@Field("gender") gender: String,
                     @Field("phone_no") phone_no: String): Call<registerUser>



    @FormUrlEncoded
    @POST("users/login")
    fun login(@Field("email") email: String ,@Field("password") password: String): Call<getLogin>

    @GET("products/getList")
    fun productList(@Query("product_category_id")product_category_id:String, @Query("limit")limit:Int, @Query("page")page: Int):Call<getProduct>

    @GET("products/getDetail")
    fun  getDetail(@Query("product_id")product_id:String):Call<ProductDetailResponse>

    @GET("cart")
    fun  getMycart(@Header("access_token")access_token:String):Call<GetCartItemResponse>

    @FormUrlEncoded
    @POST("editCart")
    fun editCart(@Header("access_token")access_token:String, @Field("product_id") product_id: Int, @Field("quantity") quantity: Int): Call<AddToCartResponse>

    @FormUrlEncoded
    @POST("deleteCart")
    fun deleteCart(@Header("access_token")access_token:String, @Field("product_id") product_id: Int): Call<AddToCartResponse>

    @GET("users/getUserData")
    fun getUserData(@Header("access_token")access_token:String): Call<getUserData>

    @FormUrlEncoded
    @POST("addToCart")
    fun addToCart(@Header("access_token")access_token:String, @Field("product_id") product_id: Int, @Field("quantity") quantity: Int): Call<AddToCartResponse>

    @GET("users/change")
    fun changePassword(@Header("access_token")access_token:String,  @Field("old_password")old_password: String, @Field("password")password: String,
                       @Field("confirm_password")confirm_password: String): Call<ChangePasswordResponse>


    @FormUrlEncoded
    @POST("users/update")
    fun updateUserData(@Header("access_token")access_token:String, @Field("email") email: String, @Field("dob") dob: String,
                       @Field("phone_no") phone_no: String, @Field("profile_pic") profile_pic: String): Call<UserData>

    @FormUrlEncoded
    @POST("order")
    fun placeOrder1(@Header("access_token")access_token:String,  @Field("address")address: String): Call<ChangePasswordResponse>

    @GET("orderList")
    fun  getOrder(@Header("access_token")access_token:String):Call<OrderListResponse>

    @GET("orderDetail")
    fun  getOrderDetail(@Header("access_token")access_token:String,  @Field("order_id")order_id: Int):Call<OrderDetailResponse>

}