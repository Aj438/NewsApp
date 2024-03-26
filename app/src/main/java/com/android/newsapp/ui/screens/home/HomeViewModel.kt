package com.android.newsapp.ui.screens.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.android.newsapp.data.model.Article
import com.android.newsapp.data.repository.NewsRepositoryImp
import com.android.newsapp.utils.NetworkResult
import com.android.newsapp.domain.repository.NewsRepository
import com.android.newsapp.utils.OrderType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    private val _newsState = MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading)
    val newsState = _newsState.asStateFlow()

    private val _articleList = mutableStateListOf<Article>()
    val articleList = MutableStateFlow(_articleList)

    private val _orderType = MutableStateFlow<OrderType>(OrderType.Ascending)
    val orderType = _orderType.asStateFlow()

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            when (val result = newsRepository.getNews()) {
                is NetworkResult.Loading -> {
                    _newsState.value = NetworkResult.Loading
                }

                is NetworkResult.Success -> {
                    _newsState.value = NetworkResult.Success("")
                    _articleList.clear()
                    _articleList.addAll(
                        result.data.articles.sortedBy { it.publishedAt }
                    )
                }

                is NetworkResult.Error -> {
                    _newsState.value = NetworkResult.Error(result.message)
                }
            }
        }
    }

    fun changeOrder() {
        _articleList.reverse()
        when (orderType.value) {
            OrderType.Ascending -> {
                _orderType.value = OrderType.Descending
            }

            OrderType.Descending -> {
                _orderType.value = OrderType.Ascending
            }
        }
    }

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                return HomeViewModel(
                    NewsRepositoryImp()
                ) as T
            }
        }
    }
}