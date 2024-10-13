package View

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

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
                navController.currentBackStackEntry?.savedStateHandle?.set("book", book)
                navController.navigate(route = "BookDetailsScreen")
            }
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(book.imageUrl),
                contentDescription = "Book cover",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = book.title,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = "by ${book.author}",
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            )
        }
    }
}
