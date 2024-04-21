package com.daniel.a160421076_uts.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daniel.a160421076_uts.databinding.DetailItemBinding

class DetailAdapter (private val content: String) : RecyclerView.Adapter<DetailAdapter.ContentViewHolder>() {

    private val paragraphs: List<String> = splitWords(content, 100)

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

    private fun splitWords(content: String, wordsPerParagraph: Int): List<String> {
        val words = content.split("\\s+".toRegex())
        val paragraphs = mutableListOf<String>()
        var currentParagraph = ""
        var wordCount = 0

        for (word in words) {
            currentParagraph += "$word "
            wordCount++
            if (wordCount >= wordsPerParagraph) {
                paragraphs.add(currentParagraph.trim())
                currentParagraph = ""
                wordCount = 0
            }
        }

        if (currentParagraph.isNotEmpty()) {
            paragraphs.add(currentParagraph.trim())
        }

        return paragraphs
    }
}