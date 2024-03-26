package com.android.newsapp.utils

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}