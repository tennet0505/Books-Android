package com.example.books.ViewModel

import android.content.Context
import com.example.books.Model.BookLocal

interface ViewModelInterface {
    fun updateBookFavoriteStatus(bookId: Int, isFavorite: Boolean)
    fun shareBook(book: BookLocal?, context: Context)
    var searchQuery: String
}