package com.example.newapp.dataLayer.localdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.newapp.dataLayer.models.StepCounterModel
import kotlinx.coroutines.flow.Flow

@Dao
interface StepCounterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(step: StepCounterModel)
    @Update
    suspend fun update(step: StepCounterModel)
    @Query("SELECT * FROM steps WHERE dateTime = :date AND userId = :userId  LIMIT 1")
    fun getStepsForDate(userId : String , date: String): Flow<StepCounterModel?>
    @Query("SELECT * FROM steps WHERE dateTime = :date AND userId = :userId LIMIT 1")
    suspend fun getStepsByDate(userId : String, date: String): StepCounterModel?

    @Query("SELECT * FROM steps WHERE userId = :userId ORDER BY dateTime DESC")
    fun getAllSteps(userId: String): List<StepCounterModel>
}