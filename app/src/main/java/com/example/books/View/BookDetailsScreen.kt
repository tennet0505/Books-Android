import com.example.books.Model.Book
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.books.Model.BookLocal
import com.example.books.ViewModel.BookOperations
import com.example.books.ViewModel.BookViewModel

@Composable
fun BookDetailsScreen(
    navController: NavController,
    viewModel: BookOperations
) {
    // Retrieve the book from the navigation back stack
    val book = navController.previousBackStackEntry
        ?.savedStateHandle
        ?.get<BookLocal>("book")

    // Check if the book is favorite; if not, default to false
    var isFavorite by remember { mutableStateOf(book?.isFavorite ?: false) }

    // Get the book ID and ensure it's not null
    val bookId = book?.id?.toInt() ?: throw IllegalArgumentException("Book ID cannot be null")

    // Function to handle favorite click
    val onFavoriteClick: () -> Unit = {
        // Toggle favorite status
        isFavorite = !isFavorite

        // Update the favorite status in the ViewModel
        viewModel.updateBookFavoriteStatus(bookId = bookId, isFavorite)
    }

    // Main column layout for book details
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = 64.dp)
    ) {
        // Book cover image
        Image(
            painter = rememberAsyncImagePainter(book.imageUrl),
            contentDescription = "Book cover",
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
                .padding(horizontal = 64.dp)
                .aspectRatio(2f / 3f)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Row for title and favorite button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Title in the center
            Text(
                text = book.title,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )

            // Favorite button
            IconButton(
                onClick = onFavoriteClick,
                modifier = Modifier.padding(end = 8.dp)
            ) {
                // Change icon based on favorite status
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite"
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "by ${book.author}",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 64.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "From publisher",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 32.dp)
                .padding(top = 16.dp)
        )
        Text(
            text = book.bookDescription,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 24.sp
            ),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(32.dp)
        )
    }
}
