package com.mtu.sd3.bookoffriends.screens
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mtu.sd3.bookoffriends.FormViewModel
import com.mtu.sd3.bookoffriends.Screen
import com.mtu.sd3.bookoffriends.utility.DatePickerModal
import com.mtu.sd3.bookoffriends.utility.convertMillisToDate

fun convertDate(millis: Long?): String {
    return millis?.let {
        convertMillisToDate(it)
    } ?: ""
}

@Composable
fun NewFriendInfos(navController: NavController, viewModel: FormViewModel) {
    var showDatePicker by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(16.dp) // Space between rows
    ) {
        // First Name Input
        Row {
            Text("First Name: ", modifier = Modifier.width(100.dp))
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = viewModel.firstName,
                onValueChange = { viewModel.firstName = it },
                isError = !viewModel.isFirstNameValid
            )
        }
        if (!viewModel.isFirstNameValid) {
            Text(
                text = "A valid name is required",
                color = Color.Red,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        // Last Name Input
        Row {
            Text("Last Name: ", modifier = Modifier.width(100.dp))
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = viewModel.lastName,
                onValueChange = { viewModel.lastName = it },
                isError = !viewModel.isLastNameValid
            )
        }
        if (!viewModel.isLastNameValid) {
            Text(
                text = "A valid lastname is required",
                color = Color.Red,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        // Age Input
        Row {
            Text("Age: ", modifier = Modifier.width(100.dp))
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = if (viewModel.age == -1) "" else viewModel.age.toString(),
                onValueChange = {
                    viewModel.age = it.toIntOrNull() ?: -1 // Ensure valid integer input
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                isError = !viewModel.isAgeValid
            )
        }
        if (!viewModel.isAgeValid) {
            if (!viewModel.isLastNameValid) {
                Text(
                    text = "A valid Age is required",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        // Address Input
        Row {
            Text("Address: ", modifier = Modifier.width(100.dp))
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = viewModel.address,
                onValueChange = { viewModel.address = it },
                isError = !viewModel.isAddressValid
            )
        }
        if (!viewModel.isAddressValid) {
            if (!viewModel.isLastNameValid) {
                Text(
                    text = "A valid Address is required",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        // Phone Number Input
        Row {
            Text("Phone Number: ", modifier = Modifier.width(100.dp))
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = viewModel.phoneNumber,
                onValueChange = { viewModel.phoneNumber = it },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                isError = !viewModel.isPhoneNumberValid
            )
        }
        if (!viewModel.isPhoneNumberValid) {
            if (!viewModel.isLastNameValid) {
                Text(
                    text = "A valid Number is required",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        // Birth Date Input with Date Picker
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = viewModel.birthdate,
            onValueChange = {},
            label = { Text("DOB") },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showDatePicker = !showDatePicker }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Select date of birth"
                    )
                }
            },
            isError = !viewModel.isBirthdateValid
        )
        if (showDatePicker) {
            DatePickerModal(
                onDateSelected = { date ->
                    viewModel.birthdate = convertDate(date)
                    showDatePicker = false
                },
                onDismiss = { showDatePicker = false })
        }
        if (!viewModel.isBirthdateValid) {
            Text(
                text = "Pick a date",
                color = Color.Red,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        // Occupation Input
        Row {
            Text("Occupation: ", modifier = Modifier.width(100.dp))
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = viewModel.occupation,
                onValueChange = { viewModel.occupation = it },
            )
        }


        // Submit Button
        Button(onClick = {
            if (viewModel.validateInputs()) {
                navController.navigate(Screen.NewFriendFun.route)
            }
        }) { Text(text = "Fun things") }
    }
}