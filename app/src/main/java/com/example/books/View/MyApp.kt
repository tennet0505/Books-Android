package com.example.books.View

import BookDetailsScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MyApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen() }
            composable("favorite") { MyLibraryScreen() }
        }
    }
}

@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "BooksScreen") {
        composable(route = "BooksScreen") {
            BooksScreen(navController = navController)
        }
        composable(route = "BookDetailsScreen") {
            BookDetailsScreen(navController = navController)
        }
    }
}

@Composable
fun MyLibraryScreen() {
    // Your profile screen content
    Text(text = "This is the Profile Screen")
}
