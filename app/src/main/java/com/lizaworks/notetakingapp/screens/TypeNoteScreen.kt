package com.lizaworks.notetakingapp.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lizaworks.notetakingapp.R
import com.lizaworks.notetakingapp.ui.theme.NoteTakingAppTheme

@Composable
fun TypeNoteScreen(onBackClicked: ()->Unit) {
    Column(
        modifier = Modifier
            .padding(vertical = 42.dp, horizontal = 16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        TopAppBar(onBackClicked = onBackClicked)
        NoteArea()
    }
}


@Composable
private fun NoteArea() {
    var title by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }
    Column {
        TextField(
            value = title,
            onValueChange = { title = it },
            textStyle = MaterialTheme.typography.headlineMedium,
            placeholder = {
                Text(
                    "Enter title of note...",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.W500,
                    color = Color(0xFFE4E7EC)
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
        )
        TextField(
            value = text,
            onValueChange = { text = it },
            textStyle = MaterialTheme.typography.bodyLarge,
            placeholder = {
                Text(
                    "This is where your note will be. It’ll be housed here. You’ll save your note here. Type your memories here. Write down your thoughts.",
                    color = Color(0xFFE4E7EC),
                    fontSize = 18.sp, fontWeight = FontWeight.W400
                );

            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.weight(1f),
        )
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.darkmode_icon),
                    contentDescription = "dark mode"
                )
                SaveButton(enabled = title.isNotEmpty() && text.isNotEmpty()) {

                }
            }
        }
    }
}

@Composable
private fun TopAppBar(onBackClicked: ()->Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        IconButton(onClick =  onBackClicked) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_left),
                contentDescription = "arrow left"
            )
        }
        Text("Back")
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.picture_icon),
            contentDescription = "picture"
        )
        Image(painter = painterResource(id = R.drawable.note_icon), contentDescription = "note")
        Image(painter = painterResource(id = R.drawable.text_icon), contentDescription = "text")
    }
}

@Composable
private fun SaveButton(enabled: Boolean, onClicked: () -> Unit) {
    val backgroundColor by animateColorAsState(
        targetValue = if (enabled) Color.Black else Color(0xFFD0D5DD),
        label = ""
    )
    Box(
        modifier = Modifier
            .clip(shape = CircleShape)
            .clickable(enabled = enabled, onClick = onClicked)
            .size(72.dp)
            .background(color = backgroundColor, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(id = R.drawable.ic_save), contentDescription = "save icon")
    }
}

@Preview(showBackground = true)
@Composable
fun TypeNotePreview() {
    NoteTakingAppTheme {
        TypeNoteScreen(onBackClicked = {})
    }
}