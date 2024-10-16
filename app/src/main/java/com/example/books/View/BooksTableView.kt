package com.example.books.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.books.Model.Book
import com.example.books.Model.BookLocal
import com.example.books.Model.toBookLocal

@Composable
fun BooksTableView(
    books: List<Book>,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    LazyColumn(
        modifier = modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(books) { book ->
            BookTableItem(
                book = book,
                navController = navController
            )
        }
    }
}

@Composable
fun BookTableItem(book: Book, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                // Navigate and pass the Book object
                val bookLocal: BookLocal = book.toBookLocal()
                navController.currentBackStackEntry?.savedStateHandle?.set("book", bookLocal)
                navController.navigate("BookDetailsScreen")
            }
            .padding(8.dp)
    ) {
        // Book Image
        Image(
            painter = rememberAsyncImagePainter(book.imageUrl),
            contentDescription = "Book cover",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .aspectRatio(0.75f)
                .clip(RoundedCornerShape(8.dp))
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Title, Author and Rating in a Column
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = book.title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(1.dp))

            Text(
                text = "by ${book.author}",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(1.dp))

            // Rating logo (e.g., 5 stars, or any image)
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    imageVector = Icons.Filled.Star,  // Star icon provided by Compose
                    contentDescription = "Rating",
                    modifier = Modifier.size(16.dp),
                    colorFilter = ColorFilter.tint(Color.Yellow)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "4.5", // Replace with actual rating
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}
