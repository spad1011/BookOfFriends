package com.mtu.sd3.bookoffriends.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mtu.sd3.bookoffriends.Screen
import com.mtu.sd3.bookoffriends.utility.FriendCardList
import com.mtu.sd3.bookoffriends.utility.SQLViewmodel


@Composable
fun FindFriend(navController: NavController, sqlViewModel: SQLViewmodel) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var birthYear by remember { mutableStateOf("") }
    var friends = sqlViewModel.friends.collectAsState().value
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.TopStart

        ) {
            Column(
                Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(Modifier.fillMaxWidth()) {
                    Text("First name:")
                    TextField(value = firstName, onValueChange = { firstName = it })
                }
                Row(Modifier.fillMaxWidth()) {
                    Text("Last name:")
                    TextField(value = lastName, onValueChange = { lastName = it })
                }
                Row(Modifier.fillMaxWidth()) {
                    Text("Year of birth:")
                    TextField(value = birthYear, onValueChange = { birthYear = it })
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    Button(onClick = {
                        Log.d(
                            "FindFriend",
                            "searching with inputs: $firstName, $lastName, $birthYear"
                        )
                        sqlViewModel.getAllLite(
                            if (firstName.isBlank()) null else firstName,
                            if (lastName.isBlank()) null else lastName,
                            if (birthYear.isBlank()) null else birthYear
                        )
                    }) {
                        Text(text = "Search")
                    }
                    Button(onClick = { navController.navigate(Screen.HomeScreen.route) }) {
                        Text(text = "Go back")
                    }
                }
            }
        }
        if (friends.isEmpty()) Text("No friends found")
        else {
            Log.d("FindFriend: entered else block:", "friends: $friends")
            FriendCardList(friends, navController)
        }


    }
    //list of Cards here
}
