package azizi.ahmed.flash.packages.screens

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import azizi.ahmed.flash.packages.components.*
import azizi.ahmed.flash.packages.model.Flashcard
import azizi.ahmed.flash.packages.viewModel.FlashViewModel
import java.util.UUID
import androidx.compose.animation.*
import androidx.compose.animation.core.tween

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: FlashViewModel = hiltViewModel()
) {
    val flashcards by viewModel.flashcardsList.collectAsState()
    val currentIndex by viewModel.currentIndex.collectAsState()

    val isAddNoteDialogVisible = remember {
        mutableStateOf(false)
    }
    val isUpdateNoteDialogVisible = remember {
        mutableStateOf(false)
    }
    val isDeleteNoteDialogVisible = remember {
        mutableStateOf(false)
    }

    val question = remember {
        mutableStateOf("")
    }
    val answer = remember {
        mutableStateOf("")
    }
    var editingCardId by remember {
        mutableStateOf<UUID?>(null)
    }

    val context = LocalContext.current

    var flipped by remember {
        mutableStateOf(false)
    }

    // Reset flip when card changes
    LaunchedEffect(currentIndex) {
        flipped = false
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { isAddNoteDialogVisible.value = true },
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
            // Add Note Dialog
            if (isAddNoteDialogVisible.value) {
                AddFlashcardScreen(
                    onDismissRequest = {
                        isAddNoteDialogVisible.value = false
                        question.value = ""
                        answer.value = ""
                    },
                    question = question,
                    answer = answer,
                    addNote = {
                        viewModel.addFlashcard(
                            Flashcard(
                                flashcardQuestion = question.value,
                                flashcardAnswer = answer.value
                            )
                        )
                        isAddNoteDialogVisible.value = false
                        Toast.makeText(context, "Note added", Toast.LENGTH_SHORT).show()
                    }
                )
            }

            // Delete Note Dialog
            if (isDeleteNoteDialogVisible.value) {
                AlertDialog(
                    modifier = modifier.fillMaxWidth(),
                    containerColor = Color.White,
                    onDismissRequest = {
                        isDeleteNoteDialogVisible.value = false
                   },
                    title = {
                        Text(
                            text = "Delete Flashcard",
                            color = Color(0xFF1AA3E5),
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    },
                    text = {
                        Text(
                            text = "Are you sure you want to delete this flashcard?",
                            textAlign = TextAlign.Center,
                            color = Color(0xFF1AA3E5),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    },
                    confirmButton = {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(
                                onClick = {
                                    viewModel.safeDeleteCurrentCard()
                                    isDeleteNoteDialogVisible.value = false
                                    question.value = ""
                                    answer.value = ""
                                    Toast.makeText(context, "Note deleted", Toast.LENGTH_SHORT).show()
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White,
                                    contentColor = Color(0xFF1AA3E5)
                                )
                            ) {
                                Text("Delete")
                            }

                            Button(
                                onClick = {
                                    isDeleteNoteDialogVisible.value = false
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White,
                                    contentColor = Color(0xFF1AA3E5)
                                )
                            ) {
                                Text("Cancel")
                            }
                        }
                    }
                )
            }

            // Update Note Dialog
            if (isUpdateNoteDialogVisible.value) {
                UpdateFlashcardScreen(
                    onDismissRequest = {
                        isUpdateNoteDialogVisible.value = false
                        question.value = ""
                        answer.value = ""
                    },
                    updateNote = {
                        editingCardId?.let {
                            viewModel.updateFlashcard(
                                Flashcard(
                                    flashcardId = it,
                                    flashcardQuestion = question.value,
                                    flashcardAnswer = answer.value
                                )
                            )
                        }
                        isUpdateNoteDialogVisible.value = false
                        Toast.makeText(context, "Note updated", Toast.LENGTH_SHORT).show()
                    },
                    question = question,
                    answer = answer,
                )
            }

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

            // Animated Card Display
            if (flashcards.isNotEmpty()) {
                AnimatedContent(
                    targetState = currentIndex,
                    transitionSpec = {
                        if (targetState > initialState) {
                            slideInHorizontally(
                                initialOffsetX = { fullWidth -> fullWidth },
                                animationSpec = tween(300)
                            ) togetherWith slideOutHorizontally(
                                targetOffsetX = { fullWidth -> -fullWidth },
                                animationSpec = tween(300)
                            )
                        } else {
                            slideInHorizontally(
                                initialOffsetX = { fullWidth -> -fullWidth },
                                animationSpec = tween(300)
                            ) togetherWith slideOutHorizontally(
                                targetOffsetX = { fullWidth -> fullWidth },
                                animationSpec = tween(300)
                            )
                        }
                    },
                    label = "CardSlideAnimation"
                ) { index ->
                    if (index in flashcards.indices) {
                        val card = flashcards[index]
                        FlashcardForm(
                            question = card.flashcardQuestion,
                            answer = card.flashcardAnswer,
                            deleteNote = {
                                isDeleteNoteDialogVisible.value = true
                            },
                            flipped = flipped,
                            editNote = {
                                isUpdateNoteDialogVisible.value = true
                                question.value = card.flashcardQuestion
                                answer.value = card.flashcardAnswer
                                editingCardId = card.flashcardId
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Show / Hide Answer
            if (flashcards.isNotEmpty()) {
                ShowOrHideAnswerButton(
                    flipped = flipped,
                    showOrHideAnswer = {
                        flipped = !flipped
                    }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Navigation Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (currentIndex > 0) {
                    PreviousButton {
                        viewModel.previousCard()
                    }
                } else {
                    Spacer(modifier = Modifier.width(150.dp))
                }

                if (currentIndex < flashcards.size - 1) {
                    NextButton {
                        viewModel.nextCard()
                    }
                } else {
                    Spacer(modifier = Modifier.width(150.dp))
                }
            }
        }
    }
}
