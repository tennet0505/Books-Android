package com.example.books.View

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.books.ViewModel.BookViewModel
import com.example.books.ViewModel.FavoriteBooksViewModel

@Composable
fun FavoriteBooksScreen(
    favoriteBooksViewModel: FavoriteBooksViewModel,
    navController: NavController,
) {
    // Observe filtered favorite books from the ViewModel
    val books = favoriteBooksViewModel.favoriteBooks.observeAsState(emptyList()).value
    val filteredBooks = favoriteBooksViewModel.filteredBooks.observeAsState(emptyList()).value

    // Load favorite books when this screen is first displayed
    LaunchedEffect(Unit) {
        favoriteBooksViewModel.loadFavoriteBooks()
    }

    if (books.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "No favorite books available",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 24.sp
                ),
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(32.dp)
            )
        }
    } else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Favorite Books",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = favoriteBooksViewModel.searchQuery,
                onValueChange = { favoriteBooksViewModel.searchQuery = it },
                placeholder = { Text("Search favorite books...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (filteredBooks.isEmpty()) {
                Text(text = "No books found", modifier = Modifier.padding(16.dp))
            } else {
                BooksGrid(books = filteredBooks, navController = navController)
            }
        }
    }
}
