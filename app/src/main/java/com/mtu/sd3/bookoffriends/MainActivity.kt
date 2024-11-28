package com.mtu.sd3.bookoffriends

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mtu.sd3.bookoffriends.screens.FindFriend
import com.mtu.sd3.bookoffriends.screens.FriendDetails
import com.mtu.sd3.bookoffriends.screens.HomeScreen
import com.mtu.sd3.bookoffriends.screens.NewFriendFun
import com.mtu.sd3.bookoffriends.screens.NewFriendInfos
import com.mtu.sd3.bookoffriends.ui.theme.BookOfFriendsTheme
import com.mtu.sd3.bookoffriends.utility.SQLViewmodel

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
    data object FriendDetails : Screen("frienddetails/{id}") {
        fun createRoute(id: Int): String {
            return "frienddetails/${id}"
        }
    }
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
        NavItem("New Friend Fun", screen = Screen.NewFriendFun),
        NavItem("Friend Details", screen = Screen.FriendDetails)

    )
    val viewModel: FormViewModel = viewModel()
    val sqlViewmodel: SQLViewmodel = viewModel()

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
            composable(Screen.FindFriend.route) { FindFriend(navController, sqlViewmodel) }
            composable(Screen.NewFriendFun.route) { NewFriendFun(navController, viewModel, sqlViewmodel) }
            composable(
                Screen.FriendDetails.route,
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("id")
                if (id != null)
                    FriendDetails(sqlViewmodel, id)
            }
        }
    }
}

class FormViewModel : ViewModel() {
    var firstName by mutableStateOf("")
    var lastName by mutableStateOf("")
    var age by mutableIntStateOf(-1)
    var height by mutableStateOf("n/a")
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

    //TODO check if mutableStateOfs are necessary. Change viewmodel on result.
    var messageToOwner = "";

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