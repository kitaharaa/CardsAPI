package com.kitahara.cardsapitest.presentation.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.layoutId
import com.kitahara.cardsapitest.R
import com.kitahara.cardsapitest.presentation.ui.theme.CardBorder
import com.kitahara.cardsapitest.presentation.ui.theme.CardInner

@Composable
@Preview
fun HalfCard() {
    Card(
        modifier = Modifier
            .height(82.dp)
            .width(256.dp)
            .layoutId("blackBackCard"),
        colors = CardDefaults.cardColors(
            containerColor = CardInner
        ),
        border = BorderStroke(1.dp, CardBorder),
        shape = RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp),
        elevation = CardDefaults.cardElevation(7.dp)
    ) {

        Box(Modifier.fillMaxSize()) {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopStart
            ) {
                Image(
                    modifier = Modifier.padding(top = 24.dp, start = 20.dp),
                    painter = painterResource(id = R.drawable.medium_logo),
                    contentDescription = "Spendbase logo"
                )
            }

            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopEnd
            ) {
                Image(
                    modifier = Modifier.padding(top = 5.dp, end = 20.dp),
                    painter = painterResource(id = R.drawable.mastercard_svgrepo_com__1_),
                    contentDescription = "Mastercard logo"
                )
            }
        }
    }
}
