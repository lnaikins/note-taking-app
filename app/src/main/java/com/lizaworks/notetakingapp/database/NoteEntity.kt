package com.lizaworks.notetakingapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val title: String,
    val content: String,
//    val date: String,
    val image: Int? = null,
    val locked : Boolean = false
)