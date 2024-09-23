package com.example.appdisney.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DisneyDao {
    @Query("SELECT * FROM filmes")
    fun getFilmes(): Flow<List<Disney>>

    @Query("SELECT * FROM filmes WHERE id = :id")
    fun getFilmeById(id: Int): Flow<Disney>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilme(disney: Disney)

    @Delete
    suspend fun deleteFilme(disney: Disney)
}
