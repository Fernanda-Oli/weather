package com.feandrade.forecast.weather.data.repositorynews

import com.feandrade.forecast.weather.data.model.newsmodel.NewsResponse
import com.feandrade.forecast.weather.data.network.NewsApi

class RepositoryNewsImpl(private val newsApi: NewsApi) : RepositoryNews {
    override suspend fun getSearchNews(query: String, page: Int, apiKey: String): NewsResponse =
        newsApi.searchNews(query, page, apiKey)
}