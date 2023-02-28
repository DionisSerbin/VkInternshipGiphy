package com.example.vkinternshipgiphy.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.vkinternshipgiphy.data.model.GifPagingSource
import com.example.vkinternshipgiphy.data.model.GiphyData
import com.example.vkinternshipgiphy.data.network.ApiService
import com.example.vkinternshipgiphy.domain.GetGifDataUseCase


class GifPagerRepository constructor(private val service: ApiService): GetGifDataUseCase {

    override fun getGifsList(q: String): LiveData<PagingData<GiphyData>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = true,
                initialLoadSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = {
                GifPagingSource(service, q)
            }
            , initialKey = 1
        ).liveData
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 25
    }
}