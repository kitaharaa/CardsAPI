@file:OptIn(ExperimentalMaterial3Api::class)

package com.kitahara.cardsapitest.presentation.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.layoutId
import com.kitahara.cardsapitest.R
import com.kitahara.cardsapitest.domain.formatMoney
import com.kitahara.cardsapitest.presentation.main.card.MyCards
import com.kitahara.cardsapitest.presentation.main.info.AccountInfo
import com.kitahara.cardsapitest.presentation.main.service_icon.ServiceIcon
import com.kitahara.cardsapitest.presentation.main.templates.ColumnHeader
import com.kitahara.cardsapitest.presentation.main.templates.ContentBaseBack

@Composable
@Preview
fun MainScreen(
    modifier: Modifier = Modifier
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

            MyCards()

            RecentTransactions()
        }
    }
}

@Composable
fun RecentTransactions() {
    ContentBaseBack {
        Column(it) {
            ColumnHeader(titleContent = "Recent transactions") {
                //todo implement
            }

            (0..2).forEach { _ ->
                TransactionItem(
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

@Composable
@Preview
fun TransactionItem(
    service: String = "Slack",
    lastFour: String = "4343",
    operationSum: Float = 411.32f,
    status: String = "Success",
    logoUrl: String = "https://spendbase.s3.eu-central-1.amazonaws.com/users/4/picture.png%3F1676305861461",
) {

    val constraintSet = ConstraintSet {
        val serviceIcon = createRefFor("serviceIcon")
        val transactionText = createRefFor("transactionText")
        val resultIcon = createRefFor("resultIcon")
        val amount = createRefFor("amount")

        constrain(amount) {
            end.linkTo(resultIcon.start, 5.dp)

            top.linkTo(transactionText.top)
            bottom.linkTo(transactionText.bottom)
        }

        constrain(transactionText) {
            start.linkTo(serviceIcon.end, margin = 10.dp)
            end.linkTo(amount.start, margin = 10.dp)

            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            width = Dimension.fillToConstraints
        }

        constrain(serviceIcon) {
            top.linkTo(transactionText.top, 5.dp)
            bottom.linkTo(transactionText.bottom, 5.dp)
            start.linkTo(parent.start)
        }

        constrain(resultIcon) {
            end.linkTo(parent.end)
            top.linkTo(transactionText.top)
            bottom.linkTo(transactionText.bottom)
        }

    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        constraintSet = constraintSet
    ) {
        ServiceIcon(
            modifier = Modifier.layoutId("serviceIcon"),
            size = 36.dp,
            url = logoUrl,
            iconSize = 21.dp,
            onClick = {}
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .layoutId("transactionText"),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                modifier = Modifier,
                fontWeight = FontWeight.SemiBold,
                text = service,
                fontSize = 16.sp
            )

            Text(
                modifier = Modifier,
                color = Color.Gray,
                text = "**$lastFour",
                fontSize = 12.sp
            )
        }

        val (icon, description) = if (status == "Success") {
            painterResource(
                id = R.drawable.successful_transaction
            ) to stringResource(R.string.successful_transactions)
        } else {
            painterResource(
                id = R.drawable.unsuccessful_transaction
            ) to stringResource(R.string.unsuccessful_transactions)
        }

        Icon(
            modifier = Modifier
                .size(24.dp)
                .layoutId("resultIcon"),
            painter = icon,
            contentDescription = description
        )

        Text(
            modifier = Modifier.layoutId("amount"),
            fontWeight = FontWeight.SemiBold,
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        textDecoration = if (status == "Success") TextDecoration.None
                        else TextDecoration.LineThrough,
                        color = if (operationSum.toString()
                                .contains("-")
                        ) Color.Red else Color.Unspecified
                    )
                ) {
                    append(formatMoney(operationSum))
                }
            },
            fontSize = 16.sp
        )
    }
}