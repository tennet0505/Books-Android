import com.example.books.Model.Book
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()) // Enable scrolling
                .padding(top = 64.dp)){
            Image(
                painter = rememberAsyncImagePainter(book.imageUrl),
                contentDescription = "Book cover",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp))
                    .padding(horizontal = 64.dp)
                    .aspectRatio(2f / 3f) // Optional: Keep a specific aspect ratio
                    .align(Alignment.CenterHorizontally), // Center the image horizontally
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = book.title,
                    style = TextStyle(
                    fontSize = 24.sp, // Set the desired font size here
                    fontWeight = FontWeight.Bold // Optional: set font weight
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally) // Align text to the left
                    .padding(horizontal = 64.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "by ${book.author}",
                style = TextStyle(
                    fontSize = 16.sp, // Set the desired font size here
                    fontWeight = FontWeight.Bold // Optional: set font weight
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally) // Align text to the left
                    .padding(horizontal = 64.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "From publisher",
                style = TextStyle(
                    fontSize = 20.sp, // Set the desired font size here
                    fontWeight = FontWeight.Bold, // Optional: set font weight
                ),
                modifier = Modifier
                    .align(Alignment.Start) // Align text to the left
                    .padding(horizontal = 32.dp)
                    .padding(top = 16.dp)// Padding around the text
            )
            Text(
                text = book.bookDescription,
                style = TextStyle(
                    fontSize = 16.sp, // Set the desired font size here
                    fontWeight = FontWeight.Normal, // Optional: set font weight
                    lineHeight = 24.sp // Line height of 1.5 (16.sp * 1.5 = 24.sp)
                ),
                modifier = Modifier
                    .align(Alignment.Start) // Align text to the left
                    .padding(32.dp) // Padding around the text
            )
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize() // Make the Box fill the entire screen
        ) {
            Text(
                text = "No book details available",
                style = TextStyle(
                    fontSize = 16.sp, // Set the desired font size here
                    fontWeight = FontWeight.Normal, // Optional: set font weight
                    lineHeight = 24.sp // Line height of 1.5 (16.sp * 1.5 = 24.sp)
                ),
                modifier = Modifier
                    .align(Alignment.Center) // Center the text horizontally and vertically
                    .padding(32.dp) // Add padding around the text
            )
        }
    }
}

