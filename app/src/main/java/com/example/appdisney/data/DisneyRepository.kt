package com.example.appdisney.data

import kotlinx.coroutines.flow.Flow

open class DisneyRepository(private val disneyDao: DisneyDao) {
    fun getFilmes(): Flow<List<Disney>> = disneyDao.getFilmes()

    fun getFilmeById(id: Int): Flow<Disney> = disneyDao.getFilmeById(id)

    suspend fun insertFilme(disney: Disney) = disneyDao.insertFilme(disney)

    suspend fun deleteFilme(disney: Disney) = disneyDao.deleteFilme(disney)
}
