package com.android.newsapp.data.model

import org.json.JSONObject

data class NewsResponse(
    val status: String,
    val articles: List<Article>
)

data class Article(
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val imageUrl: String,
    val publishedAt: String,
    val content: String
)

data class Source(
    val id: String?,
    val name: String
)

fun parseNewsResponse(jsonResponse: String): NewsResponse {
    val jsonObject = JSONObject(jsonResponse)
    val status = jsonObject.getString("status")
    val articlesArray = jsonObject.getJSONArray("articles")

    val articles = mutableListOf<Article>()
    for (i in 0 until articlesArray.length()) {
        val articleObject = articlesArray.getJSONObject(i)
        val sourceObject = articleObject.getJSONObject("source")
        val source = Source(
            id = if (sourceObject.has("id")) sourceObject.getString("id") else null,
            name = sourceObject.getString("name")
        )
        val author = articleObject.getString("author")
        val title = articleObject.getString("title")
        val description = articleObject.getString("description")
        val url = articleObject.getString("url")
        val imageUrl = articleObject.getString("urlToImage")
        val publishedAt = articleObject.getString("publishedAt")
        val content = articleObject.getString("content")

        val article = Article(source, author, title, description, url, imageUrl, publishedAt, content)
        articles.add(article)
    }

    return NewsResponse(status, articles)
}
