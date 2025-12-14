package com.example.newapp.domain.di

import android.content.Context
import android.hardware.SensorEventListener
import androidx.room.Room
import com.example.newapp.dataLayer.localdb.DatabaseManager
import com.example.newapp.dataLayer.localdb.dao.StepCounterDatabase
import com.example.newapp.domain.hardware.StepCounterSensor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val ST_DATABASE = "step_counter_database"

    @Provides
    @Singleton
    fun provideDatabaseManager(
        database: StepCounterDatabase
    ): DatabaseManager {
        return DatabaseManager( database = database)
    }
    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext context: Context): StepCounterDatabase = try {
        Room.databaseBuilder(context, StepCounterDatabase::class.java, ST_DATABASE)
            .build()
    } catch (error: IllegalStateException) {
        error.printStackTrace()
        context.deleteDatabase(ST_DATABASE)
        Room.databaseBuilder(context, StepCounterDatabase::class.java, ST_DATABASE)
            .fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    @Singleton
    fun providerStepCounterSensor(
        databaseManager : DatabaseManager) : SensorEventListener = StepCounterSensor(databaseManager)
}