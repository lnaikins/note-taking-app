package com.lizaworks.notetakingapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
//import com.lizaworks.notetakingapp.Note
import com.lizaworks.notetakingapp.R
import com.lizaworks.notetakingapp.ui.theme.NoteTakingAppTheme

@Composable
fun Homepage() {
    Column(
        modifier = Modifier
            .padding(vertical = 42.dp, horizontal = 8.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        AppTitle()
        StaggeredGrid(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun StaggeredGrid(modifier: Modifier) {
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalItemSpacing = 16.dp
    ) {
        val notes = mutableListOf(
            Note(
                title = "Note.d",
                note = "I’m a research-focused UX Designer & Writer. I love working with small and medium-scale businesses to design, build, and launch cutting-e...",
                date = "2024.07.10",
                image = R.drawable.notepic_1,
                locked = R.drawable.padlock
            ),
            Note(
                title = "The job hard",
                note = "For athletes, high altitude produces two contradictory effects on performance...",
                date = "2024.07.11",
            ),
            Note(
                title = "Best day ever",
                note = "Physical space is often conceived in three linear dimensions, although modern physici...",
                date = "2024.07.10",
            ),
            Note(
                title = "Dear designers",
                note = "This is the content of note 2.hdhcjshfkvjhkjvkljflkjshdkgjhewvdgjvwjhuhwijqiljiojiohekjdhjvwjgvjegvyjhgwkjkjkjhkjbhjfdhhh",
                date = "2024.07.11",
                image = R.drawable.notepic_2 ,
                locked = R.drawable.padlock

            ),
            Note(
                title = "Note.d",
                note = "First published 11th June 2015. Revised and updated 23rd June 2022. Everyone should have a space to sit outside in the summer. Ev...",
                date = "2024.07.10",
                image = R.drawable.notepic_1
            ),
            Note(
                title = "The job hard",
                note = "This is the content of note 2.hdhcjshfkvjhkjvkljflkjshdkgjhewvdgjvwjhuhwijqiljiojiohekjdhjvwjgvjegvyjhgwkjkjkjhkjbhjfdhhh",
                date = "2024.07.11",
                image = R.drawable.notepic_2
            ),
        )

        item {
            Column(
                modifier = Modifier
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

        Text(text = note.note, fontSize = 12.sp, fontWeight = FontWeight.W400, color = Color(0xFF667085))
        if (note.image != null) {
            Image(painter = painterResource(id = note.image), contentDescription = "")
        }
        Row(verticalAlignment = Alignment.CenterVertically){
        Text(text = note.date, color = Color(0xFF101828), fontSize = 12.sp, fontWeight = FontWeight.W400)
            Spacer(modifier = Modifier.weight(1f))
            if (note.locked != null) {
                Image(painter = painterResource(id = note.locked), contentDescription = "", modifier = Modifier.size(12.dp))
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun HomepagePreview() {
    NoteTakingAppTheme {
        Homepage()
    }
}


data class Note(
    val title: String,
    val note: String,
    val date: String,
    val image: Int? = null,
    val locked : Int? = null
)