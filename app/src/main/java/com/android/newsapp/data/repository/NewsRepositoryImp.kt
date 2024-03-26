package com.android.newsapp.data.repository

import com.android.newsapp.data.model.NewsResponse
import com.android.newsapp.utils.NetworkResult
import com.android.newsapp.domain.repository.NewsRepository
import com.android.newsapp.networking.RemoteApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepositoryImp:NewsRepository {
    override suspend fun getNews(): NetworkResult<NewsResponse> {
        return withContext(Dispatchers.IO){
            RemoteApi.getNews()
        }
    }
}