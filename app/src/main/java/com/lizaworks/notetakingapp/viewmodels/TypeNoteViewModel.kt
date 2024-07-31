package com.lizaworks.notetakingapp.viewmodels

import android.icu.text.CaseMap.Title
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lizaworks.notetakingapp.database.Note
import com.lizaworks.notetakingapp.database.NoteDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TypeNoteViewModel @Inject constructor(val noteDao: NoteDao): ViewModel(){
    fun saveNote(title: String, content: String){
        val note = Note(title = title, content = content)
        viewModelScope.launch(Dispatchers.IO){
            noteDao.saveNote(note)
        }
    }
}