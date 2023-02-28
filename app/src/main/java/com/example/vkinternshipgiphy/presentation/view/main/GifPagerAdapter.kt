package com.example.vkinternshipgiphy.presentation.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.paging.PagingDataAdapter
import com.example.vkinternshipgiphy.R

import com.example.vkinternshipgiphy.data.model.GifComparator
import com.example.vkinternshipgiphy.data.model.GiphyData

class GifPagerAdapter(private val viewModel: ViewModel): PagingDataAdapter<GiphyData, GifViewHolder>(GifComparator()) {

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        val gifLink = getItem(position)
        if (gifLink != null) {
            holder.bind(gifLink)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gif_item, parent, false)
        return GifViewHolder(view, viewModel)
    }
}



