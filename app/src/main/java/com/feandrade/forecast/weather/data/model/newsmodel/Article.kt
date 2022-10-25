package com.feandrade.forecast.weather.data.model.newsmodel

import java.io.Serializable


data class Article(
    val id: Int? = null,
    val source: Source?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?,
    var userId: Long
): Serializable