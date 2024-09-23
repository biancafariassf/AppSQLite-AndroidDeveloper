package com.example.appdisney.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "filmes")
data class Disney(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val ano: Int,
    val protagonista: String,
    val genero: String,
    val diretor: String
)
