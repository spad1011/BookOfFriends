package com.mtu.sd3.bookoffriends.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mtu.sd3.bookoffriends.Screen
import com.mtu.sd3.bookoffriends.entity.Friend
import com.mtu.sd3.bookoffriends.utility.FormViewModel
import com.mtu.sd3.bookoffriends.utility.InsertState
import com.mtu.sd3.bookoffriends.utility.SQLViewModel

@Composable
fun NewFriendFun(
    navController: NavController,
    formViewModel: FormViewModel,
    sqlViewModel: SQLViewModel
) {
    val insertionResult = sqlViewModel.insertionResult.collectAsState().value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff37474f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            // Hobbies Input
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xff008000),
                                Color(0xff023020),//Dark green
                            ), start = Offset(50F, 0F), end = Offset(1000F, 0F)
                        ), shape = RoundedCornerShape(8.dp)
                    ), verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Hobbies: ",
                    modifier = Modifier.width(100.dp),
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                OutlinedTextField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp, top = 8.dp, bottom = 8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White.copy(alpha = 0.1F))
                        .border(
                            width = 2.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    value = formViewModel.hobbies,
                    onValueChange = { formViewModel.hobbies = it }, textStyle = TextStyle(
                        Color.White,
                        fontSize = 16.sp
                    )
                )
            }

            // Birth Place Input
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xff191970),//Midnight blue
                                Color(0xff1434A4), //Egyptian blue
                            ), start = Offset(50F, 0F), end = Offset(1000F, 0F)
                        ), shape = RoundedCornerShape(8.dp)
                    ), verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White.copy(alpha = 0.1F))
                        .border(
                            width = 2.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    value = formViewModel.birthplace,
                    onValueChange = { formViewModel.birthplace = it },
                    textStyle = TextStyle(
                        Color.White,
                        fontSize = 16.sp
                    )
                )
                Text(
                    " :Birth Place",
                    modifier = Modifier.width(100.dp),
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }

            // Favorite Food Input
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xffa21212),
                                Color(0xff4A0404),//dark red
                            ), start = Offset(0.1F, 0F), end = Offset(Float.POSITIVE_INFINITY, 0F)
                        ), shape = RoundedCornerShape(8.dp)
                    ), verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Favorite Food: ",
                    modifier = Modifier.width(100.dp),
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                OutlinedTextField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp, top = 8.dp, bottom = 8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White.copy(alpha = 0.1F))
                        .border(
                            width = 2.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    value = formViewModel.favFood,
                    onValueChange = { formViewModel.favFood = it },
                    textStyle = TextStyle(
                        Color.White,
                        fontSize = 16.sp
                    )
                )
            }

            // Favorite Movie Input
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xff8b8000),
                                Color(0xffFFBF00)
                            ), start = Offset(1F, 0F), end = Offset(Float.POSITIVE_INFINITY, 0F)
                        ), shape = RoundedCornerShape(8.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White.copy(alpha = 0.1F))
                        .border(
                            width = 2.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    value = formViewModel.favMovie,
                    onValueChange = { formViewModel.favMovie = it },
                    textStyle = TextStyle(
                        Color.White,
                        fontSize = 16.sp
                    )
                )
                Text(
                    " :Favorite Movie",
                    modifier = Modifier.width(100.dp),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )

            }

            // Most Loved Input
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xffAA336A),
                                Color(0xffde3163),
                            ), start = Offset(0F, 0F), end = Offset(Float.POSITIVE_INFINITY, 0F)
                        ), shape = RoundedCornerShape(8.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Most Loved: ",
                    modifier = Modifier.width(100.dp),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,

                    )
                OutlinedTextField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp, top = 8.dp, bottom = 8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White.copy(alpha = 0.1F))
                        .border(
                            width = 2.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    value = formViewModel.mostLoved,
                    onValueChange = { formViewModel.mostLoved = it }, textStyle = TextStyle(
                        Color.White,
                        fontSize = 16.sp
                    )
                )
            }

            // Most Hated Input
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color.Black,
                                Color(0xff353935)
                            ), start = Offset(0.2F, 0F), end = Offset(Float.POSITIVE_INFINITY, 0F)
                        ), shape = RoundedCornerShape(8.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White.copy(alpha = 0.1F))
                        .border(
                            width = 2.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    value = formViewModel.mostHated,
                    onValueChange = { formViewModel.mostHated = it }, textStyle = TextStyle(
                        Color.White,
                        fontSize = 16.sp
                    )
                )
                Text(
                    " :Most Hated",
                    modifier = Modifier.width(100.dp),
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xff795548),
                                Color(0xff3e2723)
                            ), start = Offset(0.2F, 0F), end = Offset(Float.POSITIVE_INFINITY, 0F)
                        ), shape = RoundedCornerShape(8.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    "Message to Owner: ",
                    modifier = Modifier.width(100.dp),
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                OutlinedTextField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White.copy(alpha = 0.1F))
                        .border(
                            width = 2.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    value = formViewModel.messageToOwner,
                    onValueChange = { formViewModel.messageToOwner = it }, textStyle = TextStyle(
                        Color.White,
                        fontSize = 16.sp
                    )
                )

            }
            var friend = Friend(
                firstName = formViewModel.firstName,
                lastName = formViewModel.lastName,
                height = formViewModel.height,
                address = formViewModel.address,
                phoneNumber = formViewModel.phoneNumber,
                occupation = formViewModel.occupation,
                hobbies = formViewModel.hobbies,
                birthdate = formViewModel.birthdate,
                birthplace = formViewModel.birthplace,
                favFood = formViewModel.favFood,
                favMovie = formViewModel.favMovie,
                mostLoved = formViewModel.mostLoved,
                mostHated = formViewModel.mostHated,
                messageToOwner = formViewModel.messageToOwner
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Button(
                    onClick = { navController.navigate(Screen.NewFriendInfos.route) },
                    modifier = Modifier
                        .height(60.dp)
                        .width(115.dp)
                        .padding(end = 40.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
                Button(
                    onClick = {
                        Log.d("NewFriendFun", "Friend: $friend")

                        sqlViewModel.insertFriend(
                            friend
                        )

                        navController.navigate(Screen.HomeScreen.route)
                    }, modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth(0.9F)
                ) { Text(text = "Submit", fontSize = 20.sp) }
            }

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .height(40.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            when (insertionResult) {
                is InsertState.Idle -> {
                    Text("Ready to submit", color = Color.White)
                }

                is InsertState.Failure -> {
                    Text(insertionResult.errorMessage, color = Color.Red)
                }

                is InsertState.Loading -> {
                    CircularProgressIndicator()
                }

                is InsertState.Success -> {
                    Text("Insertion successfully", color = Color.Green)
                    sqlViewModel.resetInsertState()
                    formViewModel.resetForm()
                    navController.navigate(Screen.HomeScreen.route)
                }
            }
        }
    }
}
