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

class FavoriteBooksViewModel(private val repository: BookRepository) : ViewModel(), BookOperations {

    private val _favoriteBooks = MutableLiveData<List<Book>>()  // List of favorite books
    val favoriteBooks: LiveData<List<Book>> get() = _favoriteBooks

    private val _filteredBooks = MutableLiveData<List<Book>>()  // List of filtered favorite books
    val filteredBooks: LiveData<List<Book>> get() = _filteredBooks

    override var searchQuery: String = ""
        set(value) {
            field = value
            filterFavoriteBooks(value)  // Filter favorite books whenever the search query changes
        }

    init {
        viewModelScope.launch {
            loadFavoriteBooks()
        }
    }

    // Load favorite books from the repository
    suspend fun loadFavoriteBooks() {
        _favoriteBooks.value = repository.getFavoriteBooks()
        filterFavoriteBooks(searchQuery) // Filter favorite books based on the current search query
    }

    // Filter favorite books based on the search query
    private fun filterFavoriteBooks(query: String) {
        _favoriteBooks.value?.let { favoriteBooks ->
            _filteredBooks.value = favoriteBooks.filter { book ->
                book.title.contains(query, ignoreCase = true) ||
                        book.author.contains(query, ignoreCase = true)
            }
        }
    }

    override fun updateBookFavoriteStatus(bookId: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            repository.updateBookFavoriteStatus(bookId, isFavorite)
            loadFavoriteBooks() // Refresh the favorite books after updating
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
