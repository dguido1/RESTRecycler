package com.dguido1.restrecycler.api

import com.dguido1.restrecycler.data.Novel
import com.google.gson.GsonBuilder
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

/*
    David Guido
    Computer Science 411 (Mobile Application Development)
    App 3 (REST Recycler)
    March 29, 2020
*/

// API Service class.
class Service {

    companion object {

        // Get novels of X subject using Google Books API
        fun getNovelsBySubject(search: String, callback: ((List<Novel>) -> Unit)) {
            val url = GBooksAPI(search).getUrl
            val request = Request.Builder().url(url).build()
            val client = OkHttpClient()
            client.newCall(request).enqueue(object: okhttp3.Callback {

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body()?.string()
                    val gson = GsonBuilder().create()

                    body?.let {
                        val bookResponse = gson.fromJson(it, NovelResponse::class.java)
                        callback(bookResponse.novel)
                    }
                }

                // Fail condition
                override fun onFailure(call: Call, e: IOException) {
                    println("Fail")
                }
            })
        }
    }
}