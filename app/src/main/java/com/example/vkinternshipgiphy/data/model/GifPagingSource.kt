package com.example.vkinternshipgiphy.data.model

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.vkinternshipgiphy.data.GifPagerRepository
import com.example.vkinternshipgiphy.data.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.Exception

class GifPagingSource(private val service: ApiService, private val q: String): PagingSource<Int, GiphyData>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GiphyData> {

        return try {
            val position = params.key ?: ONE_POSITION
            return withContext(Dispatchers.IO) {
                val response = service.getGiphy(
                    apiKey = API_KEY,
                    rating = RATING,
                    limit = LIMIT,
                    lang = LANG,
                    offset = params.key!!,
                    q = q
                )

                val resp = response.body()
                Log.d("TAG", response.body().toString())
                LoadResult.Page(
                    data = resp!!.data,
                    prevKey = if (position == ONE_POSITION) null else position - ONE_POSITION,
                    nextKey = position + ONE_POSITION
                )
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, GiphyData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(ONE_POSITION)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(ONE_POSITION)
        }
    }

    companion object {
        const val API_KEY = "yY5LAD1stQaVe3gw1Ct1aQ9Zv2HroMVe"
        const val RATING = "g"
        const val LANG = "en"
        const val LIMIT = GifPagerRepository.NETWORK_PAGE_SIZE
        const val ONE_POSITION = 1
    }

}
