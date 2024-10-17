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

    // Primary constructor for Parcelable
    constructor(parcel: Parcel) : this(
        id = parcel.readString() ?: "",
        title = parcel.readString() ?: "",
        author = parcel.readString() ?: "",
        imageUrl = parcel.readString() ?: "",
        bookDescription = parcel.readString() ?: "",
        isFavorite = parcel.readInt() == 1, // Use writeInt() and readInt()
        isPopular = parcel.readInt() == 1, // Use writeInt() and readInt()
        pdfUrl = parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(author)
        parcel.writeString(imageUrl)
        parcel.writeString(bookDescription)
        parcel.writeInt(if (isFavorite) 1 else 0) // Use writeInt() for boolean
        parcel.writeInt(if (isPopular) 1 else 0)  // Use writeInt() for boolean
        parcel.writeString(pdfUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BookLocal> {
        override fun createFromParcel(parcel: Parcel): BookLocal {
            return BookLocal(parcel)
        }

        override fun newArray(size: Int): Array<BookLocal?> {
            return arrayOfNulls(size)
        }
    }
}