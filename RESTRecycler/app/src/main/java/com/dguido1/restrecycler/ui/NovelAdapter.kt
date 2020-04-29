package com.dguido1.restrecycler.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dguido1.restrecycler.data.Novel
import com.dguido1.restrecycler.R
import kotlinx.android.synthetic.main.novel_item.view.*

/*
    David Guido
    Computer Science 411 (Mobile Application Development)
    App 3 (REST Recycler)
    March 29, 2020
*/

// Custom Novel Adapter class for recycler view
class NovelAdapter(private val books: ArrayList<Novel>, private val callback: ((Int) -> Unit)) : RecyclerView.Adapter<NovelAdapter.MainViewHolder>() {

    private lateinit var context: Context

    // View holder initialization calls this function once
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.novel_item, parent, false)
        return MainViewHolder(row).listen { pos, _ ->
            callback(pos)
        }
    }

    // For each row in recycler view
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(books[position])
    }

    // Called once during full duration of RV life.
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }

    // Rows (items) in RV
    override fun getItemCount(): Int = books.size

    // Unique ID for each item
    override fun getItemId(position: Int): Long = position.toLong()

    // RV View Holder
    inner class MainViewHolder(private val row: View): RecyclerView.ViewHolder(row) {
        fun bind(book: Novel) {
            row.title_text_view.text = book.info.title
            row.year_text_view.text = book.info.pubDate
            row.author_text_view.text = book.info.authors[0]

        }
    }
}