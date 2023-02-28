package com.example.vkinternshipgiphy.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.vkinternshipgiphy.data.GifPagerRepository
import com.example.vkinternshipgiphy.data.model.GiphyData
import com.example.vkinternshipgiphy.data.network.ApiService

class MainViewModel : ViewModel() {

    private val retrofitService = ApiService.create()
    private val gifPagerRepository = GifPagerRepository(retrofitService)

    val errorMessage = MutableLiveData<String>()

    private var _giphyData = MutableLiveData<GiphyData?>()
    val giphyData = _giphyData

    fun saveGiphyData(giphyData: GiphyData) {
        _giphyData.value = giphyData
    }


    fun getGifsList(q: String): LiveData<PagingData<GiphyData>> {
        return gifPagerRepository.getGifsList(q).cachedIn(viewModelScope)
    }
}