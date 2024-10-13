import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BookDetailsScreen(book: Any?) {
    Surface(modifier = Modifier.fillMaxSize()) {
        // Check if book is not null and display its details
        book?.let { b ->
            Text(
                text = "Title: ${b}",
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "Author: ${b}",
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            )
            // Display other details as needed
        } ?: run {
            Text(
                text = "No book details available.",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

