package com.example.yugan.savedata.add

class AddViewModel(var addView: AddView) {

    fun addDetails()
    {
        if(addView.validiateName())
            addView.showNameError()
        else if (addView.validiateUser())
            addView.showUserIdError()
        else if (addView.validiatePassword())
            addView.showPasswordError()
        else addView.addDetails()
    }
}