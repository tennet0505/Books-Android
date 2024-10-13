package ViewModel

import View.Book
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.books.RetrofitClient
import kotlinx.coroutines.launch

//class BookDetailViewModel(private val bookId: String) : ViewModel() {
//    private val _bookDetails = MutableLiveData<Book>()
//    val bookDetails: LiveData<Book> get() = _bookDetails
//
//    init {
//        viewModelScope.launch {
//            fetchBookDetails()
//        }
//    }
//
//    private suspend fun fetchBookDetails() {
//        // Make a network call to fetch book details by bookId
//        // For example:
//        val book = RetrofitClient.booksApiService.getBookById(bookId)
//        _bookDetails.value = book
//    }
//}
