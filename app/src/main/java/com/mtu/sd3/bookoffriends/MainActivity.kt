package com.mtu.sd3.bookoffriends

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mtu.sd3.bookoffriends.screens.FindFriend
import com.mtu.sd3.bookoffriends.screens.HomeScreen
import com.mtu.sd3.bookoffriends.screens.NewFriendFun
import com.mtu.sd3.bookoffriends.ui.theme.BookOfFriendsTheme
import com.mtu.sd3.bookoffriends.utility.convertMillisToDate
import com.mtu.sd3.bookoffriends.screens.NewFriendInfos

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookOfFriendsTheme {
                MainFunction()
            }
        }
    }
}

sealed class Screen(val route: String) {
    data object HomeScreen : Screen("homescreen")
    data object NewFriendInfos : Screen("newfriendinfos")
    data object FindFriend : Screen("findfriend")
    data object NewFriendFun : Screen("newfriendfun")
}

data class NavItem(
    var label: String,
    var screen: Screen
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainFunction() {
    val navController = rememberNavController()
    val navItemList = listOf(
        NavItem("HomeScreen", screen = Screen.HomeScreen),
        NavItem("New Friend Infos", screen = Screen.NewFriendInfos),
        NavItem("Find Friend", screen = Screen.FindFriend),
        NavItem("New Friend Fun", screen = Screen.NewFriendFun)

    )
    val viewModel: FormViewModel = viewModel()

    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            if (selectedIndex != navItemList.indexOf(navItemList[0])) {
                TopAppBar(title = { Text("My book of friends") })
            }
        }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.HomeScreen.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.HomeScreen.route) { HomeScreen(navController) }
            composable(Screen.NewFriendInfos.route) { NewFriendInfos(navController, viewModel) }
            composable(Screen.FindFriend.route) { FindFriend() }
            composable(Screen.NewFriendFun.route) { NewFriendFun(navController, viewModel) }

        }
    }
}

class FormViewModel : ViewModel() {
    var firstName by mutableStateOf("")
    var lastName by mutableStateOf("")
    var age by mutableIntStateOf(-1)
    var address by mutableStateOf("")
    var phoneNumber by mutableStateOf("")
    var occupation by mutableStateOf("")
    var hobbies by mutableStateOf("")
    var birthdate by mutableStateOf("")
    var birthplace by mutableStateOf("")
    var favFood by mutableStateOf("")
    var favMovie by mutableStateOf("")
    var mostLoved by mutableStateOf("")
    var mostHated by mutableStateOf("")

    var isFirstNameValid by mutableStateOf(true)
    var isLastNameValid by mutableStateOf(true)
    var isAgeValid by mutableStateOf(true)
    var isAddressValid by mutableStateOf(true)
    var isPhoneNumberValid by mutableStateOf(true)
    var isBirthdateValid by mutableStateOf(true)
    private val PHONE_NUMBER_REGEX =
        Regex("^[+]?[(]?[0-9]{3}[)]?[-\\s.]?[0-9]{3}[-\\s.]?[0-9]{4,6}$")
    private val PHONE_NUMBER_REGEX_TESTING = Regex("^\\d{4}\$")

    fun validateInputs(): Boolean {
        isFirstNameValid = firstName.length >= 3 && firstName.isNotBlank()
        isLastNameValid = firstName.length >= 3 && firstName.isNotBlank()
        isAgeValid = age in 120 downTo 0 && age.toString().isNotBlank()
        isAddressValid = address.isNotBlank()
        isPhoneNumberValid =
            PHONE_NUMBER_REGEX_TESTING.matches(phoneNumber) //TODO remove testing regex eventually
        isBirthdateValid = birthdate.isNotBlank()
        val returnCheck = listOf(
            isFirstNameValid,
            isLastNameValid,
            isAgeValid,
            isAddressValid,
            isPhoneNumberValid,
            isBirthdateValid
        )
        return returnCheck.all { it }
    }
}