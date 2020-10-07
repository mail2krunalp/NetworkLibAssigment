package com.assignment.sparklive.network

import android.content.Context
import android.preference.PreferenceManager
import android.util.Log
import kotlinx.coroutines.delay
import java.io.InputStream


class NetworkApiLogic {

    suspend fun getUserData(context: Context) : String? {
        delay(1000)

        var dataFromPref = getPreference(context,"response")

        if(dataFromPref.isNullOrEmpty()){
             // get the data from api
            var response = readJSONFromAsset(context)
            Log.i("NetworkApiLogic" ,"Getting the data from API")
            //set the data to pref
            setPreference(context,"response",response)
            return getPreference(context,"response")
        }else{
            Log.i("NetworkApiLogic" ,"Getting the data from Shared Preference")
            return dataFromPref
        }

    }

    fun readJSONFromAsset(context: Context): String? {
        var json: String
        try {
            val inputStream: InputStream = context.assets.open("items.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: Exception) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    fun setPreference(context: Context, key: String?, value: String?): Boolean {
        val settings =
            context.getSharedPreferences("Assignment", Context.MODE_PRIVATE)
        val editor = settings.edit()
        editor.putString(key, value)
        return editor.commit()
    }

    fun getPreference(context: Context, key: String?): String? {
        val settings =
            context.getSharedPreferences("Assignment", Context.MODE_PRIVATE)
        return settings.getString(key, null)
    }


}