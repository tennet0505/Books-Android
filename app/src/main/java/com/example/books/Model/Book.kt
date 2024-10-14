package com.example.books.Model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val author: String,
    val imageUrl: String,
    val bookDescription: String
)

data class BookLocal(
    val id: String,
    val title: String,
    val author: String,
    val imageUrl: String,
    val bookDescription: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(author)
        parcel.writeString(imageUrl)
        parcel.writeString(bookDescription)
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