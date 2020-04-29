package com.dguido1.restrecycler.api
import com.dguido1.restrecycler.data.Novel
import com.google.gson.annotations.SerializedName

/*
    David Guido
    Computer Science 411 (Mobile Application Development)
    App 3 (REST Recycler)
    March 29, 2020
*/

// (Data) Class stores response of Google Books API call
data class NovelResponse(@SerializedName("items") val novel: List<Novel>)
