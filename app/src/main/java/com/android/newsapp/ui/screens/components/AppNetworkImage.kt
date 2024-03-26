package com.android.newsapp.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter
import com.android.newsapp.R

@Composable
fun AppNetworkImage(
    modifier: Modifier = Modifier,
    model: Any? = null,
    errorImage: Painter? = painterResource(id = R.drawable.error_404),
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.FillBounds,
    colorFilter: ColorFilter? = null,
    alpha: Float = 1f
) {

    // This variable states if the painter is loading from the URL or is already loaded
    val isLoading = remember { mutableStateOf(true) }

    // This is the AsyncPainterImage
    val painter = rememberAsyncImagePainter(
        model = model,
        onError = {

            // Loading state is turned back to false
            isLoading.value = false
        },
        onSuccess = {

            // Loading state is turned back to false
            isLoading.value = false
        },
        onLoading = {

            // Loading State is turned to true since the loading is started
            isLoading.value = true
        },
        error = errorImage
    )

    // This is the Fetched image which will be shown when the Image is fetched from the Server
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier.appShimmerAnimation(isLoading.value),
        contentScale = contentScale,
        alpha = alpha,
        colorFilter = colorFilter,
    )
}