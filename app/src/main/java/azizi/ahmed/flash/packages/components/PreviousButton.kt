package azizi.ahmed.flash.packages.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PreviousButton(
    modifier: Modifier = Modifier,
    navigateBackToPreviousCard: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .width(150.dp)
            .height(70.dp)
            .background(Color.White)
    ){
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp),
            onClick = navigateBackToPreviousCard,
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF1AA3E5)
            )
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Color(0xFF1AA3E5)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Previous",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}