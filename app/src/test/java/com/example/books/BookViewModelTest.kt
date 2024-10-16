package com.example.books
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.books.DB.AppDatabase

import com.example.books.DB.BookDao
import com.example.books.DB.BookRepository
import com.example.books.Model.Book
import com.example.books.ViewModel.BookViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


class BookViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule() // Allows LiveData to be observed synchronously
    private lateinit var bookDao: BookDao
    private lateinit var bookRepository: BookRepository
    private lateinit var viewModel: BookViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this) // Initializes Mockito annotations
        bookDao = mock(BookDao::class.java) // Mock BookDao
        bookRepository = BookRepository(bookDao)
        viewModel = BookViewModel(bookRepository)
    }

    //    private lateinit var bookDatabase: AppDatabase
//    private lateinit var bookRepository: BookRepository
//    private lateinit var viewModel: BookViewModel
//    private val observer: Observer<List<Book>> = Mockito.mock(Observer::class.java) as Observer<List<Book>>
//
//    @Before
//    fun setup() {
//        MockitoAnnotations.openMocks(this)
//        // Initialize the Room database
//        bookDatabase = Room.inMemoryDatabaseBuilder(
//            ApplicationProvider.getApplicationContext(),
//            AppDatabase::class.java
//        ).build()
//
//        // Initialize the BookRepository with the in-memory database
//        bookRepository = BookRepository(bookDatabase.bookDao())
//
//        // Create ViewModel with ViewModelFactory
//        viewModel = BookViewModel(bookRepository)
//        viewModel.filteredBooks.observeForever(observer) // Observe the LiveData
//    }
//
//    @Test
//    fun testSearchQuery() {
//        // Given
//        val book1 = Book(title = "Test Book 1", author = "Author 1", imageUrl = "", bookDescription = "")
//        val book2 = Book(title = "Test Book 2", author = "Author 2", imageUrl = "", bookDescription = "")
//        viewModel.addBook(book1)
//        viewModel.addBook(book2)
//
//        // When
//        viewModel.searchQuery = "Test Book 1"
//
//        // Then
//        assertEquals(1, viewModel.filteredBooks.value?.size) // Expect one book to be filtered
//        assertEquals("Test Book 1", viewModel.filteredBooks.value?.get(0)?.title) // Expect the correct book
//    }
//
    @Test
    fun addBook_ShouldInsertBookIntoDatabase(): Unit = runBlocking {
        val book = Book(
            title = "Test Book",
            author = "Test Author",
            imageUrl = "http://example.com/test.jpg",
            bookDescription = "Test Description"
        )

        // Add the book
        viewModel.addBook(book)

        // Verify that the book was added
        val books = bookRepository.getBooksFromDb()
        assertEquals(1, books.size)
        assertEquals(book.title, books[0].title)
    }
//
//    @Test
//    fun addBook_should_add_a_book_and_refresh_the_list(): Unit = runBlocking {
//        // Given a book to add
//        val book = Book(title = "Test Book", author = "Test Author", imageUrl = "", bookDescription = "")
//
//        // When addBook is called
//        viewModel.addBook(book)
//
//        // Verify that the repository's addBook method was called
//        verify(bookRepository).addBook(book)
//    }
}