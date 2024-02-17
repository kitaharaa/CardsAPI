package com.kitahara.cardsapitest.presentation.main.templates

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import coil.compose.SubcomposeAsyncImage

@Composable
fun CustomAsyncImage(
    modifier : Modifier = Modifier,
    url: String,
    description: String
) {
    SubcomposeAsyncImage(
        modifier = modifier.clip(CircleShape),
        model = url,
        contentDescription = description,
        loading = {
            CircularProgressIndicator()
        }
    )
}

