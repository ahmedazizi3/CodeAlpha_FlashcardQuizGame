package azizi.ahmed.flash.packages.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun FlashcardForm(
    modifier: Modifier = Modifier,
    question: String = "What is the capital of Algeria?",
    answer: String = "Algiers",
    flipped: Boolean = false,
    editNote: () -> Unit = {},
    deleteNote: () -> Unit = {}
) {
    val rotation by animateFloatAsState(
        targetValue = if (flipped) 180f else 0f,
        animationSpec = tween(
            durationMillis = if (flipped) 1000 else 0
        ),
        label = "CardFlipRotation"
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(8.dp)
            .testTag("flashcardForm"),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .testTag("flashcardCard")
                .graphicsLayer {
                    rotationY = rotation
                    cameraDistance = 12 * density
                },
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 20.dp)
        ) {
            if (rotation <= 90f) {
                CardFront(
                    question = question,
                    deleteNote = deleteNote,
                    editNote = editNote
                )
            } else {
                CardBack(answer = answer)
            }
        }
    }
}




