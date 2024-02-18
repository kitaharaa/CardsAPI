package com.kitahara.cardsapitest.presentation.card.options

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardManagementOption(actionName: String, icon: Int, onClick: () -> Unit) {
    Card {
        Column(
            modifier = Modifier
                .height(72.dp)
                .width(80.dp)
                .clickable {
                    onClick()
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                modifier = Modifier.padding(vertical = 12.dp),
                painter = painterResource(id = icon),
                contentDescription = "Card management option"
            )

            Text(
                modifier = Modifier,
                text = actionName,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}