package com.example.books.View.BooksActivities

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.books.Model.Book
import com.example.books.Model.BookLocal
import com.example.books.Model.toBookLocal
import com.example.books.ViewModel.BookViewModel
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import com.example.books.R
import com.example.books.View.BookItem
import com.example.books.View.BookItemSquare
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun BooksScreen(
    viewModel: BookViewModel,
    navController: NavController
) {
    val books = viewModel.booksData.observeAsState(emptyList()).value
    val filteredBooks = viewModel.filteredBooks.observeAsState(emptyList()).value
    val isLoading = viewModel.isLoading

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator() // Loader
        }
    } else {
        if (books.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "No books available",
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
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp) // Set padding for the content
            ) {
                item {
                    Text(
                        text = "Books",
                        style = TextStyle(
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                        ),
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }

                // Search Bar
                item { SearchBar(viewModel, navController) }
                item { Spacer(modifier = Modifier.height(32.dp)) }
                // Genres Section
                item { GenresSection() }
                item { Spacer(modifier = Modifier.height(16.dp)) }
                // Popular Books Section
                item {
                    Text(
                        text = "Popular",
                        style = TextStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
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
                    Text(
                        text = "New",
                        style = TextStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
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

