package com.kitahara.cardsapitest.presentation.main.recent_transactions

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.kitahara.cardsapitest.presentation.main.templates.ColumnHeader
import com.kitahara.cardsapitest.presentation.main.templates.ContentBaseBack

@Composable
fun RecentTransactions() {
    ContentBaseBack {
        Column(it) {
            ColumnHeader(titleContent = "Recent transactions") {
                //todo implement
            }

            (0..2).forEach { _ ->
                GeneralTransactionItem(
                    service = "Slack",
                    lastFour = "4343",
                    operationSum = 112.2f,
                    status = "Success",
                    logoUrl = "https://spendbase.s3.eu-central-1.amazonaws.com/users/4/picture.png%3F1676305861461",
                )
            }
        }
    }
}