package com.example.roomexample.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    lateinit var database: RoomDatabase
    val contatosLiveData by lazy { MutableLiveData<List<Contato>>() }

    fun buscarContato() {
        viewModelScope.launch {
            val contatos = database.contatoDao().getAll()
            contatosLiveData.postValue(contatos)
        }
    }

    fun ultimoContato() {
        viewModelScope.launch {
            val last = database.contatoDao().getAll().last()
            contatosLiveData.postValue(listOf(last))
        }
    }

    fun addContato(contato: Contato) {
        viewModelScope.launch {
            database.contatoDao().insertAll(contato)
        }.invokeOnCompletion {
//            buscarContato()
            ultimoContato()
        }
    }

    fun delete() {
        val ultimoContato = contatosLiveData.value?.last() ?: return
        viewModelScope.launch {
            database.contatoDao().delete(ultimoContato)
            buscarContato()
        }
    }
}