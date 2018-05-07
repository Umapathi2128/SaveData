package com.example.yugan.savedata.view

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yugan.savedata.databinding.CustomRecyclerBinding

class MainAdapter(var list:ArrayList<MainDataModel>, var context: Context, var colors:ArrayList<Int>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var customRecyclerBinding:CustomRecyclerBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        customRecyclerBinding=DataBindingUtil.inflate(LayoutInflater.from(parent.context)
                    , com.example.yugan.savedata.R.layout.custom_recycler,parent,false)
        return MyViewHolder(customRecyclerBinding.root)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        customRecyclerBinding.name.text=list[position].name
        customRecyclerBinding.userId.text=list[position].userId
        customRecyclerBinding.password.text=list[position].password
        var count=0
        if(list.size<=6) count++
        else count=0


//        customRecyclerBinding.linear.setBackgroundColor(colors[position])
    }

    class MyViewHolder( var view:View):RecyclerView.ViewHolder(view)
}