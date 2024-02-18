package com.kitahara.cardsapitest.presentation.main.service_icon

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.kitahara.cardsapitest.R
import com.kitahara.cardsapitest.presentation.main.templates.CustomAsyncImage

@Composable
fun ServiceIcon(modifier: Modifier, size: Dp, iconSize: Dp, url: String, onClick: () -> Unit = {}) {
    IconButton(
        modifier = modifier
            .size(size)
            .clip(CircleShape),
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Color.White
        )
    ) {
        CustomAsyncImage(
            modifier = Modifier.size(iconSize),
            url = url,
            description = stringResource(R.string.service_logo)
        )
    }
}
