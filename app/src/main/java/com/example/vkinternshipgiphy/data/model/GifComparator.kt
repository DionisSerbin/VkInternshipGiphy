package com.example.vkinternshipgiphy.data.model

import androidx.recyclerview.widget.DiffUtil

class GifComparator: DiffUtil.ItemCallback<GiphyData>()  {
    override fun areItemsTheSame(oldItem: GiphyData, newItem: GiphyData): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: GiphyData, newItem: GiphyData): Boolean {
        return oldItem == newItem
    }
}