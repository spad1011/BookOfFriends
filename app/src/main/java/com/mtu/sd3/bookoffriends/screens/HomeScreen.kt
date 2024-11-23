package com.mtu.sd3.bookoffriends.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
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
    var passwordinput by remember { mutableStateOf("") }
    var inputHandler by remember { mutableStateOf(InputHandler("", false)) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "MY BOOK OF FRIENDS",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 64.dp),
            value = passwordinput,
            onValueChange = { passwordinput = it },
            label = { Text("Input") },
            isError = inputHandler.isError
        )
        if (inputHandler.isError) {
            Text(
                text = inputHandler.message,
                color = Color.Red,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 64.dp), onClick = {
            inputHandler = inputCheck(passwordinput)
            if (!inputHandler.isError)
                navController.navigate(Screen.FindFriend.route)
        }) { Text("Submit magic word") }
        Text("or", style = MaterialTheme.typography.titleMedium)
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 64.dp),
            onClick = { navController.navigate(Screen.NewFriendInfos.route) }) {
            Text(
                "Make a new friend",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

}