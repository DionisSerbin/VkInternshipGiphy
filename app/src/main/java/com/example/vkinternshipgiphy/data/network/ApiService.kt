package com.example.vkinternshipgiphy.data.network

import com.example.vkinternshipgiphy.data.model.GiphyResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("gifs/search")
    suspend fun getGiphy(
        @Query("api_key") apiKey: String,
        @Query("q") q: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("rating") rating: String,
        @Query("lang") lang: String
    ): Response<GiphyResponse>


    companion object {
        fun create() : ApiService {
            val interceptor= HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val restrofit = Retrofit.Builder().apply {
                client(client)
                addConverterFactory(GsonConverterFactory.create())
                baseUrl("https://api.giphy.com/v1/")
            }.build()

            return restrofit.create(ApiService::class.java)
        }

    }
}