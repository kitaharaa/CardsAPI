package com.kitahara.cardsapitest.presentation.main.card

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.kitahara.cardsapitest.presentation.main.templates.ColumnHeader
import com.kitahara.cardsapitest.presentation.main.templates.ContentBaseBack

@Composable
fun MyCards() {
    ContentBaseBack {
        Column(modifier = it) {
            ColumnHeader(titleContent = "My Cards", onSeeAllClicked = {})

            (0..2).forEach { _ ->
                //todo enter list
                CardColumnItem(
                    name = "Slack",
                    lastFour = "5562",
                    logoUrl = "https://spendbase.s3.eu-central-1.amazonaws.com/users/4/picture.png%3F1676305861461"
                ) {

                }
            }
        }
    }
}