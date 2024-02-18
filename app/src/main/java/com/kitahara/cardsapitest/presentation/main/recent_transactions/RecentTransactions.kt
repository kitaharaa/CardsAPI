package com.kitahara.cardsapitest.presentation.main.recent_transactions

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.kitahara.cardsapitest.data.transactions.TransactionInfo
import com.kitahara.cardsapitest.presentation.main.templates.ColumnHeader
import com.kitahara.cardsapitest.presentation.main.templates.ContentBaseBack

@Composable
fun RecentTransactions(recentTransactions: List<TransactionInfo>?) {
    ContentBaseBack {
        Column(it) {
            ColumnHeader(titleContent = "Recent transactions") {
                //todo implement
            }

            if (recentTransactions?.isNotEmpty() == true) {
                (0..2).forEach { id ->
                    val transaction = recentTransactions.getOrNull(id) ?: return@forEach

                    GeneralTransactionItem(
                        service = transaction.merchant?.name ?: "Unknown operation",
                        lastFour = transaction.card?.cardLast4 ,
                        operationSum = transaction.amount,
                        status = transaction.status,
                        logoUrl = transaction.card?.cardHolder?.logoUrl
                    )
                }
            } else {//todo handle}
            }
        }
    }
}