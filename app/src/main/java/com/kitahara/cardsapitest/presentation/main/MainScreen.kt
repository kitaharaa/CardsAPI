@file:OptIn(ExperimentalMaterial3Api::class)

package com.kitahara.cardsapitest.presentation.main

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId
import com.kitahara.cardsapitest.R
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
                sum = 100000,
                sign = "$",
                countryFlag = R.drawable.united_states_of_america
            )

            MyCards()
        }
    }
}

@Composable
fun MyCards() {
    ContentBaseBack {
        Column(modifier = it) {
            ColumnHeader(titleContent = "My Cards", onSeeAllClicked = {})

            (0..2).forEach { _ ->
                //todo enter list
                SmallCardItem(
                    name = "Slack",
                    lastFour = "5562",
                    logoUrl = "https://spendbase.s3.eu-central-1.amazonaws.com/users/4/picture.png%3F1676305861461"
                ) {}
            }
        }
    }
}

@Composable
fun SmallCardItem(
    name: String,
    lastFour: String,
    logoUrl: String,
    onCardClicked: () -> Unit
) {
    val constraintSet = ConstraintSet {
        val card = createRefFor("card")
        val title = createRefFor("title")
        val openButton = createRefFor("openButton")

        constrain(card) {
            start.linkTo(parent.start)
            top.linkTo(parent.top, 4.dp)
            bottom.linkTo(parent.bottom, 6.dp)
        }

        constrain(title) {
            start.linkTo(card.end, 12.dp)
            top.linkTo(card.top, 12.dp)
            bottom.linkTo(card.bottom)
        }

        constrain(openButton) {
            end.linkTo(parent.end)

            top.linkTo(title.top)
            bottom.linkTo(title.bottom)
        }
    }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onCardClicked),
        constraintSet = constraintSet
    ) {
        CardItem(
            modifier = Modifier.layoutId("card"),
            lastFour = lastFour,
            logoUrl = logoUrl
        )

        Text(
            modifier = Modifier.layoutId("title"),
            fontWeight = FontWeight.SemiBold,
            text = name,
            fontSize = 16.sp
        )

        Icon(
            modifier = Modifier
                .size(24.dp)
                .layoutId("openButton"),
            painter = painterResource(id = R.drawable.next),

            contentDescription = stringResource(R.string.open_button)
        )
    }
}