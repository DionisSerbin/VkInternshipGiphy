package com.example.vkinternshipgiphy.presentation.view.main

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.vkinternshipgiphy.R
import com.example.vkinternshipgiphy.data.model.GiphyData
import com.example.vkinternshipgiphy.presentation.viewmodel.MainViewModel
import com.google.android.material.internal.ContextUtils.getActivity


class GifViewHolder(val view: View, val viewModel: ViewModel): RecyclerView.ViewHolder(view) {


    private val image by lazy { view.findViewById<ImageView>(R.id.imageview)}

    private val imageLoader by lazy { Glide.with(image) }

    fun bind(giphy: GiphyData) {

        val url = giphy.images.original.url

        image.setOnClickListener {
            (viewModel as MainViewModel).saveGiphyData(giphy)
            Navigation.findNavController(image).navigate(R.id.action_navigation_home_to_gifFragment)
        }

        imageLoader
            .asGif()
            .apply(RequestOptions.placeholderOf(R.drawable.no_image))
            .load(url)
            .fitCenter()
            .downsample(DownsampleStrategy.CENTER_INSIDE)
            .dontTransform()

            .into(image)
    }
}