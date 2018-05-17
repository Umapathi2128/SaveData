package com.example.yugan.savedata.add

import android.annotation.SuppressLint
import android.arch.persistence.room.Room
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import com.example.yugan.savedata.BR
import com.example.yugan.savedata.R
import com.example.yugan.savedata.SnackBarClass
import com.example.yugan.savedata.databinding.ActivityAddActiviityBinding
import com.example.yugan.savedata.repository.room.SaveDataBase
import com.example.yugan.savedata.repository.room.SaveDataModel
import com.example.yugan.savedata.view.MainActivity

class AddActiviity : AppCompatActivity(), AddView {

    private lateinit var addActiviityBinding: ActivityAddActiviityBinding
    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addActiviityBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_activiity)
        val addViewModel = AddViewModel(this)
        addActiviityBinding.setVariable(BR.addData, addViewModel)
    }

    @SuppressLint("PrivateResource")
    override fun addDetails() {
        val databse: SaveDataBase = Room.databaseBuilder(applicationContext, SaveDataBase::class.java, "saveData")
                .allowMainThreadQueries().build()

        val list = databse.saveDataDao().getAll()
        for (i in 0 until list.size) {

            if (list[i].name == addActiviityBinding.etxtName.text.trim().toString()
                    && list[i].userId == addActiviityBinding.etxtUser.text.trim().toString()
                    && list[i].password == addActiviityBinding.etxtPsd.text.trim().toString()) {
                count = 1
            }
        }

        if (count == 1) {
            count=0
            SnackBarClass().snackShow(addActiviityBinding.btnAdd, "This row is already exists!...",this)
        } else {
            databse.saveDataDao().insertDetails(SaveDataModel(addActiviityBinding.etxtName.text.trim().toString()
                    , addActiviityBinding.etxtUser.text.trim().toString()
                    , addActiviityBinding.etxtPsd.text.trim().toString()))
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
        }
    }

    override fun showNameError() {
        addActiviityBinding.etxtName.error = "Name shouldn't be empty"
    }

    override fun showUserIdError() {
        addActiviityBinding.etxtUser.error = "UserID shouldn't be empty"
    }

    override fun showPasswordError() {
        addActiviityBinding.etxtPsd.error = "Password shouldn't be empty"
    }

    override fun validiateName(): Boolean {
        return addActiviityBinding.etxtName.text.trim().toString() == ""
    }

    override fun validiateUser(): Boolean {
        return addActiviityBinding.etxtUser.text.trim().toString() == ""
    }

    override fun validiatePassword(): Boolean {
        return addActiviityBinding.etxtPsd.text.trim().toString() == ""
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onPause() {
        super.onPause()
        finish()
    }

    @SuppressLint("PrivateResource")
    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
    }

}
