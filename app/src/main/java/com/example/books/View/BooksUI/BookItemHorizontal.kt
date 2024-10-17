package com.example.books.View.BooksUI

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.books.Helpers.AppColors
import com.example.books.Model.Book
import com.example.books.Model.BookLocal
import com.example.books.Model.toBookLocal
import com.example.books.View.BookItem

@Composable
fun BookItemHorizontal(book: Book,
                       navController: NavController,
                       onFavoriteClick: (Book) -> Unit) {
    Column(
        modifier = Modifier
            .clickable {
                val bookLocal: BookLocal = book.toBookLocal()
                navController.currentBackStackEntry?.savedStateHandle?.set("book", bookLocal)
                navController.navigate("BookDetailsScreen")
            }
            .width(180.dp) // Set the width of the cell
            .height(320.dp) // Set the height of the cell
    ) {
        // Use BookItem instead of Image
        BookItem(book = book,
            navController = navController,
            isHideFavButton = false,
            isFavorite = book.isFavorite,
            onFavoriteClick = {
                onFavoriteClick(book)
            })
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
        ) {
            Text(text = book.title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = "by ${book.author}", fontSize = 14.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Default.Star,
                    contentDescription = "Rating",
                    tint = AppColors.OrangeColor,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "4.5", fontSize = 14.sp)
            }
        }
    }
}
