package com.example.yugan.savedata.view

import android.app.Activity
import android.app.KeyguardManager
import android.arch.persistence.room.Room
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.yugan.savedata.BR
import com.example.yugan.savedata.R
import com.example.yugan.savedata.add.AddActiviity
import com.example.yugan.savedata.databinding.ActivityMainBinding
import com.example.yugan.savedata.repository.room.SaveDataBase

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var list:ArrayList<MainDataModel>
    private lateinit var colors:ArrayList<Int>
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val mainViewModel= MainViewModel(this)
        activityMainBinding.setVariable(BR.add,mainViewModel)



        val databse: SaveDataBase = Room.databaseBuilder(applicationContext, SaveDataBase::class.java,"saveData")
                .allowMainThreadQueries().build()
        val alist=databse.saveDataDao().getAll()
        list= ArrayList()
        for(i in 0..alist.size-1)
        {
            list.add(MainDataModel(alist[i].name,alist[i].userId, alist[i].password))
        }


//        list.add(MainDataModel("uma", "uma@gmail.com", "1234"))
//        list.add(MainDataModel("uma", "098397559382", "1234"))
//        list.add(MainDataModel("uma", "axis bank", "1234"))
//        list.add(MainDataModel("uma", "andhra bank", "1234"))
//        list.add(MainDataModel("uma", "andhra LoginID", "1234"))
//        list.add(MainDataModel("uma", "andhra Login psd", "1234"))
        colors=ArrayList()
        colors.add(R.color.red)
        colors.add(R.color.lightYellow)
        colors.add(R.color.hash)
        colors.add(R.color.unknown)
        colors.add(R.color.orange)
        colors.add(R.color.skyBlue)
        val mainAdapter= MainAdapter(list, this, colors)
        val recyclerView: RecyclerView.LayoutManager= LinearLayoutManager(applicationContext)
        activityMainBinding.recyclerview.layoutManager=recyclerView
        activityMainBinding.recyclerview.itemAnimator = DefaultItemAnimator()
        activityMainBinding.recyclerview.adapter = mainAdapter

    }

    override fun addDetails() {
        Toast.makeText(this,"fab",Toast.LENGTH_LONG).show()
        startActivity(Intent(this,AddActiviity::class.java))
    }



}
