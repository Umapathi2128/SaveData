package com.example.yugan.savedata.repository.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class SaveDataModel(
                    @field:ColumnInfo(name = "name") var name:String
                    ,@field:ColumnInfo(name="userId") var userId:String
                    ,@field:ColumnInfo(name="password") var password:String){
    @PrimaryKey(autoGenerate = true) var id:Int=0
}