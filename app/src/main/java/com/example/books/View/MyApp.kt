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
import com.example.books.ViewModel.SearchViewModel

@Composable
fun MyApp(bookViewModel: BookViewModel,
          favBookViewModel: FavoriteBooksViewModel,
          searchViewModel: SearchViewModel) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            Modifier.padding(innerPadding)
        ) {
            composable("home") {
                HomeScreen(bookViewModel = bookViewModel, navController = navController)
            }
            composable("favorite") {
                MyLibraryScreen(favBooksViewModel = favBookViewModel, navController = navController)
            }
            composable("search") {
                SearchScreen(searchViewModel = searchViewModel, navController = navController)
            }
            // Add route for book details
            composable("BookDetailsScreen") {
                BookDetailsScreen(navController = navController, viewModel = bookViewModel)
            }
        }
    }
}
@Composable
fun HomeScreen(
    bookViewModel: BookViewModel,
    navController: NavHostController
) {
    // Just pass the navController to BooksScreen
    BooksScreen(
        navController = navController,
        viewModel = bookViewModel
    )
}

@Composable
fun MyLibraryScreen(
    favBooksViewModel: FavoriteBooksViewModel,
    navController: NavHostController
) {
    FavoriteBooksScreen(
        navController = navController,
        favoriteBooksViewModel = favBooksViewModel
    )
}

@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel,
    navController: NavHostController
) {
    SearchBookScreen(
        navController = navController,
        searchViewModel = searchViewModel
    )
}
