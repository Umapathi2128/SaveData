package com.example.yugan.savedata.view

import android.annotation.SuppressLint
import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yugan.savedata.databinding.CustomRecyclerBinding
import com.example.yugan.savedata.databinding.EmptyLayoutBinding


class MainAdapter( private var list: ArrayList<MainDataModel>,var context:Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//    override fun getSwipeLayoutResourceId(position: Int): Int {
//        return R.id.swipe
//    }

    private lateinit var customRecyclerBinding: CustomRecyclerBinding
    private lateinit var emptyLayoutBinding: EmptyLayoutBinding
    private var pos=0
//    var mainInterface:MainInterface=context as MainInterface
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if(list.size==0){
            emptyLayoutBinding = DataBindingUtil . inflate (LayoutInflater.from(parent.context)
                    , com.example.yugan.savedata.R.layout.empty_layout, parent, false)
            MyEmptyViewHolder(emptyLayoutBinding.root)
        }
        else {
            customRecyclerBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context)
                    , com.example.yugan.savedata.R.layout.custom_recycler, parent, false)
            MyViewHolder(customRecyclerBinding)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun remove(position:Int){
//        notifyItemChanged(position)
        notifyItemRemoved(position)
        list.removeAt(position)
//        notifyDataSetChanged()
    }

    fun restoreItem(item:MainDataModel,position: Int)
    {
        list.add(position,item)
        notifyItemInserted(position)
//        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        pos=position
        val vHolder = holder as MyViewHolder
        vHolder.onBind( list[position])
//        customRecyclerBinding.name.text = list[position].name
//        customRecyclerBinding.userId.text = list[position].userId
//        customRecyclerBinding.password.text = list[position].password
//
        customRecyclerBinding.linear.setBackgroundResource(list[position].color)
    }

    inner class MyViewHolder(var view: CustomRecyclerBinding) : RecyclerView.ViewHolder(view.root){
        fun onBind(item :MainDataModel){
//            view.swipe.showMode=SwipeLayout.ShowMode.LayDown
//            view.swipe.addSwipeListener( object : SimpleSwipeListener(){
//
//                override fun onOpen(layout: SwipeLayout?) {
//
////                    mainInterface.swipeToRemove(position)
//
////                context.startActivity(Intent(context,MainActivity::class.java))
////                notifyDataSetChanged()
////                notifyItemChanged(position)
////                notifyItemRemoved(position)
////                list.removeAt(position)
////                notifyDataSetChanged()
//                }
//
//            })
            view.viewHolder = item
        }
    }
    inner class MyEmptyViewHolder(var view: View) : RecyclerView.ViewHolder(view)
}