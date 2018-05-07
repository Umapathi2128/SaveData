package com.example.yugan.savedata.add

import android.arch.persistence.room.Room
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.yugan.savedata.R
import com.example.yugan.savedata.BR
import com.example.yugan.savedata.databinding.ActivityAddActiviityBinding
import com.example.yugan.savedata.repository.room.SaveDataBase
import com.example.yugan.savedata.repository.room.SaveDataModel
import com.example.yugan.savedata.view.MainActivity

class AddActiviity : AppCompatActivity(),AddView {

    private lateinit var addActiviityBinding:ActivityAddActiviityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addActiviityBinding=DataBindingUtil.setContentView(this,R.layout.activity_add_activiity)
        val addViewModel=AddViewModel(this)
        addActiviityBinding.setVariable(BR.addData,addViewModel)
    }

    override fun addDetails() {
        val databse:SaveDataBase=Room.databaseBuilder(applicationContext,SaveDataBase::class.java,"saveData")
                                        .allowMainThreadQueries().build()
        databse.saveDataDao().insertDetails(SaveDataModel(addActiviityBinding.etxtName.text.trim().toString()
                                            ,addActiviityBinding.etxtUser.text.trim().toString()
                                            ,addActiviityBinding.etxtPsd.text.trim().toString()))

        startActivity(Intent(this,MainActivity::class.java))
        finish()

    }

    override fun showNameError() {
        addActiviityBinding.etxtName.error="Name shouldn't be empty"
    }

    override fun showUserIdError() {
        addActiviityBinding.etxtUser.error="UserID shouldn't be empty"
    }

    override fun showPasswordError() {
        addActiviityBinding.etxtPsd.error="Password shouldn't be empty"
    }

    override fun validiateName(): Boolean {
        return addActiviityBinding.etxtName.text.trim().toString()==""
    }

    override fun validiateUser(): Boolean {
        return addActiviityBinding.etxtUser.text.trim().toString()==""
    }

    override fun validiatePassword(): Boolean {
        return addActiviityBinding.etxtPsd.text.trim().toString()==""
    }


}
