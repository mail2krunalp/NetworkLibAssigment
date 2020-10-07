package com.krunal.mhealth.model

import com.google.gson.annotations.SerializedName

class UserMainModel {
    @SerializedName("results")
    var resultsModel: ArrayList<UserDataModel>? = null
        private set
}