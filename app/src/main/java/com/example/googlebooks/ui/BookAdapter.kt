package com.example.googlebooks.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.googlebooks.R
import com.example.googlebooks.data.remote.model.ResponseBooks.Book
import com.example.googlebooks.databinding.RvItemBinding

class BookAdapter : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    private val items = mutableListOf<Book>()

    fun addBooks(booksItems: List<Book>) {
        items.clear()
        items.addAll(booksItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val paymentBean: Book = items[position]
        holder.bind(paymentBean)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(private var itemBinding: RvItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: Book) =
            with(itemView) {
                Glide.with(this)
                    .load(item.volumeInfo.imageLinks?.thumbnail)
                    .placeholder(R.drawable.img_placeholder)
                    .dontAnimate()
                    .into(itemBinding.ivBookImage)

                itemBinding.tvBookTitle.text = item.volumeInfo.title ?: ""
                itemBinding.tvBookAuthor.text = item.volumeInfo.authors?.toString()
            }
    }
}