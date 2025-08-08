package azizi.ahmed.flash.packages.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import azizi.ahmed.flash.packages.components.*

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.hilt.navigation.compose.hiltViewModel
import azizi.ahmed.flash.packages.model.Flashcard
import azizi.ahmed.flash.packages.viewModel.FlashViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToAddScreen: () -> Unit = {},
    viewModel: FlashViewModel = hiltViewModel()
) {
    val flashcards by viewModel.flashcardsList.collectAsState()

    LaunchedEffect(Unit) {
        if (flashcards.isEmpty()) {
            viewModel.addFlashcard(
                Flashcard(
                    flashcardQuestion = "What is the capital of Algeria?",
                    flashcardAnswer = "Algiers"
                )
            )
            viewModel.addFlashcard(
                Flashcard(
                    flashcardQuestion = "What is the largest country in the world?",
                    flashcardAnswer = "Russia"
                )
            )
            viewModel.addFlashcard(
                Flashcard(
                    flashcardQuestion = "What is the smallest country in the world?",
                    flashcardAnswer = "Vatican City"
                )
            )
        }
    }
    val currentIndex by viewModel.currentIndex.collectAsState()
    var flipped by remember { mutableStateOf(false) }

    // Reset flip when card changes
    LaunchedEffect(currentIndex) {
        flipped = false
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToAddScreen,
                shape = CircleShape,
                containerColor = Color(0xFF1AA3E5),
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { flashPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "Flash",
                color = Color(0xFF1AA3E5),
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                fontStyle = FontStyle.Italic,
                modifier = modifier.padding(flashPadding)
            )

            Spacer(modifier = Modifier.height(100.dp))

            if (flashcards.isNotEmpty()) {
                AnimatedContent(
                    targetState = currentIndex,
                    transitionSpec = {
                        if (targetState > initialState) {
                            slideInHorizontally(
                                initialOffsetX = { it },
                                animationSpec = tween(300)
                            ) togetherWith slideOutHorizontally(
                                targetOffsetX = { -it },
                                animationSpec = tween(300)
                            )
                        } else {
                            slideInHorizontally(
                                initialOffsetX = { -it },
                                animationSpec = tween(300)
                            ) togetherWith slideOutHorizontally(
                                targetOffsetX = { it },
                                animationSpec = tween(300)
                            )
                        }
                    },
                    label = "CardSlideAnimation"
                ) { index ->
                    val card = flashcards[index]
                    FlashcardForm(
                        question = card.flashcardQuestion,
                        answer = card.flashcardAnswer,
                        flipped = flipped
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            ShowOrHideAnswerButton(
                flipped = flipped,
                showOrHideAnswer = {
                    flipped = !flipped
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (currentIndex > 0) {
                    PreviousButton { viewModel.previousCard() }
                } else {
                    Spacer(modifier = Modifier.width(150.dp))
                }

                if (currentIndex < flashcards.size - 1) {
                    NextButton { viewModel.nextCard() }
                } else {
                    Spacer(modifier = Modifier.width(150.dp))
                }
            }
        }
    }
}

