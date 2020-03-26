package com.example.neostoreapplication

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "address_table")
class AddressNote (



    var address:String,
    var landmark: String,
    var city:String,
    var state:String,
    var country: String,
    var zipCode: String

){
    @PrimaryKey(autoGenerate = true)
    var id:Int =0
}
