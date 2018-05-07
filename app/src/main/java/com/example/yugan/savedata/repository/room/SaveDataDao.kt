package com.example.yugan.savedata.repository.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface SaveDataDao {

    @Query("SELECT * FROM SaveDataModel")
    fun getAll():MutableList<SaveDataModel>

    @Insert
    fun insertDetails(saveDataModel: SaveDataModel)

    @Delete
    fun deleteData(saveDataModel: SaveDataModel)
}