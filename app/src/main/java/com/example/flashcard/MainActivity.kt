package com.example.flashcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flashcard.ui.theme.FlashCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlashCardTheme {
                val viewModel: FlashcardViewModel = viewModel()
                FlashcardScreen(
                    viewModel,
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets(
                            left = WindowInsets.systemBars.asPaddingValues().calculateLeftPadding(LayoutDirection.Ltr),
                            right = WindowInsets.systemBars.asPaddingValues().calculateRightPadding(LayoutDirection.Ltr),
                            top = WindowInsets.safeDrawing.asPaddingValues().calculateTopPadding(),
                            bottom = WindowInsets.safeDrawing.asPaddingValues().calculateBottomPadding()
                        )))
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FlashCardTheme {

        FlashcardScreen(
            viewModel = viewModel(),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    }
}