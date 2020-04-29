package com.dguido1.restrecycler.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dguido1.restrecycler.R
import com.dguido1.restrecycler.api.Service
import com.dguido1.restrecycler.data.Novel
import com.dguido1.restrecycler.ui.NovelAdapter

/*
    David Guido
    Computer Science 411 (Mobile Application Development)
    App 3 (REST Recycler)
    March 29, 2020
*/

// Main activity class
class MainActivity : AppCompatActivity() {

    private lateinit var novels: ArrayList<Novel>           // Array List of sci-fi novels
    private lateinit var recyclerView: RecyclerView         // Recycler View R. reference
    private lateinit var progressBar: ProgressBar           // Progress bar (loading) R. reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handleSetup()
    }

    // Setup Recycler View
    private fun handleSetup() {
        recyclerView = findViewById(R.id.main_recycler_view)
        novels = arrayListOf()
        progressBar = findViewById(R.id.main_progress_bar)

        val adapter = NovelAdapter(novels) {
            // Callback gets item pos.
            //  i.e.
            //      Area to handle (go to next activity) operation
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // Populate recycler view with books from G. Books Api, matching "sci-fi" novels
        getNovelsBySubject("science_fiction")
    }

    // Get novels of X subject, using Google Books API
    private fun getNovelsBySubject(search: String) {
        progressBar.visibility = View.VISIBLE           // Show progress bar
        progressBar.animate()                           // Animate progress bar

        Service.getNovelsBySubject(search) {            // Call getNovelsBySubject
            this.novels.clear()
            recyclerView.recycledViewPool.clear()       // Clear RV pool
            this.novels.addAll(ArrayList(it))           // Add novels to array list

            // Cause code to get executed on the Main Thread
            runOnUiThread {
                recyclerView.adapter!!.notifyDataSetChanged()
                progressBar.animate().cancel()
                progressBar.visibility = View.GONE
                println("TAG: Book Count: ${it.size}")
            }
        }
    }
}