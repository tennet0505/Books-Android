package com.example.books.View
import com.example.books.ViewModel.BookViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.books.DB.AppDatabase
import com.example.books.DB.BookRepository
import com.example.books.DB.BookViewModelFactory
import com.example.books.ViewModel.FavoriteBooksViewModel
import com.example.books.ViewModel.SearchBookViewModel
import com.example.books.ui.theme.BooksTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the Room database
        val bookDatabase = AppDatabase.getDatabase(this)

        // Initialize the BookRepository
        val bookRepository = BookRepository(bookDatabase.bookDao())

        // Create ViewModel with ViewModelFactory
        val bookViewModel: BookViewModel by viewModels {
            BookViewModelFactory(bookRepository)
        }
        val favBooksViewModel: FavoriteBooksViewModel by viewModels {
            BookViewModelFactory(bookRepository)
        }
        val searchViewModel: SearchBookViewModel by viewModels {
            BookViewModelFactory(bookRepository)
        }

        setContent {
            BooksTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp(
                        bookViewModel = bookViewModel,
                        favBookViewModel = favBooksViewModel,
                        searchViewModel = searchViewModel
                    )
                }
            }
        }
    }
}


