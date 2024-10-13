package com.example.books
import BookDetailsScreen
import View.BooksGrid
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.books.ui.theme.BooksTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BooksTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    getBooks()
                }
            }
        }
    }
}

//@Composable
//fun DetailScreenOpen(modifier: Modifier = Modifier, viewModel: BookViewModel = viewModel()){
//    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
//    val coroutineScope = rememberCoroutineScope()
//
//    NavigableSupportingPaneScaffold(
//        modifier = modifier,
//        navigator = navigator,
//        mainPane = {
//            val books = viewModel.booksData.observeAsState().value
//
//            if (books.isNullOrEmpty()) {
//                Text(text = "No books available", modifier = modifier)
//            } else {
//                BooksGrid(books = books) {
//                    coroutineScope.launch { // Launch a coroutine
//                        navigator.navigateTo(
//                            pane = ListDetailPaneScaffoldRole.Detail,
//                            contentKey = it
//                        )
//                    }
//                }
//            }
//        },
//        supportingPane = {
//            val content = navigator.currentDestination?.contentKey
//            BookDetailsScreen(book = content)
//        })
//}

@Composable
fun getBooks(modifier: Modifier = Modifier, viewModel: BookViewModel = viewModel()) {
    val books = viewModel.booksData.observeAsState().value

    if (books.isNullOrEmpty()) {
        Text(text = "No books available", modifier = modifier)
    } else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(top = 32.dp) // Add top padding here
        ) {
            Text(
                text = "Books",
                style = TextStyle(
                    fontSize = 20.sp, // Set the desired font size here
                    fontWeight = FontWeight.Bold // Optional: set font weight
                )
            )

            Spacer(modifier = Modifier.height(16.dp)) // Add some space between title and grid

            BooksGrid(books = books) {
                // Handle item click here
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BooksTheme {
        getBooks()
    }
}