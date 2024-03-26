package com.android.newsapp.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import com.android.newsapp.R
import com.android.newsapp.ui.theme.Accent
import com.android.newsapp.ui.theme.BlackPure
import com.android.newsapp.ui.theme.BlackVariant
import com.android.newsapp.ui.theme.FabPadding
import com.android.newsapp.ui.theme.GeneralTextLineHeight
import com.android.newsapp.ui.theme.GeneralTextSize
import com.android.newsapp.ui.theme.HalfPadding
import com.android.newsapp.ui.theme.ItemHeadingTextSize
import com.android.newsapp.ui.theme.SectionHeadingTextSize
import com.android.newsapp.ui.theme.White
import com.android.newsapp.ui.theme.WhitePure


@Composable
fun SectionHeadingText(
    text: String,
    modifier: Modifier = Modifier,
) {
    val textColor = if (isSystemInDarkTheme()) {
        WhitePure
    } else {
        BlackPure
    }

    Text(
        text = text,
        modifier = modifier.padding(FabPadding),
        fontFamily = FontFamily(Font(resId = R.font.newsreader_extra_bold)),
        color = textColor,
        fontSize = SectionHeadingTextSize,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
    )
}

@Composable
fun ItemHeadingText(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = WhitePure,
) {
    Text(
        text = text,
        fontFamily = FontFamily(Font(resId = R.font.newsreader_bold)),
        color = textColor,
        fontSize = ItemHeadingTextSize,
        overflow = TextOverflow.Ellipsis,
        maxLines = 2,
        modifier = modifier.padding(all = HalfPadding),
        style = MaterialTheme.typography.titleLarge.copy(
            shadow = Shadow(
                color = BlackVariant,
                offset = Offset(1f, 1f),
                blurRadius = 5f
            )
        )
    )
}

@Composable
fun GeneralText(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    textColor: Color = White,
) {
    Text(
        text = text,
        fontFamily = FontFamily(Font(resId = R.font.newsreader_light)),
        color = textColor,
        fontSize = GeneralTextSize,
        modifier = modifier.padding(all = HalfPadding),
        lineHeight = GeneralTextLineHeight,
        style = MaterialTheme.typography.titleMedium.copy(
            shadow = Shadow(
                color = BlackVariant,
                offset = Offset(1f, 1f),
                blurRadius = 5f,
            )
        ),
        maxLines = maxLines,
    )
}

@Composable
fun ShowLoading(sectionHeight: Dp) {
    Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(sectionHeight)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
              .wrapContentSize()
              .align(Alignment.Center),
            color = Accent,
        )
    }
}

@Composable
fun LoadErrorPlaceholder(modifier: Modifier = Modifier, message: String, onRetry: () -> Unit) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.bg_placeholder),
            contentDescription = null,
            modifier = Modifier
              .align(Alignment.Center)
              .fillMaxSize()
        )
        Text(
            modifier = Modifier
              .padding(all = FabPadding)
              .align(Alignment.BottomCenter),
            text = message,
            color = Color.Red,
        )
        Button(
            onClick = onRetry, modifier = Modifier
                .align(Alignment.Center)
        ) {
            Text(
                text = "Retry",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}