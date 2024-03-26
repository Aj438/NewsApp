package com.android.newsapp.ui.screens.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppElevatedCard(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: CardColors = CardDefaults.elevatedCardColors(
        containerColor = Color.White
    ),
    shape: Shape = RoundedCornerShape(8.dp),
    elevation: CardElevation = CardDefaults.elevatedCardElevation(
        defaultElevation = 8.dp,
        pressedElevation = 2.dp,
        focusedElevation = 2.dp,
        hoveredElevation = 4.dp,
        draggedElevation = 8.dp,
        disabledElevation = 2.dp
    ),
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {

    if (onClick != null)
        ElevatedCard(
            modifier = modifier,
            enabled = enabled,
            colors = colors,
            shape = shape,
            elevation = elevation,
            onClick = onClick,
            content = content
        )
    else
        ElevatedCard(
            modifier = modifier,
            shape = shape,
            colors = colors,
            elevation = elevation,
            content = content
        )
}