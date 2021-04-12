package com.example.roomexample.ui.main

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ContatoDao {
    @Query("SELECT * FROM contato")
    suspend fun getAll(): List<Contato>

    @Insert
    suspend fun insertAll(vararg contatos: Contato)

    @Delete
    suspend fun delete (contatos: Contato)
}