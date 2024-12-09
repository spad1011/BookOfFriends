package com.mtu.sd3.bookoffriends.screens

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mtu.sd3.bookoffriends.Screen
import com.mtu.sd3.bookoffriends.utility.DatePickerModal
import com.mtu.sd3.bookoffriends.utility.FormViewModel
import com.mtu.sd3.bookoffriends.utility.convertMillisToDate

fun convertDate(millis: Long?): String {
    return millis?.let {
        convertMillisToDate(it)
    } ?: ""
}

@Composable
fun NewFriendInfos(navController: NavController, formViewModel: FormViewModel) {
    var showDatePicker by remember { mutableStateOf(false) }

    Box(
        Modifier
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
            // First Name Input
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(Color(0xff546e7a), shape = RoundedCornerShape(8.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "First Name: ",
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
                    value = formViewModel.firstName,
                    onValueChange = { formViewModel.firstName = it },
                    isError = !formViewModel.isFirstNameValid,
                    textStyle = TextStyle(
                        Color.White,
                        fontSize = 16.sp
                    )
                )
            }
            if (!formViewModel.isFirstNameValid) {
                Text(
                    text = "A valid name is required",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            // Last Name Input
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(Color(0xff546e7a), shape = RoundedCornerShape(8.dp)),
                verticalAlignment = Alignment.CenterVertically
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
                    value = formViewModel.lastName,
                    onValueChange = { formViewModel.lastName = it },
                    isError = !formViewModel.isLastNameValid,
                    textStyle = TextStyle(
                        Color.White,
                        fontSize = 16.sp
                    )
                )
                Text(
                    " :Last Name", modifier = Modifier.width(100.dp), color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )

            }
            if (!formViewModel.isLastNameValid) {
                Text(
                    text = "A valid lastname is required",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            //Height Input
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(Color(0xff546e7a), shape = RoundedCornerShape(8.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Height (cm):", modifier = Modifier.width(100.dp), color = Color.White,
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
                    value = if (formViewModel.height == "n/a") "" else formViewModel.height,
                    onValueChange = { if (it.toIntOrNull() != null) formViewModel.height = it },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    textStyle = TextStyle(
                        Color.White,
                        fontSize = 16.sp
                    )
                )
            }

            // Address Input
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(Color(0xff546e7a), shape = RoundedCornerShape(8.dp)),
                verticalAlignment = Alignment.CenterVertically
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
                    value = formViewModel.address,
                    onValueChange = { formViewModel.address = it },
                    isError = !formViewModel.isAddressValid,
                    textStyle = TextStyle(
                        Color.White,
                        fontSize = 16.sp
                    )
                )
                Text(
                    " :Address", modifier = Modifier.width(100.dp), color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )

            }
            if (!formViewModel.isAddressValid) {
                if (!formViewModel.isLastNameValid) {
                    Text(
                        text = "A valid Address is required",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            // Phone Number Input
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(Color(0xff546e7a), shape = RoundedCornerShape(8.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Phone Number: ", modifier = Modifier.width(100.dp), color = Color.White,
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
                    value = formViewModel.phoneNumber,
                    onValueChange = { formViewModel.phoneNumber = it },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                    isError = !formViewModel.isPhoneNumberValid,
                    textStyle = TextStyle(
                        Color.White,
                        fontSize = 16.sp
                    )
                )
            }
            if (!formViewModel.isPhoneNumberValid) {
                if (!formViewModel.isLastNameValid) {
                    Text(
                        text = "A valid Number is required",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            // Birth Date Input with Date Picker
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(Color(0xff546e7a), shape = RoundedCornerShape(8.dp)),
                verticalAlignment = Alignment.CenterVertically
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
                    value = formViewModel.birthdate,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { showDatePicker = !showDatePicker }) {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "Select date of birth"
                            )
                        }
                    },
                    isError = !formViewModel.isBirthdateValid,
                    textStyle = TextStyle(
                        Color.White,
                        fontSize = 16.sp
                    )
                )
                Text(
                    " :Birthdate", modifier = Modifier.width(100.dp), color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
            if (showDatePicker) {
                DatePickerModal(
                    onDateSelected = { date ->
                        formViewModel.birthdate = convertDate(date)
                        showDatePicker = false
                    },
                    onDismiss = { showDatePicker = false })
            }
            if (!formViewModel.isBirthdateValid) {
                Text(
                    text = "Pick a date",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            // Occupation Input
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(Color(0xff546e7a), shape = RoundedCornerShape(8.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Occu-\npation: ", modifier = Modifier.width(100.dp), color = Color.White,
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
                    value = formViewModel.occupation,
                    onValueChange = { formViewModel.occupation = it },
                    textStyle = TextStyle(
                        Color.White,
                        fontSize = 16.sp
                    )
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        formViewModel.resetForm()
                        navController.navigate(Screen.HomeScreen.route)
                    },
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
                        if (formViewModel.validateInputs()) {
                            navController.navigate(Screen.NewFriendFun.route)
                        }
                    },
                    Modifier
                        .height(60.dp)
                        .fillMaxWidth(0.9f)
                ) { Text(text = "Fun things", fontSize = 20.sp) }
            }
        }
    }
}