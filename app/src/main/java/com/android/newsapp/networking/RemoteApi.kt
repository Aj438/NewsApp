package com.android.newsapp.networking

import com.android.newsapp.data.model.NewsResponse
import com.android.newsapp.data.model.parseNewsResponse
import com.android.newsapp.utils.NetworkResult
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URI
import java.net.URL

object RemoteApi {
    private const val BASE_URL =
        "https://candidate-test-data-moengage.s3.amazonaws.com/Android/news-api-feed/staticResponse.json"

    fun getNews(): NetworkResult<NewsResponse> {
            try {
                val url: URL = URI.create(BASE_URL).toURL()
                val connection: HttpURLConnection = url.openConnection() as HttpURLConnection

                //Request method: GET
                connection.requestMethod = "GET"
                connection.setRequestProperty("Content-Type", "application/json")
                connection.setRequestProperty("Accept", "application/json")
                connection.connectTimeout = 10000
                connection.readTimeout = 10000
                connection.doInput = true

                // Response code
                val responseCode: Int = connection.responseCode

                return if (responseCode == HttpURLConnection.HTTP_OK) {
                    // Read and print the response data
                    val reader = BufferedReader(InputStreamReader(connection.inputStream))
                    var line: String?
                    val response = StringBuilder()

                    while (reader.readLine().also { line = it } != null) {
                        response.append(line)
                    }
                    reader.close()
                    connection.disconnect()
                    NetworkResult.Success(parseNewsResponse(response.toString()))
                } else {
                    connection.disconnect()
                    NetworkResult.Error("Unable to fetch data from the API")
                }
            }
            catch (e: Exception) {
               return NetworkResult.Error(e.localizedMessage?:"")
            }
    }
}