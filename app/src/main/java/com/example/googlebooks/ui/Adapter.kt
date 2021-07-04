package com.example.googlebooks.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.googlebooks.R
import com.example.googlebooks.data.model.ResponseBooks
import com.example.googlebooks.databinding.RvItemBinding

class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private val items = mutableListOf<ResponseBooks.Items>()

    fun addBooks(booksItems: List<ResponseBooks.Items>) {
        items.addAll(booksItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val paymentBean: ResponseBooks.Items = items[position]
        holder.bind(paymentBean)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(private var itemBinding: RvItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: ResponseBooks.Items) =
            with(itemView) {
                Glide.with(this)
                    .asBitmap()
                    .load(item.volumeInfo.imageLinks.thumbnail)
                    .transform(CenterCrop(), RoundedCorners(20))
                    .error(R.drawable.img_placeholder)
                    .override(400)
                    .into(itemBinding.ivBookImage)

                itemBinding.tvBookTitle.text = item.volumeInfo.title
                itemBinding.tvBookAuthor.text = item.volumeInfo.authors.toString()

            }
    }
}