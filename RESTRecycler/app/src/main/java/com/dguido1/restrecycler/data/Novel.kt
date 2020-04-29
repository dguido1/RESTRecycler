package com.dguido1.restrecycler.data
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/*
    David Guido
    Computer Science 411 (Mobile Application Development)
    App 3 (REST Recycler)
    March 29, 2020
*/

// Data class for a Google Books API novel
data class Novel(@SerializedName("volumeInfo") val info: VolumeInfo, val id: String): Serializable

// Data class for a Google Books API Volume
data class VolumeInfo(val title: String, @SerializedName("imageLinks") val image: Image?, val authors: List<String>, val pubDate: String): Serializable

// Data class for a Google Books API image thumbnail
data class Image(val novelThumbnail: String):Serializable