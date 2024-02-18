package com.kitahara.cardsapitest.presentation.main.recent_transactions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kitahara.cardsapitest.data.transactions.TransactionInfo
import com.kitahara.cardsapitest.presentation.main.templates.ColumnHeader
import com.kitahara.cardsapitest.presentation.main.templates.ContentBaseBack
import com.kitahara.cardsapitest.presentation.request_state_handler.HandleRemoteContentLoading

@Composable
fun RecentTransactions(
    title: String = "Recent transactions",
    recentTransactions: List<TransactionInfo>?,
    shouldUseLimit: Boolean,
    shouldUseCardForBackground: Boolean,
    shouldShowSeeAll: Boolean = true,
    requestRestart: () -> Unit,
    onAllRecentTransactionsPressed: () -> Unit
) {
    val content: @Composable (Modifier?) -> Unit = {
        Column(
            it ?: Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(14.dp)
        ) {
            ColumnHeader(
                titleContent = title,
                onSeeAllClicked = onAllRecentTransactionsPressed,
                isSeeAllVisible = shouldShowSeeAll
            )
            HandleRemoteContentLoading(
                content = {
                    val limit = if (shouldUseLimit) 0..2 else 0..(recentTransactions?.size ?: 1)

                    limit.forEach { id ->
                        val transaction = recentTransactions?.getOrNull(id) ?: return@forEach

                        GeneralTransactionItem(
                            service = transaction.merchant?.name ?: "Unknown operation",
                            lastFour = transaction.card?.cardLast4,
                            operationSum = transaction.amount,
                            status = transaction.status,
                            logoUrl = transaction.card?.cardHolder?.logoUrl
                        )
                    }
                },

                state = recentTransactions,
                restartRequest = requestRestart
            )
        }
    }

    if (shouldUseCardForBackground)
        ContentBaseBack(content = content) else content(null)
}