package com.example.books.View.BooksUI

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.books.ViewModel.BookViewModel
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import com.example.books.View.UIComponents.BookItemSquare
import com.example.books.View.UIComponents.HeaderTitle
import com.example.books.View.UIComponents.LoadingIndicator
import com.example.books.View.UIComponents.NoBooksAvailable
import com.example.books.View.UIComponents.TitleGrid

@Composable
fun BooksScreen(
    viewModel: BookViewModel,
    navController: NavController
) {
    val books = viewModel.booksData.observeAsState(emptyList()).value
    val filteredBooks = viewModel.filteredBooks.observeAsState(emptyList()).value
    val isLoading = viewModel.isLoading

    if (isLoading) {
        LoadingIndicator()
    } else {
        if (books.isEmpty()) {
            NoBooksAvailable("No books available")
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                item {
                    HeaderTitle("Books")
                }

                // Search Bar
                item { SearchBar(viewModel, navController) }
                item { Spacer(modifier = Modifier.height(32.dp)) }
                // Genres Section
                item { GenresSection() }
                item { Spacer(modifier = Modifier.height(16.dp)) }
                // Popular Books Section
                item {
                    TitleGrid("Popular")
                }
                // Popular Books Items
                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(bottom = 16.dp)
                    ) {
                        items(filteredBooks.filter { it.isPopular }) { book ->
                            BookItemHorizontal(book = book,
                                navController = navController,
                                onFavoriteClick = {
                                    viewModel.updateBookFavoriteStatus(book.id.toInt(), !book.isFavorite)
                                })
                        }
                    }
                }
                // New Books Section
                item {
                    TitleGrid("New")
                }
                item { Spacer(modifier = Modifier.height(4.dp)) }
                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(bottom = 16.dp)
                    ) {
                        items(filteredBooks.filter { !it.isPopular }) { book ->
                            BookItemSquare(book = book,
                                navController = navController,
                                isFavorite = book.isFavorite,
                                onFavoriteClick = {
                                    viewModel.updateBookFavoriteStatus(book.id.toInt(), !book.isFavorite)
                                })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SearchBar(viewModel: BookViewModel, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("search") {
                    popUpTo("home") { inclusive = false }
                }
            }
    ) {
        TextField(
            value = viewModel.searchQuery,
            onValueChange = { /* No action needed since it's not editable */ },
            placeholder = { Text("Search books...") },
            enabled = false,  // Make the TextField non-editable
            modifier = Modifier.fillMaxWidth()
        )
    }
}

