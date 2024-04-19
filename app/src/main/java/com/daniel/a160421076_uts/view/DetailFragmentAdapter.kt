package com.daniel.a160421076_uts.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daniel.a160421076_uts.databinding.DetailItemBinding

class DetailFragmentAdapter ( private val content: String) : RecyclerView.Adapter<DetailFragmentAdapter.ContentViewHolder>() {

    private val paragraphs: List<String> = content.split("\n")
//    private val itemsPerPage = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DetailItemBinding.inflate(inflater, parent, false)
        return ContentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(paragraphs[position])
//        val startIndex = position * itemsPerPage
//        val endIndex = minOf(startIndex + itemsPerPage, paragraphs.size)
//        val contentToShow = paragraphs.subList(startIndex, endIndex).joinToString("\n\n")
//        holder.bind(contentToShow)
    }

//    override fun getItemCount(): Int = (paragraphs.size + itemsPerPage - 1) / itemsPerPage
    override fun getItemCount(): Int = paragraphs.size

    inner class ContentViewHolder(private val binding: DetailItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(content: String) {
            binding.txtContent.text = content
        }
    }
}