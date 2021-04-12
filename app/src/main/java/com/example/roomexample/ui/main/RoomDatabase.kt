package com.example.roomexample.ui.main

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Contato::class], version = 1)
abstract class RoomDatabase: RoomDatabase() {
    abstract fun contatoDao(): ContatoDao
}