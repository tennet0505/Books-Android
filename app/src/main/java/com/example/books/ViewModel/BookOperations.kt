package com.example.books.ViewModel

interface BookOperations {
    fun updateBookFavoriteStatus(bookId: Int, isFavorite: Boolean)
}