package com.example.books.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.books.Model.Book
import com.example.books.Model.BookLocal
import com.example.books.Model.toBookLocal

@Composable
fun BooksGrid(
    books: List<Book>,  // The list of books passed from ViewModel
    modifier: Modifier = Modifier,
    navController: NavController// Lambda to handle book clicks
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Creates a grid with 2 columns
        modifier = modifier.padding(8.dp),  // Optionally add padding
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(books) { book ->
            BookItem(
                book = book,
                navController = navController
            )
        }
    }
}

@Composable
fun BookItem(book: Book, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .aspectRatio(0.75f)
            .clickable {
                // Navigate and pass the Book object
                val bookLocal: BookLocal = book.toBookLocal()
                navController.currentBackStackEntry?.savedStateHandle?.set("book", bookLocal)
                navController.navigate("BookDetailsScreen")
            },
                shape = RoundedCornerShape(4.dp)
    ) {
        Box(Modifier.fillMaxSize()) {
            // Image as background
            Image(
                painter = rememberAsyncImagePainter(book.imageUrl),
                contentDescription = "Book cover",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )

            // Top gradient
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp) // Adjust height as needed
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.4f),  // 40% opacity black
                                Color.Transparent // Fully transparent
                            ),
                            startY = 0f,
                            endY = 300f // Adjust as needed for smoother transition
                        )
                    )
            )

            // Bottom gradient
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp) // Adjust height as needed
                    .align(Alignment.BottomCenter)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.4f),  // 40% opacity black
                                Color.Transparent // Fully transparent
                            ),
                            startY = 300f, // Adjust as needed for smoother transition
                            endY = 0f
                        )
                    )
            )

            // Title at the top center
            Text(
                text = book.title,
                textAlign = TextAlign.Center,
                maxLines = 2,
                color = Color.White,
                fontSize = 28.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 16.dp)
                    .padding(horizontal = 8.dp)// Adjust padding as necessary
            )

            // Author at the bottom center
            Text(
                text = "by ${book.author}",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 8.dp)
                    .padding(horizontal = 8.dp)// Adjust padding as necessary
            )
        }
    }
}

@Composable
fun BookItemSquare(book: Book, navController: NavController) {
    Card(
        modifier = Modifier
            .size(100.dp) // Set size to 100x100
            .clickable {
                val bookLocal: BookLocal = book.toBookLocal()
                navController.currentBackStackEntry?.savedStateHandle?.set("book", bookLocal)
                navController.navigate("BookDetailsScreen")
            },
        shape = RoundedCornerShape(4.dp) // Set corner radius
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Image as background
            Image(
                painter = rememberAsyncImagePainter(book.imageUrl),
                contentDescription = "Book cover",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Gradient overlay for title
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp) // Adjust height for the title area
                    .align(Alignment.TopCenter)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Black.copy(alpha = 0.6f), Color.Transparent),
                            startY = 0f, // Start from the top
                            endY = 100f // End at the height of the Box
                        )
                    )
            )

            // Title at the top center
            Text(
                text = book.title,
                textAlign = TextAlign.Center,
                maxLines = 2, // Allow 2 lines for the title
                overflow = TextOverflow.Ellipsis, // Use ellipsis for overflow
                color = Color.White,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 8.dp)
                    .padding(horizontal = 4.dp) // Adjust padding as necessary
            )

            // Author at the bottom center
            Text(
                text = "by ${book.author}",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 8.sp,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 4.dp)
                    .padding(horizontal = 4.dp) // Adjust padding as necessary
            )
        }
    }
}
