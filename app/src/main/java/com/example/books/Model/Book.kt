package com.example.books.Model

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val author: String,
    val imageUrl: String,
    val bookDescription: String,
    val isFavorite: Boolean = false,
    val isPopular: Boolean = false,
    val pdfUrl: String
)

fun Book.toBookLocal(): BookLocal {
    return BookLocal(
        id = this.id.toString(),  // Convert Long to String
        title = this.title,
        author = this.author,
        imageUrl = this.imageUrl,
        bookDescription = this.bookDescription,
        isFavorite = this.isFavorite,
        isPopular = this.isPopular,
        pdfUrl = this.pdfUrl
    )
}

data class BookLocal(
    val id: String,
    val title: String,
    val author: String,
    val imageUrl: String,
    val bookDescription: String,
    val isFavorite: Boolean = false,
    val isPopular: Boolean = false,
    val pdfUrl: String
) : Parcelable {


    @RequiresApi(Build.VERSION_CODES.Q)
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readBoolean(),
        parcel.readBoolean(),
        parcel.readString() ?: "",

    )
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(author)
        parcel.writeString(imageUrl)
        parcel.writeString(bookDescription)
        parcel.writeBoolean(isFavorite)
        parcel.writeBoolean(isPopular)
        parcel.writeString(pdfUrl)
    }

    override fun describeContents(): Int {
        return 0
    }
    @RequiresApi(Build.VERSION_CODES.Q)
    companion object CREATOR : Parcelable.Creator<BookLocal> {
        override fun createFromParcel(parcel: Parcel): BookLocal {
            return BookLocal(parcel)
        }

        override fun newArray(size: Int): Array<BookLocal?> {
            return arrayOfNulls(size)
        }
    }
}