package com.family.okr

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Okr(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String
)