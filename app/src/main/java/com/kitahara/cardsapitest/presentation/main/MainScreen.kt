package com.kitahara.cardsapitest.presentation.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kitahara.cardsapitest.R
import com.kitahara.cardsapitest.data.RequestType
import com.kitahara.cardsapitest.data.cards_dto.CardInfo
import com.kitahara.cardsapitest.data.transactions.TransactionInfo
import com.kitahara.cardsapitest.presentation.main.card.MyCards
import com.kitahara.cardsapitest.presentation.main.info.AccountInfo
import com.kitahara.cardsapitest.presentation.main.recent_transactions.RecentTransactions

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    cards: List<CardInfo>?,
    transactions: List<TransactionInfo>?,
    navigateCardDetails: (String) -> Unit,
    onAllCardsPressed: (RequestType) -> Unit,
    restartRequest: (RequestType) -> Unit,
    onAllRecentTransactionsPressed: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CustomTopAppBar()
        }

    ) { appBarPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(appBarPadding)
                .padding(horizontal = 15.dp),
            verticalArrangement = Arrangement.spacedBy(
                10.dp
            )
        ) {
            AccountInfo(
                currency = "USD",
                sum = 100000f,
                countryFlag = R.drawable.united_states_of_america
            )

            MyCards(
                cards = cards,
                onCardClicked = navigateCardDetails,
                onAllCardsPressed = { onAllCardsPressed(RequestType.Cards) },
                restartRequest = { restartRequest(RequestType.Cards) },
            )

            RecentTransactions(
                recentTransactions = transactions,
                onAllRecentTransactionsPressed = onAllRecentTransactionsPressed,
                shouldUseLimit = true,
                shouldShowSeeAll = true,
                requestRestart = { restartRequest(RequestType.Transactions) },
                shouldUseCardForBackground = true
            )
        }
    }
}