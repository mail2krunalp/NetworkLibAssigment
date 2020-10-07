package com.krunal.mhealth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assignment.sparklive.network.Status
import com.google.gson.Gson
import com.krunal.mhealth.ViewModel.UserViewModel
import com.krunal.mhealth.adapter.UserAdapter
import com.krunal.mhealth.model.UserDataModel
import com.krunal.mhealth.model.UserMainModel

class MainActivity : AppCompatActivity() {

    private  lateinit var mProgressBar: LinearLayout
    private lateinit var mUserView : RecyclerView
    private var mAdapter : UserAdapter?=null
    private var userDataList : ArrayList<UserDataModel> = ArrayList()
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mProgressBar = findViewById(R.id.llProgressBar)
        mUserView = findViewById(R.id.user_list)
        mAdapter = UserAdapter(this, userDataList)
        mUserView.layoutManager = LinearLayoutManager(this)
        mUserView.adapter = mAdapter
        getUserData()


    }

    private fun getUserData() {
        viewModel =
                ViewModelProviders.of(this).get(UserViewModel::class.java)

        viewModel.fetchUserDataData(this).observe(this, androidx.lifecycle.Observer {
            it?.let{ resource ->
                when(resource.status){
                    Status.SUCCESS -> {
                        mUserView.visibility = View.VISIBLE
                        mProgressBar.visibility = View.GONE
                        resource.data?.let {
                            stream -> retrieveList(stream)
                        }
                    }
                    Status.ERROR -> {
                        mProgressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        mProgressBar.visibility = View.VISIBLE
                        mUserView.visibility = View.GONE
                    }

                }
            }
        })

    }

    private fun retrieveList(stream: String) {
        val gson = Gson()
        var list = gson.fromJson(stream, UserMainModel::class.java)

        userDataList.apply {
            clear()
            if(stream.isNotEmpty()){
                list.resultsModel?.let { addAll(it) }
                mAdapter?.notifyDataSetChanged()
            }
        }
    }
}