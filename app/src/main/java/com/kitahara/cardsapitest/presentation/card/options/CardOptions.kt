package com.kitahara.cardsapitest.presentation.card.options

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.kitahara.cardsapitest.R

@Composable
fun CardOptions(
    onDetails: () -> Unit,
    onLock: () -> Unit,
    onTerminate: () -> Unit,
) {
    Card(elevation = CardDefaults.cardElevation(10.dp)) {

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            CardManagementOption(
                actionName = "Details",
                icon = R.drawable.eye,
                onClick = onDetails
            )

            CardManagementOption(
                actionName = "Lock",
                icon = R.drawable.lock,
                onClick = onLock
            )

            CardManagementOption(
                actionName = "Terminate",
                icon = R.drawable.credit_card_close,
                onClick = onTerminate
            )
        }
    }
}