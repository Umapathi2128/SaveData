package com.example.yugan.savedata.add

interface AddView {

    fun addDetails()

    fun showNameError()

    fun showUserIdError()

    fun showPasswordError()

    fun validiateName():Boolean

    fun validiateUser():Boolean

    fun validiatePassword():Boolean
}