package com.daniel.a160421076_uts.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daniel.a160421076_uts.databinding.DetailItemBinding

class DetailFragmentAdapter ( private val content: String) : RecyclerView.Adapter<DetailFragmentAdapter.ContentViewHolder>() {

    private val paragraphs: List<String> = content.split("\n")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DetailItemBinding.inflate(inflater, parent, false)
        return ContentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(paragraphs[position])
    }

    override fun getItemCount(): Int = paragraphs.size

    inner class ContentViewHolder(private val binding: DetailItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(content: String) {
            binding.txtContent.text = content
        }
    }
}