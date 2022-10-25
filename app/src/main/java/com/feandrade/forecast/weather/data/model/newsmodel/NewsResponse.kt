package com.feandrade.forecast.weather.data.model.newsmodel

import java.io.Serializable

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
): Serializable