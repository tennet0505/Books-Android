package com.example.books.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavBar(navController: NavController) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    Box(modifier = Modifier.background(Color.Black)) { // Set the background color here
        BottomNavigation(
            modifier = Modifier.height(90.dp),
            backgroundColor = Color.Transparent // Make BottomNavigation background transparent
        ) {

        BottomNavigationItem(
            icon = { Icon(Icons.Filled.MenuBook,
                contentDescription = "Books",
                tint = Color.White) },
            label = { Text("Books", color = Color.White) },
            selected = currentRoute == "home",
            onClick = {
                navController.navigate("home") {
                    popUpTo("home") { inclusive = true }
                }
            }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Favorite,
                contentDescription = "Favorites",
                tint = Color.White) },
            label = { Text("My library", color = Color.White) },
            selected = currentRoute == "favorite",
            onClick = {
                navController.navigate("favorite") {
                    popUpTo("favorite") { inclusive = true }
                }
            }
        )
    }
}
}
