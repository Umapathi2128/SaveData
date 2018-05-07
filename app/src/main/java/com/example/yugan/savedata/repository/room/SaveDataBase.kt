package com.example.yugan.savedata.repository.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(SaveDataModel::class),version = 1)
abstract class SaveDataBase : RoomDatabase() {
    abstract fun saveDataDao():SaveDataDao
}