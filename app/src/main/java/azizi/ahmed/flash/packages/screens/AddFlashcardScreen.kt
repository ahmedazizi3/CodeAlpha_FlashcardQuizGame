package azizi.ahmed.flash.packages.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddFlashcardScreen(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    addNote: () -> Unit = {},
    question: MutableState<String> = mutableStateOf(""),
    answer: MutableState<String> = mutableStateOf("")
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        AlertDialog(
            modifier = modifier
                .fillMaxWidth(),
            containerColor = Color.White,
            onDismissRequest = {
                onDismissRequest()
            },
            title = {
                Text(
                    text = "Add Flashcard",
                    color = Color(0xFF1AA3E5),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                )
            },
            text = {
                Column(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Enter the question",
                        color = Color(0xFF1AA3E5),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Spacer(modifier = modifier.height(4.dp))
                    OutlinedTextField(
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal
                        ),
                        value = question.value,
                        onValueChange = { qst ->
                            question.value = qst
                        },
                        modifier = modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF1AA3E5),
                            unfocusedBorderColor = Color(0xFF1AA3E5),
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            cursorColor = Color(0xFF1AA3E5),
                            selectionColors = TextSelectionColors(
                                handleColor = Color(0xFF1AA3E5),
                                backgroundColor = Color.White
                            ),
                            errorTextColor = Color.Red,
                            errorBorderColor = Color.Red,
                            focusedTextColor = Color(0xFF1AA3E5),
                            unfocusedTextColor = Color(0xFF1AA3E5),
                            disabledTextColor = Color(0xFF1AA3E5),
                            disabledBorderColor = Color(0xFF1AA3E5),
                            disabledContainerColor = Color.White,
                        )
                    )

                    Spacer(modifier = modifier.height(24.dp))


                    Text(
                        text = "Enter the answer",
                        color = Color(0xFF1AA3E5),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Spacer(modifier = modifier.height(4.dp))
                    OutlinedTextField(
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal
                        ),
                        value = answer.value,
                        onValueChange = { ans ->
                            answer.value = ans
                        },
                        modifier = modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF1AA3E5),
                            unfocusedBorderColor = Color(0xFF1AA3E5),
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            cursorColor = Color(0xFF1AA3E5),
                            selectionColors = TextSelectionColors(
                                handleColor = Color(0xFF1AA3E5),
                                backgroundColor = Color.White
                            ),
                            errorTextColor = Color.Red,
                            errorBorderColor = Color.Red,
                            focusedTextColor = Color(0xFF1AA3E5),
                            unfocusedTextColor = Color(0xFF1AA3E5),
                            disabledTextColor = Color(0xFF1AA3E5),
                            disabledBorderColor = Color(0xFF1AA3E5),
                            disabledContainerColor = Color.White,
                        )
                    )
                }
            },
            confirmButton = {
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            addNote()
                            question.value = ""
                            answer.value = ""
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color(0xFF1AA3E5)
                        )
                    ) {
                        Text(text = "Add")
                    }

                    Button(
                        onClick = {
                            onDismissRequest()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color(0xFF1AA3E5)
                        )
                    ) {
                        Text(text = "Cancel")
                    }
                }
            }
        )
    }
}