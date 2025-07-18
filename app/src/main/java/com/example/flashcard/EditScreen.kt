package com.example.flashcard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun EditSection(
    onAdd: (String, String) -> Unit,
    onEdit: (String, String) -> Unit,
    onDelete: () -> Unit
) {
    var question by remember { mutableStateOf("") }
    var answer by remember { mutableStateOf("") }

    Column {
        BasicTextField(
            value = question,
            onValueChange = { question = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.White),
            decorationBox = { innerTextField ->
                Box(Modifier.padding(8.dp)) {
                    if (question.isEmpty()) Text("Enter Question", color = Color.Gray)
                    innerTextField()
                }
            }
        )

        BasicTextField(
            value = answer,
            onValueChange = { answer = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.White),
            decorationBox = { innerTextField ->
                Box(Modifier.padding(8.dp)) {
                    if (answer.isEmpty()) Text("Enter Answer", color = Color.Gray)
                    innerTextField()
                }
            }
        )

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = {
                if (question.isNotBlank() && answer.isNotBlank()) {
                    onAdd(question, answer)
                    question = ""
                    answer = ""
                }
            }) { Text("Add") }

            Button(onClick = {
                if (question.isNotBlank() && answer.isNotBlank()) {
                    onEdit(question, answer)
                    question = ""
                    answer = ""
                }
            }) { Text("Edit") }

            Button(onClick = onDelete) { Text("Delete") }
        }
    }
}