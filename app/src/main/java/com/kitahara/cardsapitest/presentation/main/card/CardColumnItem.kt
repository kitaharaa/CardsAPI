package com.kitahara.cardsapitest.presentation.main.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId
import com.kitahara.cardsapitest.R

@Composable
fun CardColumnItem(
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
        SmallBlackCard(
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