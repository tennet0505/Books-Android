package com.example.books.ViewModel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.books.DB.BookRepository
import com.example.books.Model.Book
import com.example.books.Model.BookLocal
import kotlinx.coroutines.launch

class SearchBookViewModel(private val repository: BookRepository) : ViewModel(), ViewModelInterface {

    private val _bookData = MutableLiveData<List<Book>>()  // List of Book objects
    val booksData: LiveData<List<Book>> get() = _bookData

    private val _filteredBooks = MutableLiveData<List<Book>>()  // List of filtered books
    val filteredBooks: LiveData<List<Book>> get() = _filteredBooks

    override var searchQuery: String = ""
        set(value) {
            field = value
            filterBooks(value)  // Filter books whenever the search query changes
        }

    init {
        // Load books when ViewModel is created
        viewModelScope.launch {
            loadBooksFromDatabase()
        }
    }

    // Load books from the database
    private suspend fun loadBooksFromDatabase() {
        _bookData.value = repository.getBooksFromDb()  // Load books from the repository
        _filteredBooks.value = _bookData.value  // Initialize filtered list
    }

    // Filter books based on the search query
    private fun filterBooks(query: String) {
        _filteredBooks.value = _bookData.value?.filter { book ->
            book.title.contains(query, ignoreCase = true) ||
                    book.author.contains(query, ignoreCase = true)
        }
    }

    override fun updateBookFavoriteStatus(bookId: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            repository.updateBookFavoriteStatus(bookId, isFavorite)
        }
    }

    override fun shareBook(book: BookLocal?, context: Context) {
        val shareText = "Check out this book: ${book?.title} by ${book?.author}.\nMore info: ${book?.imageUrl}" // Adjust as needed
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }
        context.startActivity(Intent.createChooser(shareIntent, "Share via"))
    }
}
