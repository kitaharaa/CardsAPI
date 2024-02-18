@file:OptIn(ExperimentalMaterial3Api::class)

package com.kitahara.cardsapitest.presentation.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kitahara.cardsapitest.R
import com.kitahara.cardsapitest.presentation.SharedViewModel
import com.kitahara.cardsapitest.presentation.main.card.MyCards
import com.kitahara.cardsapitest.presentation.main.info.AccountInfo
import com.kitahara.cardsapitest.presentation.main.recent_transactions.RecentTransactions

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: SharedViewModel,
    navigateCardDetails: (String) -> Unit
) {
    val cards by viewModel.cardsFlow.collectAsState()
    val recentTransactions by viewModel.transactionsFlow.collectAsState()

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

            MyCards(cards, navigateCardDetails)

            RecentTransactions(recentTransactions = recentTransactions)
        }
    }
}