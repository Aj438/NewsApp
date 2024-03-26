package com.android.newsapp.ui.screens.home

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.android.newsapp.data.model.Article
import com.android.newsapp.ui.screens.components.AppNetworkImage
import com.android.newsapp.ui.screens.components.AppScaffold
import com.android.newsapp.ui.screens.components.DynamicThemePrimaryColorsFromImage
import com.android.newsapp.ui.screens.components.GeneralText
import com.android.newsapp.ui.screens.components.ItemHeadingText
import com.android.newsapp.ui.screens.components.MinContrastOfPrimaryVsSurface
import com.android.newsapp.ui.screens.components.OrderSection
import com.android.newsapp.ui.screens.components.contrastAgainst
import com.android.newsapp.ui.screens.components.rememberDominantColorState
import com.android.newsapp.utils.OrderType
import com.google.firebase.Firebase
import com.google.firebase.messaging.messaging
import kotlinx.coroutines.tasks.await


@Composable
fun HomeScreen(
    listOfItems: SnapshotStateList<Article>,
    orderType: OrderType,
    onNewsDetails: (String) -> Unit,
    onOrderChange: (OrderType) -> Unit,
) {
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // permission granted
        } else {
            // permission denied, but should I show a rationale?
        }
    }

    val context = LocalContext.current
    LaunchedEffect(Unit){
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // permission granted
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }
    LaunchedEffect(Unit){
       val token= Firebase.messaging.token.await()
        Log.d("TAG", "HomeScreen: $token")
    }
    AppScaffold(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "News",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            OrderSection(
                modifier = Modifier.fillMaxWidth(),
                noteOrder = orderType,
                onOrderChange = onOrderChange
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(0.dp),
            ) {
                items(listOfItems) { article: Article ->
                    CardItem(article = article) {
                        onNewsDetails(article.url)
                    }
                }
            }
        }
    }
}

@Composable
fun CardItem(article: Article, onClick: () -> Unit) {
    val surfaceColor = MaterialTheme.colorScheme.surface
    val dominantColorState = rememberDominantColorState { color ->
        // We want a color which has sufficient contrast against the surface color
        color.contrastAgainst(surfaceColor) >= MinContrastOfPrimaryVsSurface
    }
    DynamicThemePrimaryColorsFromImage(dominantColorState) {
        LaunchedEffect(article) {
            dominantColorState.updateColorsFromImageUrl(
                article.imageUrl, null
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .clickable { onClick() }
        ) {
            AppNetworkImage(
                modifier = Modifier
                    .aspectRatio(16 / 9f)
                    .padding(16.dp),
                model = article.imageUrl,
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                ItemHeadingText(
                    text = article.title,
                    modifier = Modifier.fillMaxWidth()
                )
                GeneralText(
                    text = article.description,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 3,
                )

            }

        }
    }
}
