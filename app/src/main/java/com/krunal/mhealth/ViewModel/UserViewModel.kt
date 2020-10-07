package com.krunal.mhealth.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.assignment.sparklive.network.NetworkApiLogic
import com.assignment.sparklive.network.Resource
import kotlinx.coroutines.Dispatchers

class UserViewModel : ViewModel() {

    fun fetchUserDataData(context: Context)
            = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = NetworkApiLogic().getUserData(context)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}