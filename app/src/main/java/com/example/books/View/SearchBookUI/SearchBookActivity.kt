package com.example.books.View.SearchBookUI

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.books.View.UIComponents.HeaderTitle
import com.example.books.View.UIComponents.NoBooksAvailable
import com.example.books.ViewModel.SearchBookViewModel

@Composable
fun SearchBookScreen(
    searchViewModel: SearchBookViewModel,
    navController: NavController,
) {
    val books = searchViewModel.booksData.observeAsState(emptyList()).value
    val filteredBooks = searchViewModel.filteredBooks.observeAsState(emptyList()).value
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus() // Request focus for the search field
    }
    if (books.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            NoBooksAvailable("Books not found")
        }
    } else {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            HeaderTitle("Search")
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = searchViewModel.searchQuery,
                onValueChange = { searchViewModel.searchQuery = it },
                placeholder = { Text("Books & Authors") },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                trailingIcon = {
                    IconButton(onClick = {
                        searchViewModel.searchQuery = "" // Clear the search query
                        focusManager.clearFocus() // Dismiss the keyboard
                    }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Clear Search",
                            tint = Color.Gray // Set the color of the icon
                        )
                    }
                },
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (filteredBooks.isEmpty()) {
                Text(text = "No books found", modifier = Modifier.padding(16.dp))
            } else {
                BooksTableView(books = filteredBooks, navController = navController)
            }
        }
    }
}