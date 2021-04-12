package com.example.roomexample.ui.main

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contato (
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo val name: String,
    @ColumnInfo val telefone:Int
)