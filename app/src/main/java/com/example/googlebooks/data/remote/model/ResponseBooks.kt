package com.example.googlebooks.data.remote.model

import com.google.gson.annotations.SerializedName

data class ResponseBooks(
    @SerializedName("items") var items: List<Book>
) {

    data class Book(
        @SerializedName("volumeInfo") var volumeInfo: VolumeInfo
    ) {

        data class VolumeInfo(
            @SerializedName("title") var title: String?,
            @SerializedName("authors") var authors: List<String>?,
            @SerializedName("imageLinks") var imageLinks: ImageLinks?
        )

        data class ImageLinks(
            @SerializedName("thumbnail") var thumbnail: String?
        )
    }
}

