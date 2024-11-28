package com.mtu.sd3.bookoffriends.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mtu.sd3.bookoffriends.entity.FriendLite
import com.mtu.sd3.bookoffriends.utility.FriendCardList
import com.mtu.sd3.bookoffriends.utility.SQLViewmodel


@Composable
fun FindFriend(navController: NavController,sqlViewModel: SQLViewmodel) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var birthYear by remember { mutableStateOf("") }
    var friends: List<FriendLite> = emptyList()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Box(Modifier.fillMaxWidth().height(200.dp)){
            Row(Modifier.fillMaxWidth()) {
                Text("First name:")
                TextField(value = "", onValueChange = {firstName = it})
            }
            Row(Modifier.fillMaxWidth()) {
                Text("Last name:")
                TextField(value = "", onValueChange = {lastName = it})
            }
            Row(Modifier.fillMaxWidth()) {
                Text("Year of birth:")
                TextField(value = "", onValueChange = {birthYear = it})
            }
            Button(onClick = {friends = sqlViewModel.getAllLite(firstName, lastName, birthYear)}){
                Text(text = "Search")
            }
        }
        FriendCardList(friends, navController)
        //list of Cards here
    }
}