package com.example.books.View

import BookDetailsScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.books.ViewModel.BookViewModel
import com.example.books.ViewModel.FavoriteBooksViewModel

@Composable
fun MyApp(bookViewModel: BookViewModel,
          favBookViewModel: FavoriteBooksViewModel) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen(bookViewModel) }
            composable("favorite") { MyLibraryScreen(favBookViewModel) }
        }
    }
}

@Composable
fun HomeScreen(bookViewModel: BookViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "BooksScreen") {
        composable(route = "BooksScreen") {
            BooksScreen(
                navController = navController,
                viewModel = bookViewModel)
        }
        composable(route = "BookDetailsScreen") {
            BookDetailsScreen(
                navController = navController,
                viewModel = bookViewModel)
        }
    }
}

@Composable
fun MyLibraryScreen(favBooksViewModel: FavoriteBooksViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "FavoriteBooksScreen") {
        composable(route = "FavoriteBooksScreen") {
            FavoriteBooksScreen(
                navController = navController,
                favoriteBooksViewModel = favBooksViewModel)
        }
        composable(route = "BookDetailsScreen") {
            BookDetailsScreen(
                navController = navController,
                viewModel = favBooksViewModel)
        }
    }
}
