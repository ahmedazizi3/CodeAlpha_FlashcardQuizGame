package azizi.ahmed.flash.packages.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ShowOrHideAnswerButton(
    modifier: Modifier = Modifier,
    flipped: Boolean = false,
    showOrHideAnswer: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.White)
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp)
                .testTag("btnToggleAnswer"),
            onClick = showOrHideAnswer,
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1AA3E5))
        ) {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color(0xFF1AA3E5)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (flipped) "Hide Answer" else "Show Answer",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
