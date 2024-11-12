package com.mtu.sd3.bookoffriends

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mtu.sd3.bookoffriends.ui.theme.BookOfFriendsTheme

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
    data object NewFriend : Screen("newfriend")
    data object FindFriend : Screen("findfriend")
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
        NavItem("New Friend", screen = Screen.NewFriend),
        NavItem("Find Friend", screen = Screen.FindFriend)

    )

    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            if (selectedIndex != navItemList.indexOf(navItemList[0])) {
                TopAppBar(title = { Text("My book of friends") })
            }
        }, bottomBar =
        {
            NavigationBar {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                            if (navController.currentDestination?.route != navItem.screen.route) {
                                navController.navigate(navItem.screen.route) {
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }, label = { Text(text = navItem.label) },
                        icon = { Icons.Default.Add } //TODO: implement correct icons
                    )
                }
            }
        }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.HomeScreen.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.HomeScreen.route) { HomeScreen() }
            composable(Screen.NewFriend.route) { NewFriend() }
            composable(Screen.FindFriend.route) { FindFriend() }
        }
    }
}

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "MY BOOK OF FRIENDS",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun NewFriend() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "ADD A NEW FRIEND",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun FindFriend() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "FIND FRIENDS",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )
    }
}