package com.mtu.sd3.bookoffriends.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.mtu.sd3.bookoffriends.utility.SQLViewmodel

@Composable
fun FriendDetails(sqlViewmodel: SQLViewmodel, id: Int) {
    sqlViewmodel.getFriendById(id)
    val friend = sqlViewmodel.friend.collectAsState().value
    println(friend)
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState(0)),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "First Name: ${friend?.firstName ?:"placeholder"}")
        Text(text = "Last Name: ${friend?.lastName ?:"placeholder"}")
        Text(text = "Age: ${friend?.age?:"placeholder"}")
        Text(text = "Height: ${friend?.height?:"placeholder"}")
        Text(text = "Address: ${friend?.address?:"placeholder"}")
        Text(text = "Phone Number: ${friend?.phoneNumber?:"placeholder"}")
        Text(text = "Occupation: ${friend?.occupation?:"placeholder"}")
        Text(text = "Hobbies: ${friend?.hobbies?:"placeholder"}")
        Text(text = "Birthdate: ${friend?.birthdate?:"placeholder"}")
        Text(text = "Birthplace: ${friend?.birthplace?:"placeholder"}")
        Text(text = "Favorite Food: ${friend?.favFood?:"placeholder"}")
        Text(text = "Favorite Movie: ${friend?.favMovie?:"placeholder"}")
        Text(text = "Most Loved: ${friend?.mostLoved?:"placeholder"}")
        Text(text = "Most Hated: ${friend?.mostHated?:"placeholder"}")
        Text(text = "Message to Owner: ${friend?.messageToOwner?:"placeholder"}")

    }
}
