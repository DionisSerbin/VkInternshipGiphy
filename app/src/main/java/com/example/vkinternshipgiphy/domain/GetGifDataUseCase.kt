package com.example.vkinternshipgiphy.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.vkinternshipgiphy.data.model.GiphyData

interface GetGifDataUseCase {
    fun getGifsList(q: String): LiveData<PagingData<GiphyData>>
}