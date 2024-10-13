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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.books.ViewModel.BookViewModel

@Composable
fun BooksScreen(
    modifier: Modifier = Modifier,
    viewModel: BookViewModel = viewModel(),
    navController: NavController
) {
    val books = viewModel.booksData.observeAsState(emptyList()).value // Get the list of books
    val filteredBooks = viewModel.filteredBooks.observeAsState(emptyList()).value // Get the filtered list of books

    if (books.isNullOrEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize() // Make the Box fill the entire screen
        ) {
            Text(
                text = "No book details available",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 24.sp
                ),
                modifier = Modifier
                    .align(Alignment.Center) // Center the text horizontally and vertically
                    .padding(32.dp) // Add padding around the text
            )
        }
    } else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Books",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(16.dp)) // Add some space between title and search bar

            // Search Bar
            TextField(
                value = viewModel.searchQuery,
                onValueChange = { viewModel.searchQuery = it }, // Update search query in ViewModel
                placeholder = { Text("Search books...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp) // Add horizontal padding to the search bar
            )

            Spacer(modifier = Modifier.height(16.dp)) // Add some space between search bar and grid

            // Display filtered books
            if (filteredBooks.isEmpty()) {
                Text(text = "No books found", modifier = Modifier.padding(16.dp))
            } else {
                BooksGrid(books = filteredBooks, navController = navController)
            }
        }
    }
}

