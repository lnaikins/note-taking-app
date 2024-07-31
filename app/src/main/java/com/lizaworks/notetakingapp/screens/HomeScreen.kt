package com.lizaworks.notetakingapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
//import com.lizaworks.notetakingapp.Note
import com.lizaworks.notetakingapp.R
import com.lizaworks.notetakingapp.database.Note
import com.lizaworks.notetakingapp.ui.theme.NoteTakingAppTheme
import com.lizaworks.notetakingapp.viewmodels.HomeScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun Homepage(openTypeNoteScreen:()->Unit) {
    val homeScreenViewModel :HomeScreenViewModel = hiltViewModel()
    val notes by homeScreenViewModel.notes.collectAsState(initial = emptyList())
    Column(
        modifier = Modifier
            .padding(vertical = 42.dp, horizontal = 8.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        AppTitle()
        StaggeredGrid(notes = notes , modifier = Modifier.weight(1f), openTypeNoteScreen = openTypeNoteScreen )
    }
}

@Composable
private fun StaggeredGrid(notes: List<Note>,modifier: Modifier, openTypeNoteScreen: () -> Unit) {
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalItemSpacing = 16.dp
    ) {
        item {
            Column(
                modifier = Modifier
                    .clickable(onClick = openTypeNoteScreen)
                    .background(
                        color = Color(0xFFB9E6FE),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .size(160.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.plus_button),
                    contentDescription = "add note"
                )
                Text("New note", color = Color(0xFF026AA2))
            }
        }

        items(notes) { model ->
            NoteItem(note = model, modifier = Modifier)
        }

    }
}

@Composable
private fun AppTitle() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column {
            Text("Note.d", fontSize = 24.sp, fontWeight = FontWeight.W500)
            Text(
                "Enjoy note taking with friends",
                fontSize = 12.sp,
                fontWeight = FontWeight.W500,
                color = Color(0xFF667085)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.profile_pic),
            contentDescription = "profile pic",
            modifier = Modifier.size(50.dp),
            contentScale = ContentScale.Crop
        )
    }
}


@Composable
fun NoteItem(note: Note, modifier: Modifier) {
    Column(
        modifier = modifier
            .background(color = Color(0xFFE4E7EC), shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Text(text = note.title, fontWeight = FontWeight.W500, fontSize = 18.sp)

        Text(text = note.content, fontSize = 12.sp, fontWeight = FontWeight.W400, color = Color(0xFF667085))
        if (note.image != null) {
            Image(painter = painterResource(id = note.image), contentDescription = "")
        }
        Row(verticalAlignment = Alignment.CenterVertically){
//        Text(text = note.date, color = Color(0xFF101828), fontSize = 12.sp, fontWeight = FontWeight.W400)
            Spacer(modifier = Modifier.weight(1f))
            if (note.locked) {
                Image(painter = painterResource(id = R.drawable.padlock), contentDescription = "", modifier = Modifier.size(12.dp))
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun HomepagePreview() {
    NoteTakingAppTheme {
        Homepage(openTypeNoteScreen = {})
    }
}

