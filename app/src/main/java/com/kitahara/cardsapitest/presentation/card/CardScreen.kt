@file:OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)

package com.kitahara.cardsapitest.presentation.card

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kitahara.cardsapitest.R
import com.kitahara.cardsapitest.presentation.SharedViewModel
import com.kitahara.cardsapitest.presentation.card.options.CardOptions
import com.kitahara.cardsapitest.presentation.card.transactions.SpecificCardTransactions
import com.kitahara.cardsapitest.presentation.main.service_icon.CustomIcon

@Composable
fun CardScreen(
    modifier: Modifier = Modifier,
    serviceIconUrl: String = "https://spendbase.s3.eu-central-1.amazonaws.com/users/4/picture.png%3F1676305861461",
    serviceName: String = "Slack",
    cardsLastFour: String = "5531",
    viewModel: SharedViewModel,
    cardId: String,
    onCardDetailClicked: () -> Unit,
    navBack: () -> Unit
) {
    viewModel.extractTransactionWithCardId(cardId)

    val operations by viewModel.specificTransactionsFlow.collectAsState()
    val headers by viewModel.headersFlow.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        CustomIcon(
                            modifier = Modifier,
                            size = 40.dp,
                            iconSize = 21.dp,
                            url = serviceIconUrl
                        )

                        Spacer(Modifier.width(8.dp))

                        Text(
                            modifier = Modifier,
                            fontWeight = FontWeight.Medium,
                            text = serviceName,
                            fontSize = 16.sp
                        )

                        Spacer(Modifier.width(16.dp))

                        Text(
                            modifier = Modifier,
                            fontWeight = FontWeight.SemiBold,
                            text = "••$cardsLastFour",
                            color = Color.Gray,
                            fontSize = 12.sp
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        modifier = Modifier.size(44.dp),
                        onClick = navBack
                    ) {
                        Icon(
                            painterResource(id = R.drawable.arrow_left),
                            "Go Back Button"
                        )
                    }
                }
            )
        }
    ) { appBarPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(appBarPadding)
                .padding(horizontal = 15.dp),
            verticalArrangement = Arrangement.spacedBy(
                10.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HalfCard()

            //todo implement
            CardOptions(
                onDetails = onCardDetailClicked,
                onLock = {},
                onTerminate = {}
            )

            SpecificCardTransactions(
                operations = operations,
                headers = headers,
                matchingItems = { viewModel.getMatchingItems(it) }
            )
        }

        BackHandler {
            viewModel.clearCardData()
            navBack()
        }
    }
}