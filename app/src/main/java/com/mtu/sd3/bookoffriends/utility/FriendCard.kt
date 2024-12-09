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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mtu.sd3.bookoffriends.Screen
import com.mtu.sd3.bookoffriends.entity.FriendLite

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
    val age = determineAge(friend.birthdate)
    val isBirthday = determineBirthday(friend.birthdate)
    Card(
        onClick = { navController.navigate(Screen.FriendDetails.createRoute(friend.id)) },
        Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (isBirthday) {
                androidx.compose.ui.graphics.Color.Green
            } else {
                androidx.compose.ui.graphics.Color.DarkGray
            },
            contentColor = androidx.compose.ui.graphics.Color.White

        )
    ) {
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
                    Text(text = "Age: $age")
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

fun determineAge(birthDate: String): Int {
    val currentYear = java.time.Year.now().value
    val birthYear = birthDate.substringAfterLast("/").toInt()
    return currentYear - birthYear
}

fun determineBirthday(birthDate: String): Boolean {
    val currentMonth = java.time.LocalDate.now().monthValue
    val currentDay = java.time.LocalDate.now().dayOfMonth
    val birthDay = birthDate.substringBefore("/").toInt()
    val birthMonth = birthDate.substringAfter("/").substringBefore("/").toInt()
    return (currentMonth == birthMonth && currentDay == birthDay)

}