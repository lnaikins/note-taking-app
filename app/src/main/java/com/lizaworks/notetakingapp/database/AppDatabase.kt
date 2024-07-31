package com.lizaworks.notetakingapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class] , version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao

}

