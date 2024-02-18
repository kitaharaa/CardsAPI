package com.kitahara.cardsapitest.presentation.main.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kitahara.cardsapitest.data.cards_dto.CardInfo
import com.kitahara.cardsapitest.presentation.main.templates.ColumnHeader
import com.kitahara.cardsapitest.presentation.main.templates.ContentBaseBack

@Composable
fun MyCards(
    title: String = "My Cards",
    isSeeAllVisible: Boolean = true,
    shouldShowLimited: Boolean = true,
    shouldUseCardForBackground: Boolean = true,
    cards: List<CardInfo>?,
    onCardClicked: (String) -> Unit,
    onAllCardsPressed: () -> Unit,
) {
    val content: @Composable (Modifier) -> Unit = {
        Column(modifier = it) {
            ColumnHeader(
                titleContent = title,
                isSeeAllVisible = isSeeAllVisible,
                onSeeAllClicked = onAllCardsPressed
            )

            val size = if (shouldShowLimited) (0..2) else 0..(cards?.size ?: 1)

            size.forEach { id ->
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

    if (shouldUseCardForBackground) {
        ContentBaseBack { content(it) }
    } else content(
        Modifier
            .fillMaxWidth()
            .padding(14.dp)
    )
}