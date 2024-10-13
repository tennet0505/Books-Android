package com.example.books.ApiService

import com.example.books.Model.Book
import retrofit2.http.GET

interface BooksApiService {
    @GET("/books")
    suspend fun getBooks(): List<Book>
}