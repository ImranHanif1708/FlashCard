package com.example.flashcard

import androidx.lifecycle.ViewModel
import com.example.flashcard.model.DataSource
import com.example.flashcard.model.Flashcard
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FlashcardViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(FlashCardUiState())
    val uiState: StateFlow<FlashCardUiState> = _uiState.asStateFlow()

    init {
        _uiState.value = _uiState.value.copy(
            cards = DataSource.flashcards
        )
    }

    fun onToggleAnswer() {
        _uiState.value = _uiState.value.copy(
            showAnswer = !_uiState.value.showAnswer
        )
    }

    fun nextCard() {
        val cards = _uiState.value.cards
        val nextIndex = if (_uiState.value.currentIndex < cards.size - 1) _uiState.value.currentIndex + 1 else 0
        _uiState.value = _uiState.value.copy(
            currentIndex = nextIndex,
            showAnswer = false
        )
    }

    fun prevCard() {
        val cards = _uiState.value.cards
        val prevIndex = if (_uiState.value.currentIndex > 0) _uiState.value.currentIndex - 1 else cards.size - 1
        _uiState.value = _uiState.value.copy(
            currentIndex = prevIndex,
            showAnswer = false
        )
    }

    fun addCard(question: String, answer: String) {
        val newCard = _uiState.value.cards + Flashcard(question, answer)
        _uiState.value = uiState.value.copy(
            cards = newCard,
            currentIndex = newCard.size - 1
        )
    }

    fun editCard(question: String, answer: String) {
        val newCard = _uiState.value.cards.toMutableList()
        val currentIndex = _uiState.value.currentIndex
        if(currentIndex in newCard.indices) {
            newCard[currentIndex] = Flashcard(question, answer)
        } else {
            // If currentIndex is out of bounds, we can add a new card
            newCard.add(Flashcard(question, answer))
        }
        _uiState.value = uiState.value.copy(
            cards = newCard,
            showAnswer = false
        )
    }

    fun deleteCard() {
        val cards = _uiState.value.cards.toMutableList()
        val currentIndex = _uiState.value.currentIndex
        if (cards.isNotEmpty() && currentIndex in cards.indices) {
            cards.removeAt(currentIndex)
            val newIndex = if (cards.isEmpty()) 0 else currentIndex.coerceAtMost(cards.size - 1)
            _uiState.value = uiState.value.copy(
                cards = cards,
                currentIndex = newIndex,
                showAnswer = false)
        }
    }

}
data class FlashCardUiState(
    val cards: List<Flashcard> = emptyList(),
    val currentIndex: Int = 0,
    val showAnswer: Boolean = false
)
