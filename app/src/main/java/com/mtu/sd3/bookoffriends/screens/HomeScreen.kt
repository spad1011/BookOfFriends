package com.mtu.sd3.bookoffriends.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mtu.sd3.bookoffriends.Screen


data class InputHandler(val message: String, val isError: Boolean)

fun inputCheck(input: String): InputHandler {
    if (input.isBlank()) {
        return InputHandler("Enter a value", true)
    } else if (input == "p") {
        return InputHandler("", false)
    }
    return InputHandler("Wrong input", true)
}

@Composable
fun HomeScreen(navController: NavController) {
    var passwordInput by remember { mutableStateOf("") }
    var inputHandler by remember { mutableStateOf(InputHandler("", false)) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(32.dp))
        Text(
            text = "MY BOOK\nOF FRIENDS",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(16.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 50.sp,
            textAlign = TextAlign.Center,
            lineHeight = 60.sp,
        )
        Spacer(Modifier.height(200.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Transparent, shape = CutCornerShape(4.dp))
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = CutCornerShape(4.dp)
                )
                .padding(horizontal = 16.dp), contentAlignment = Alignment.TopStart
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 64.dp)
                        .padding(bottom = 16.dp, top = 32.dp),
                    value = passwordInput,
                    onValueChange = { passwordInput = it },
                    label = { Text("Input") },
                    isError = inputHandler.isError
                )
                if (inputHandler.isError) {
                    Text(
                        text = inputHandler.message,
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyMedium,

                        )
                }

                Button(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 64.dp), onClick = {
                    inputHandler = inputCheck(passwordInput)
                    if (!inputHandler.isError)
                        navController.navigate(Screen.FindFriend.route)
                }) { Text("Submit magic word") }
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
        Text("or", Modifier.padding(top = 16.dp), style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(150.dp))
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 48.dp)
            .height(60.dp),
            onClick = { navController.navigate(Screen.NewFriendInfos.route) }) {
            Text(
                "Make a new friend",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }

}