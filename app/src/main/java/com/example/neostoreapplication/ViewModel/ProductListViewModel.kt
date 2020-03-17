package com.example.neostoreapplication.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.neostoreapplication.Model.Responses.getProduct
import com.example.neostoreapplication.utils.NetworkController

class ProductListViewModel : AndroidViewModel{

    var  productList: LiveData<getProduct>?= null
    constructor(product_category_id:String,limit:Int,page:Int,application: Application) : super(application)
    {
        productList=NetworkController().getInstance().getProductList(product_category_id,limit.toString(),page.toString())
    }

    fun getProductListData():LiveData<getProduct>
    {
        return productList!!
    }

    class Factory(product_category_id: String,limit: Int,page: Int,application: Application): ViewModelProvider.NewInstanceFactory() {
        val application=application
        val product_category_id=product_category_id
        val limit=limit
        val page=page

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ProductListViewModel(product_category_id,limit,page,application) as T
        }
    }
}