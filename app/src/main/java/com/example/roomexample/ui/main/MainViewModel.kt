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

    fun addContato(contato: Contato) {
        viewModelScope.launch {
            database.contatoDao().insertAll(contato)
        }.invokeOnCompletion {
            buscarContato()
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