package com.example.pizzaplanets.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "planets")
data class Planet(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    // Pizza Planets's Standard Planetary Code
    @ColumnInfo(name = "planet_code")
    val planetCode: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "travel_duration_ms")
    val travelDurationMs: Int,
)