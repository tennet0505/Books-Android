package com.example.books.ViewModel

import com.example.books.Model.Book
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.books.ApiService.RetrofitClient
import kotlinx.coroutines.launch

class BookViewModel : ViewModel() {

    private val _bookData = MutableLiveData<List<Book>>()  // List of Book objects
    val booksData: LiveData<List<Book>> get() = _bookData

    // State to hold the search query
    var searchQuery: String = ""
        set(value) {
            field = value
            _filteredBooks.value = _bookData.value?.filter { book ->
                book.title.contains(value, ignoreCase = true) ||
                        book.author.contains(value, ignoreCase = true)
            }
        }

    private val _filteredBooks = MutableLiveData<List<Book>>()  // List of filtered books
    val filteredBooks: LiveData<List<Book>> get() = _filteredBooks

    init {
        viewModelScope.launch {
            getBooks()
        }
    }

    private suspend fun getBooks() {
        try {
            // Fetch books from API
            val bookList = RetrofitClient.booksApiService.getBooks()
            _bookData.value = bookList  // Set the result to LiveData
            _filteredBooks.value = bookList  // Initialize filtered list with all books
        } catch (e: Exception) {
            // Handle error, optionally set an error message or empty list
            _bookData.value = emptyList()  // Set empty list if there’s an error
            _filteredBooks.value = emptyList()  // Also set filtered list to empty
        }
    }
}
