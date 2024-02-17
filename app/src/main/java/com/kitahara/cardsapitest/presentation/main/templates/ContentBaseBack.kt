package com.kitahara.cardsapitest.presentation.main.templates

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ContentBaseBack(content: @Composable (Modifier) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        content(
            Modifier
                .fillMaxWidth()
                .padding(14.dp)
        )
    }
}