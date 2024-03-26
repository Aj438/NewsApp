package com.android.newsapp.domain.repository

import com.android.newsapp.data.model.NewsResponse
import com.android.newsapp.utils.NetworkResult

interface NewsRepository {
    suspend fun getNews(): NetworkResult<NewsResponse>
}