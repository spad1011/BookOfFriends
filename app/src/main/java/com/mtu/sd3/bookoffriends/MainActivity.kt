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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
import com.mtu.sd3.bookoffriends.utility.FormViewModel
import com.mtu.sd3.bookoffriends.utility.SQLViewModel

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
    val sqlViewmodel: SQLViewModel = viewModel()

    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            if (selectedIndex != navItemList.indexOf(navItemList[0])) {
                TopAppBar(title = { Text("My book of friends") })
            }
        }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.FindFriend.route,
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

