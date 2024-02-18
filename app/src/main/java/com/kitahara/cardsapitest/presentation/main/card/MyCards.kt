package com.kitahara.cardsapitest.presentation.main.card

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.kitahara.cardsapitest.data.cards_dto.CardInfo
import com.kitahara.cardsapitest.presentation.main.templates.ColumnHeader
import com.kitahara.cardsapitest.presentation.main.templates.ContentBaseBack

@Composable
fun MyCards(cards: List<CardInfo>?, onCardClicked: (String) -> Unit) {
    ContentBaseBack {
        Column(modifier = it) {
            ColumnHeader(titleContent = "My Cards", onSeeAllClicked = {})

            (0..2).forEach { id ->
                val item = cards?.getOrNull(id) ?: return@forEach

                CardColumnItem(
                    name = item.cardName.toString(),
                    lastFour = item.cardLast4 ?: "0000",
                    logoUrl = item.cardHolder?.logoUrl.toString()
                ) {
                    if (item.id != null)
                        onCardClicked(item.id)
                }
            }
        }
    }
}