package com.mtu.sd3.bookoffriends.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mtu.sd3.bookoffriends.FormViewModel
import com.mtu.sd3.bookoffriends.Screen
import com.mtu.sd3.bookoffriends.entity.Friend
import com.mtu.sd3.bookoffriends.utility.SQLViewmodel

@Composable
fun NewFriendFun(
    navController: NavController,
    viewModel: FormViewModel,
    sqlViewModel: SQLViewmodel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(16.dp) // Space between rows
    ) {
        // Hobbies Input
        Row {
            Text("Hobbies: ", modifier = Modifier.width(100.dp))
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = viewModel.hobbies,
                onValueChange = { viewModel.hobbies = it }
            )
        }

        // Birth Place Input
        Row {
            Text("Birth Place: ", modifier = Modifier.width(100.dp))
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = viewModel.birthplace,
                onValueChange = { viewModel.birthplace = it }
            )
        }

        // Favorite Food Input
        Row {
            Text("Favorite Food: ", modifier = Modifier.width(100.dp))
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = viewModel.favFood,
                onValueChange = { viewModel.favFood = it }
            )
        }

        // Favorite Movie Input
        Row {
            Text("Favorite Movie: ", modifier = Modifier.width(100.dp))
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = viewModel.favMovie,
                onValueChange = { viewModel.favMovie = it }
            )
        }

        // Most Loved Input
        Row {
            Text("Most Loved: ", modifier = Modifier.width(100.dp))
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = viewModel.mostLoved,
                onValueChange = { viewModel.mostLoved = it }
            )
        }

        // Most Hated Input
        Row {
            Text("Most Hated: ", modifier = Modifier.width(100.dp))
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = viewModel.mostHated,
                onValueChange = { viewModel.mostHated = it }
            )
        }
        var friend = Friend(firstName = viewModel.firstName,
            lastName = viewModel.lastName,
            age = viewModel.age,
            height = viewModel.height,
            address = viewModel.address,
            phoneNumber = viewModel.phoneNumber,
            occupation = viewModel.occupation,
            hobbies = viewModel.hobbies,
            birthdate = viewModel.birthdate,
            birthplace = viewModel.birthplace,
            favFood = viewModel.favFood,
            favMovie = viewModel.favMovie,
            mostLoved = viewModel.mostLoved,
            mostHated = viewModel.mostHated,
            messageToOwner = viewModel.messageToOwner)
        Button(onClick = {
            Log.d("NewFriendFun", "Friend: $friend")

            sqlViewModel.insertFriend(
                friend
                )

            navController.navigate(Screen.HomeScreen.route)
        }) { Text(text = "Submit") }
    }
}
