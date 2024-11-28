package com.mtu.sd3.bookoffriends.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mtu.sd3.bookoffriends.FormViewModel
import com.mtu.sd3.bookoffriends.entity.Friend
import com.mtu.sd3.bookoffriends.utility.SQLViewmodel

@Composable
fun FriendDetails(sqlViewmodel: SQLViewmodel, id: Int) {

    val friend = sqlViewmodel.getFriendById(id)
    println(friend)
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState(0)),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "First Name: ${friend.firstName}")
        Text(text = "Last Name: ${friend.lastName}")
        Text(text = "Age: ${friend.age}")
        Text(text = "Height: ${friend.height}")
        Text(text = "Address: ${friend.address}")
        Text(text = "Phone Number: ${friend.phoneNumber}")
        Text(text = "Occupation: ${friend.occupation}")
        Text(text = "Hobbies: ${friend.hobbies}")
        Text(text = "Birthdate: ${friend.birthdate}")
        Text(text = "Birthplace: ${friend.birthplace}")
        Text(text = "Favorite Food: ${friend.favFood}")
        Text(text = "Favorite Movie: ${friend.favMovie}")
        Text(text = "Most Loved: ${friend.mostLoved}")
        Text(text = "Most Hated: ${friend.mostHated}")
        Text(text = "Message to Owner: ${friend.messageToOwner}")

    }
}
