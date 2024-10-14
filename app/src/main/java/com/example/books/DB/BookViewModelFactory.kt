package com.example.books.DB

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.books.ViewModel.BookViewModel
import com.example.books.ViewModel.FavoriteBooksViewModel

class BookViewModelFactory(
    private val repository: BookRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(BookViewModel::class.java) -> {
                BookViewModel(repository) as T
            }
            modelClass.isAssignableFrom(FavoriteBooksViewModel::class.java) -> {
                FavoriteBooksViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}