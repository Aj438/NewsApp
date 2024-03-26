package com.android.newsapp.ui.screens.components


import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    title: String = "",
    containerColor: Color = Color.Transparent,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    titleContentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    backIcon: ImageVector? = Icons.Default.ArrowBack,
    onBack: () -> Unit = {},
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        title = {
            Text(text =title , style = MaterialTheme.typography.titleMedium)
        },
        navigationIcon = {
            backIcon?.let {
                IconButton(onClick =onBack) {
                    Icon(imageVector = it, contentDescription = null)
                }
            }
        },
        modifier = modifier,
        actions = actions,
        scrollBehavior = scrollBehavior,
        windowInsets = windowInsets
    )
}