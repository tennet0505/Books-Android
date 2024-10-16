package com.example.books.View.BooksActivities

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.books.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun GenresSection() {
    val genres = listOf("Action", "Romance", "Sci-Fi", "Mystery") // Example genres
    val snackbarHostState = remember { SnackbarHostState() }

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(bottom = 16.dp) // Add bottom padding
    ) {
        items(genres) { genre ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clickable {
                        showSnackbar(snackbarHostState, "$genre section is coming soon.")
                    }
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .border(
                            width = 2.dp,
                            color = Color(0xA60076E5), // 65% opacity for #0076E5
                            shape = CircleShape
                        )
                        .background(Color.Transparent, shape = CircleShape) // Make the background transparent
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_genre), // Replace with your image file name
                        contentDescription = "My Image",
                        modifier = Modifier.size(100.dp) // Set size as needed
                    )
                }
                Text(
                    text = genre,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}


// Function to show Snackbar
private fun showSnackbar(snackbarHostState: SnackbarHostState, message: String) {
    CoroutineScope(Dispatchers.Main).launch {
        snackbarHostState.showSnackbar(message)
    }
}
