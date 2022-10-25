package com.feandrade.forecast.weather.data.repositorynews

import com.feandrade.forecast.weather.data.model.newsmodel.NewsResponse

interface RepositoryNews {
    suspend fun getSearchNews(query: String, page: Int, apiKey: String): NewsResponse
}