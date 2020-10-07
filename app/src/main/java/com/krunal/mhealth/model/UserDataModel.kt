package com.krunal.mhealth.model

import com.google.gson.annotations.SerializedName

class UserDataModel{
    @SerializedName("id")
    var id:Int?=null

    @SerializedName("name")
    var name: String?=null

    @SerializedName("designation")
    var designation:String?=null
}