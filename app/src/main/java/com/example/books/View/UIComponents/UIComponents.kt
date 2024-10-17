package com.example.books.View.UIComponents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.books.ViewModel.ViewModelInterface

@Composable
fun NoBooksAvailable(message: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = message,
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal, lineHeight = 24.sp),
            modifier = Modifier.align(Alignment.Center).padding(32.dp)
        )
    }
}

@Composable
fun LoadingIndicator() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator() // Loader
    }
}

@Composable
fun HeaderTitle(title: String) {
    Text(
        text = title,
        style = TextStyle(
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
        ),
        modifier = Modifier.padding(vertical = 16.dp)
    )
}

@Composable
fun TitleGrid(title: String) {
    Text(
        text = title,
        style = TextStyle(
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier.padding(vertical = 8.dp)
    )
}