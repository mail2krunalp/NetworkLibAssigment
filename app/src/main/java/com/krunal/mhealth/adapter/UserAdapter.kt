package com.krunal.mhealth.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.krunal.mhealth.R
import com.krunal.mhealth.model.UserDataModel

class UserAdapter(val context: Context, val userDataList : ArrayList<UserDataModel>) : RecyclerView.Adapter<UserAdapter.ItemViewHolder>() {
    var mInflater: LayoutInflater? = null

    init {
        mInflater = LayoutInflater.from(context)
    }

    class ItemViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var mUserName: AppCompatTextView = itemView.findViewById(R.id.name)
        internal var mDesignation: AppCompatTextView = itemView.findViewById(R.id.designation)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(context).
            inflate(R.layout.custom_user_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return userDataList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val userDataData: UserDataModel = userDataList[position]

        holder.mUserName.text = userDataData.name
        holder.mDesignation.text = userDataData.designation

    }
}