package com.assignment.sparklive.network

import android.content.Context
import android.util.Log
import kotlinx.coroutines.delay
import java.io.InputStream


class NetworkApiLogic {

    //Required parameters:
    //url- Network url
    //method: get/post/put/delete
    //cache: Want to fetch data from cache or from network database
    //Description: Here data will stored in preference and url passed will be the Key.
    //Stored data will be mapped based on the url passed
    suspend fun getNetworkResponse(context: Context, url : String, method : String, cache : Boolean) : String? {
        delay(1000)
        if(cache){
            val dataFromPref = getPreference(context,url)
            if(dataFromPref.isNullOrEmpty()){ // no data in preference
                Log.i("NetworkApiLogic" ,"Getting the data from API")
                var response = readJSONFromAsset(context)
                //set the data to pref
                setPreference(context,url,response)
                return response
            }else{
                Log.i("NetworkApiLogic" ,"Getting the data from Shared Preference")
                return  dataFromPref
            }
        }else{
            Log.i("NetworkApiLogic" ,"Getting the data from API")
            var response = readJSONFromAsset(context)
            //set the data to pref
            setPreference(context,url,response)
            return response
        }

    }

    //Here we will get the network data as raw string
    private fun readJSONFromAsset(context: Context): String? {
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

    private fun setPreference(context: Context, key: String?, value: String?): Boolean {
        val settings =
            context.getSharedPreferences("Assignment", Context.MODE_PRIVATE)
        val editor = settings.edit()
        editor.putString(key, value)
        return editor.commit()
    }

    private fun getPreference(context: Context, key: String?): String? {
        val settings =
            context.getSharedPreferences("Assignment", Context.MODE_PRIVATE)
        return settings.getString(key, null)
    }


}