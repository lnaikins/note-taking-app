package com.lizaworks.notetakingapp.viewmodels

import androidx.lifecycle.ViewModel
import com.lizaworks.notetakingapp.database.NoteDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val noteDao: NoteDao) : ViewModel(){
    val notes = noteDao.getAllNotes()
}