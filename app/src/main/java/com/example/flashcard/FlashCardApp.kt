package com.example.flashcard

// FlashcardApp.kt - Jetpack Compose with MVVM

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flashcard.model.Flashcard


@Composable
fun FlashcardScreen(viewModel: FlashcardViewModel, modifier: Modifier) {
    val uiState by viewModel.uiState.collectAsState()
    val currentCard = uiState.cards.getOrNull(uiState.currentIndex)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        if (currentCard != null) {
            FlashcardDisplay(
                card = currentCard,
                showAnswer = uiState.showAnswer,
                onToggle = { viewModel.onToggleAnswer() }
            )
        }

        NavigationControls(
            onPrev = { viewModel.prevCard() },
            onToggle = { viewModel.onToggleAnswer() },
            showAnswer = uiState.showAnswer,
            onNext = { viewModel.nextCard() }
        )

        EditSection(
            onAdd = { q, a -> viewModel.addCard(q, a) },
            onEdit = { q, a -> viewModel.editCard(q, a) },
            onDelete = { viewModel.deleteCard() }
        )
    }
}

// Displays the flashcard
@Composable
fun FlashcardDisplay(card: Flashcard, showAnswer: Boolean, onToggle: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(200.dp)
            .clickable { onToggle() },
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(8.dp), contentAlignment = Alignment.Center) {
            Text(
                text = if (showAnswer) card.answer else card.question,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        }
    }
}

// Controls for navigating flashcards and toggling answer
@Composable
fun NavigationControls(onPrev: () -> Unit, onToggle: () -> Unit, showAnswer: Boolean, onNext: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(onClick = onPrev) { Text("Previous") }
        Button(onClick = onToggle) { Text(if (showAnswer) "Hide Answer" else "Show Answer") }
        Button(onClick = onNext) { Text("Next") }
    }
}

