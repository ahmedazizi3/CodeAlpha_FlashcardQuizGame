package azizi.ahmed.flash.packages.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import azizi.ahmed.flash.packages.components.Flashcard
import azizi.ahmed.flash.packages.components.NextButton
import azizi.ahmed.flash.packages.components.PreviousButton
import azizi.ahmed.flash.packages.components.ShowAnswerButton

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToAddScreen: () -> Unit = {},
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navigateToAddScreen
                },
                shape = CircleShape,
                containerColor = Color(0xFF1AA3E5),
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) {flashPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = modifier.height(50.dp))
            Text(
                text = "Flash",
                color = Color(0xFF1AA3E5),
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                fontStyle = FontStyle.Italic,
                modifier = modifier.padding(flashPadding)
            )

            Spacer(modifier = modifier.height(100.dp))

            Flashcard()

            Spacer(modifier = modifier.height(16.dp))

            ShowAnswerButton()

            Spacer(modifier = modifier.height(32.dp))

            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                PreviousButton {  }

                NextButton {  }
            }
        }
    }

}