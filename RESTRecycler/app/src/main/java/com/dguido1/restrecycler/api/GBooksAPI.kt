package com.dguido1.restrecycler.api

/*
    David Guido
    Computer Science 411 (Mobile Application Development)
    App 3 (REST Recycler)
    March 29, 2020
*/

// Class handles Google Books API call content.
data class GBooksAPI(private val search: String) {

    private val base = "https://www.googleapis.com/books/v1/volumes?q="   // Base URL
    private val subject = "subject:"                                      // To specify a subject

    val getUrl: String
        get() = base + subject + search    // Concat subject and search (param) to base str
}
