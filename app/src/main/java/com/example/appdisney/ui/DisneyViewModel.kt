package com.example.appdisney.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdisney.data.DisneyRepository
import com.example.appdisney.data.Disney
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DisneyViewModel(private val repository: DisneyRepository) : ViewModel() {

    val disneyList: Flow<List<Disney>> = repository.getFilmes()

    fun getFilmeById(id: Int): Flow<Disney> = repository.getFilmeById(id)

    fun addOrUpdateFilme(id: Int? = null, nome: String, ano: Int, protagonista: String, genero: String, diretor: String) {
        val disney = Disney(id = id ?: 0, nome = nome,  ano = ano, protagonista = protagonista, genero = genero, diretor = diretor)
        viewModelScope.launch {
            repository.insertFilme(disney)
        }
    }

    fun deleteSpider(disney: Disney) {
        viewModelScope.launch {
            repository.deleteFilme(disney)
        }
    }
}
