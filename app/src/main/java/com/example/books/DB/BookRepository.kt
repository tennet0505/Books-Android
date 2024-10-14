package com.example.books.DB

import com.example.books.ApiService.RetrofitClient
import com.example.books.Model.Book

class BookRepository(private val bookDao: BookDao) {

    // Fetch books from the API and save them to the database
    suspend fun fetchAndSaveBooksFromApi() {
        val books = try {
            RetrofitClient.booksApiService.getBooks()
        } catch (e: Exception) {
            emptyList<Book>()
        }

        // Save books to the database if API call was successful
        if (books.isNotEmpty()) {
            bookDao.insertBooks(books)
        }
    }

    // Get books from the database
    suspend fun getBooksFromDb(): List<Book> {
        return bookDao.getAllBooks()
    }
}

