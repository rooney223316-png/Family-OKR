package com.family.okr

import androidx.room.*

@Database(entities = [Okr::class], version = 1)
abstract class OkrDatabase : RoomDatabase() {
    abstract fun okrDao(): OkrDao
}

@Dao
interface OkrDao {
    @Insert
    fun insert(okr: Okr)

    @Query("SELECT * FROM Okr")
    fun getAll(): List<Okr>
}