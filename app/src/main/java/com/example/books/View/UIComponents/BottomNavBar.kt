package com.example.books.View.UIComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavBar(navController: NavController) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    // Access the color scheme from MaterialTheme
    val backgroundColor = MaterialTheme.colorScheme.background
    val contentColor = MaterialTheme.colorScheme.onBackground
    val inactiveContentColor = contentColor.copy(alpha = 0.5f) // Lower opacity for inactive items

    Box(modifier = Modifier.background(backgroundColor)) {
        BottomNavigation(
            modifier = Modifier.height(90.dp),
            backgroundColor = backgroundColor,
            contentColor = contentColor
        ) {

            BottomNavigationItem(
                icon = {
                    Icon(
                        Icons.Filled.MenuBook,
                        contentDescription = "Books",
                        tint = if (currentRoute == "home") contentColor else inactiveContentColor
                    )
                },
                label = {
                    Text(
                        "Books",
                        color = if (currentRoute == "home") contentColor else inactiveContentColor
                    )
                },
                selected = currentRoute == "home",
                onClick = {
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )

            BottomNavigationItem(
                icon = {
                    Icon(
                        Icons.Default.Favorite,
                        contentDescription = "Favorites",
                        tint = if (currentRoute == "favorite") contentColor else inactiveContentColor
                    )
                },
                label = {
                    Text(
                        "My library",
                        color = if (currentRoute == "favorite") contentColor else inactiveContentColor
                    )
                },
                selected = currentRoute == "favorite",
                onClick = {
                    navController.navigate("favorite") {
                        popUpTo("favorite") { inclusive = true }
                    }
                }
            )
            BottomNavigationItem(
                icon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search",
                        tint = if (currentRoute == "search") contentColor else inactiveContentColor
                    )
                },
                label = {
                    Text(
                        "Search",
                        color = if (currentRoute == "search") contentColor else inactiveContentColor
                    )
                },
                selected = currentRoute == "search",
                onClick = {
                    navController.navigate("search") {
                        popUpTo("search") { inclusive = true }
                    }
                }
            )
        }
    }
}
