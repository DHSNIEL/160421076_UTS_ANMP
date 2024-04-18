package com.daniel.a160421076_uts.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.daniel.a160421076_uts.model.Berita
import com.daniel.a160421076_uts.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.Callback
import java.io.File

class NewsAdapter (private val onItemClick: (Berita) -> Unit): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private var newsList = listOf<Berita>()

    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.image_item)
        val txtTitle: TextView = view.findViewById(R.id.txtTitleItem)
        val txtUsername: TextView = view.findViewById(R.id.txtUserItem)
        val txtDeskripsi: TextView = view.findViewById(R.id.txtDeskripsi)
        val btnRead: Button = view.findViewById(R.id.btnBaca)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.home_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentNews = newsList[position]
        Picasso.get().load(currentNews.gambar).into(holder.img)
        Picasso.get().setLoggingEnabled(true)
//        Picasso.get()
//            .load( currentNews.gambar)
//            .placeholder(R.drawable.round_person_24)
//            .error(R.drawable.outline_history_24)
//            .resize(200, 200)
//            .into(holder.img)
//        Glide.with(holder.itemView.context)
//            .load(currentNews.gambar)
//            .into(holder.img)
        holder.txtTitle.text = currentNews.judul
        holder.txtUsername.text = "@" + currentNews.username
        holder.txtDeskripsi.text = currentNews.deskripsi
        holder.btnRead.setOnClickListener { onItemClick(currentNews) }
    }

    fun updateNewsList(newNewsList: List<Berita>) {
        newsList = newNewsList
        notifyDataSetChanged()
    }
}