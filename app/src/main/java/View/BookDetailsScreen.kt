import View.Book
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

@Composable
fun BookDetailsScreen(navController: NavController) {
    val book = navController.previousBackStackEntry
        ?.savedStateHandle
        ?.get<Book>("book")

    if (book != null) {
        // Display book details
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 64.dp)) {
            Image(
                painter = rememberAsyncImagePainter(book.imageUrl),
                contentDescription = "Book cover",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 64.dp)
                    .aspectRatio(2f / 3f) // Optional: Keep a specific aspect ratio
                    .align(Alignment.CenterHorizontally), // Center the image horizontally
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = book.title,
                    style = TextStyle(
                    fontSize = 20.sp, // Set the desired font size here
                    fontWeight = FontWeight.Bold // Optional: set font weight
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "by ${book.author}",
                style = TextStyle(
                    fontSize = 16.sp, // Set the desired font size here
                    fontWeight = FontWeight.Bold // Optional: set font weight
                ))
        }
    } else {
        Text("No book details available")
    }
}

