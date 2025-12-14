package com.example.newapp.dataLayer.localdb.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newapp.dataLayer.models.StepCounterModel

@Database(entities = [StepCounterModel::class], version = 1)
abstract class StepCounterDatabase : RoomDatabase(){
   abstract fun getStepCounterDao() : StepCounterDao
}
