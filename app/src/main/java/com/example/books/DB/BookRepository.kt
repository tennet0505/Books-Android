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

        // Load existing books from the database to preserve favorite statuses
        val existingBooks = getBooksFromDb().associateBy { it.id }

        // Prepare a list to save the updated books with preserved favorite status
        val updatedBooks = books.map { apiBook ->
            val existingBook = existingBooks[apiBook.id]
            // If the book already exists, keep its favorite status
            existingBook?.copy(title = apiBook.title, author = apiBook.author, imageUrl = apiBook.imageUrl, bookDescription = apiBook.bookDescription)
                ?: // If it's a new book, return it as is
                apiBook
        }

        // Save updated books to the database
        bookDao.insertBooks(updatedBooks)
    }

    // Get books from the database
    suspend fun getBooksFromDb(): List<Book> {
        return bookDao.getAllBooks()
    }

    suspend fun getBookById(id: Int): Book? {
        return bookDao.getBookById(id)
    }

    // Update favorite status for a book
    suspend fun updateBookFavoriteStatus(bookId: Int, isFavorite: Boolean) {
        val book = bookDao.getBookById(bookId)
        if (book != null) {
            val updatedBook = book.copy(isFavorite = isFavorite)
            bookDao.update(updatedBook)
        }
    }

    // Method to get favorite books if needed
    suspend fun getFavoriteBooks(): List<Book> {
        return bookDao.getFavoriteBooks() // Assume this method is defined in your DAO
    }

    suspend fun addBook(book: Book) {
        bookDao.insertBook(book) // Assuming insertBook is defined in your DAO
    }
}
