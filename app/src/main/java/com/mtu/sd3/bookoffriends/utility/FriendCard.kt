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
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                Color(0xff388e3c)
            } else {
                Color(0xfffff3e0)
            },
            contentColor = Color.White

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
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = AbsoluteAlignment.Left
                ) {
                    Text(text = "Name: ${friend.firstName} ${friend.lastName}",color =  Color.Black, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text(text = "Age: ${if (age == -1) "unknown" else age}", color =  Color.Black, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text(text = "potential extra field",color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }
            }
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(text = "Address: ${friend.address}", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Text(text = "Phone: ${friend.phoneNumber}", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }

        }
    }
}

fun determineAge(birthDate: String): Int {
    val currentYear = java.time.Year.now().value
    val birthYear = birthDate.substringAfterLast("/").toIntOrNull() ?: return -1
    return currentYear - birthYear
}

fun determineBirthday(birthDate: String): Boolean {
    val currentMonth = java.time.LocalDate.now().monthValue
    val currentDay = java.time.LocalDate.now().dayOfMonth
    val birthDay = birthDate
        .substringBefore("/")
        .toIntOrNull() ?: return false
    val birthMonth = birthDate
        .substringAfter("/")
        .substringBefore("/")
        .toIntOrNull() ?: return false
    return (currentMonth == birthMonth && currentDay == birthDay)

}