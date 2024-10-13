package com.example.books

import View.Book
import retrofit2.http.GET

interface BooksApiService {
    @GET("/books")
    suspend fun getBooks(): List<Book>
}