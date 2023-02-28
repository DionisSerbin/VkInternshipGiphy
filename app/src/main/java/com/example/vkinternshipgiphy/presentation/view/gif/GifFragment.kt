package com.example.vkinternshipgiphy.presentation.view.gif

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.vkinternshipgiphy.R
import com.example.vkinternshipgiphy.data.model.GiphyData
import com.example.vkinternshipgiphy.presentation.viewmodel.MainViewModel

class GifFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gif, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainViewModel =
            ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        val giphyData: GiphyData = mainViewModel.giphyData.value!!

        val height = giphyData.images.original.height

        val heightTextView = view.findViewById<TextView>(R.id.height_holder)

        heightTextView.text = height

        val width = giphyData.images.original.width

        val widthTextView = view.findViewById<TextView>(R.id.width_holder)

        widthTextView.text = width
    }
}