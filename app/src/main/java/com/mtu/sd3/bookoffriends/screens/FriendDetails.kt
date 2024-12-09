package com.mtu.sd3.bookoffriends.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mtu.sd3.bookoffriends.Screen
import com.mtu.sd3.bookoffriends.utility.SQLViewModel

@Composable
fun FriendDetails(sqlViewmodel: SQLViewModel, id: Int, navController: NavController) {
    sqlViewmodel.getFriendById(id)
    val friend = sqlViewmodel.friend.collectAsState().value
    println(friend)
    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xff37474f))
            .verticalScroll(state = rememberScrollState(0)),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(color = Color(0xff90a4ae)),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Details of ${friend?.firstName ?: "ERROR"}",
                Modifier.padding(start = 16.dp, top = 32.dp),
                fontSize = 32.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                color = Color(0xffeeeeee)
            )
            Button(
                onClick = { navController.navigate(Screen.FindFriend.route) },
                Modifier.padding(top = 32.dp)
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        }
        HorizontalDivider(thickness = 5.dp, color = Color.Black)
        Text(
            text = "First Name:\n${friend?.firstName ?: "placeholder"}",
            Modifier.padding(start = 32.dp),
            color = Color(0xffeeeeee),
            fontSize = 24.sp,
            textAlign = TextAlign.Left,
        )
        Text(
            text = "Last Name:\n${friend?.lastName ?: "placeholder"}",
            Modifier.padding(start = 32.dp),
            color = Color(0xffeeeeee),
            fontSize = 24.sp,
            textAlign = TextAlign.Left,
        )
        Text(
            text = "Age:\n${friend?.age ?: "placeholder"}", Modifier.padding(start = 32.dp),
            color = Color(0xffeeeeee),
            fontSize = 24.sp,
            textAlign = TextAlign.Left,
        )
        Text(
            text = "Height:\n${friend?.height ?: "placeholder"}", Modifier.padding(start = 32.dp),
            color = Color(0xffeeeeee),
            fontSize = 24.sp,
            textAlign = TextAlign.Left,
        )
        Text(
            text = "Address:\n${friend?.address ?: "placeholder"}", Modifier.padding(start = 32.dp),
            color = Color(0xffeeeeee),
            fontSize = 24.sp,
            textAlign = TextAlign.Left,
        )
        Text(
            text = "Phone Number:\n${friend?.phoneNumber ?: "placeholder"}",
            Modifier.padding(start = 32.dp),
            color = Color(0xffeeeeee),
            fontSize = 24.sp,
            textAlign = TextAlign.Left,
        )
        Text(
            text = "Occupation:\n${friend?.occupation ?: "placeholder"}",
            Modifier.padding(start = 32.dp),
            color = Color(0xffeeeeee),
            fontSize = 24.sp,
            textAlign = TextAlign.Left,
        )
        Text(
            text = "Hobbies:\n${friend?.hobbies ?: "placeholder"}", Modifier.padding(start = 32.dp),
            color = Color(0xffeeeeee),
            fontSize = 24.sp,
            textAlign = TextAlign.Left,
        )
        Text(
            text = "Birthdate:\n${friend?.birthdate ?: "placeholder"}",
            Modifier.padding(start = 32.dp),
            color = Color(0xffeeeeee),
            fontSize = 24.sp,
            textAlign = TextAlign.Left,
        )
        Text(
            text = "Birthplace:\n${friend?.birthplace ?: "placeholder"}",
            Modifier.padding(start = 32.dp),
            color = Color(0xffeeeeee),
            fontSize = 24.sp,
            textAlign = TextAlign.Left,
        )
        Text(
            text = "Favorite Food:\n${friend?.favFood ?: "placeholder"}",
            Modifier.padding(start = 32.dp),
            color = Color(0xffeeeeee),
            fontSize = 24.sp,
            textAlign = TextAlign.Left,
        )
        Text(
            text = "Favorite Movie:\n${friend?.favMovie ?: "placeholder"}",
            Modifier.padding(start = 32.dp),
            color = Color(0xffeeeeee),
            fontSize = 24.sp,
            textAlign = TextAlign.Left,
        )
        Text(
            text = "Most Loved:\n${friend?.mostLoved ?: "placeholder"}",
            Modifier.padding(start = 32.dp),
            color = Color(0xffeeeeee),
            fontSize = 24.sp,
            textAlign = TextAlign.Left,
        )
        Text(
            text = "Most Hated:\n${friend?.mostHated ?: "placeholder"}",
            Modifier.padding(start = 32.dp),
            color = Color(0xffeeeeee),
            fontSize = 24.sp,
            textAlign = TextAlign.Left,
        )
        Text(
            text = "Message to Owner:\n${friend?.messageToOwner ?: "placeholder"}",
            Modifier.padding(start = 32.dp),
            color = Color(0xffeeeeee),
            fontSize = 24.sp,
            textAlign = TextAlign.Left,
        )

    }
}
