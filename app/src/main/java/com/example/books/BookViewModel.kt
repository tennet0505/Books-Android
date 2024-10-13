package com.example.books

import View.Book
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BookViewModel : ViewModel() {

    private val _bookData = MutableLiveData<List<Book>>()  // List of Book objects
    val booksData: LiveData<List<Book>> get() = _bookData

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
        } catch (e: Exception) {
            // Handle error, optionally set an error message or empty list
            _bookData.value = emptyList()  // Set empty list if there’s an error
        }
    }
}
