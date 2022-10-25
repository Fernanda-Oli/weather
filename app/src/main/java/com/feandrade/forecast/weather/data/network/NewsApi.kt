package com.feandrade.forecast.weather.data.network

import com.feandrade.forecast.weather.data.model.newsmodel.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String
    ): NewsResponse
}