package com.mtu.sd3.bookoffriends.utility

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mtu.sd3.bookoffriends.Screen
import com.mtu.sd3.bookoffriends.entity.FriendLite
import com.mtu.sd3.bookoffriends.screens.FriendDetails

@Composable
fun FriendCardList(friends: List<FriendLite>, navController: NavController) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(friends) { friend ->
            FriendCard(friend, navController)
        }
    }
}



@Composable
fun FriendCard(friend: FriendLite, navController: NavController) {
    Card(onClick = { navController.navigate(Screen.FriendDetails.createRoute(friend.id)) },Modifier.fillMaxWidth()) {
        Column(Modifier.fillMaxSize()) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(100.dp)
                ) {
                    Text("IMAGE PLACEHOLDER")
                }
                Column(Modifier.fillMaxSize()) {
                    Text(text = "Name: ${friend.firstName} ${friend.lastName}")
                    Text(text = "Age: ${friend.age}")
                    Text(text = "potential extra field")
                }
            }
            Row(Modifier.fillMaxWidth()) {
                Text(text = "Address: ${friend.address}")
                Text(text = "Phone: ${friend.phoneNumber}")
            }

        }
    }
}