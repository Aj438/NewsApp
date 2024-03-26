package com.android.newsapp.ui.screens.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.newsapp.utils.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    noteOrder: OrderType = OrderType.Descending,
    onOrderChange: (OrderType) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        DefaultRadioButton(
            text = "Ascending",
            selected = noteOrder is OrderType.Ascending,
            onSelect = {
                onOrderChange( OrderType.Ascending)
            }
        )
        Spacer(modifier = Modifier.width(8.dp))
        DefaultRadioButton(
            text = "Descending",
            selected = noteOrder is OrderType.Descending,
            onSelect = {
                onOrderChange( OrderType.Descending)
            }
        )
    }
}