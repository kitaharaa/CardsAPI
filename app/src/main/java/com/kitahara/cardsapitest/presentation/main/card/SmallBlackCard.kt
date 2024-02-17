package com.kitahara.cardsapitest.presentation.main.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId
import com.kitahara.cardsapitest.R
import com.kitahara.cardsapitest.presentation.main.templates.CustomAsyncImage
import com.kitahara.cardsapitest.presentation.ui.theme.CardBorder
import com.kitahara.cardsapitest.presentation.ui.theme.CardInner

@Composable
@Preview
fun SmallBlackCard(
    modifier: Modifier = Modifier,
    lastFour: String = "5523",
    logoUrl: String = "https://spendbase.s3.eu-central-1.amazonaws.com/users/4/picture.png%3F1676305861461",
) {
    val constraintSet = ConstraintSet {
        val iconButton = createRefFor("iconButton")
        val blackCard = createRefFor("blackBackCard")


        constrain(iconButton) {
            start.linkTo(blackCard.start, (-8).dp)
            top.linkTo(blackCard.top, (-12).dp)
        }
    }

    Box(
        modifier = modifier
            .height(44.dp)
            .width(56.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        ConstraintLayout(
            modifier = Modifier,
            constraintSet = constraintSet
        ) {
            Card(
                modifier = Modifier
                    .height(32.dp)
                    .width(48.dp)
                    .layoutId("blackBackCard"),
                colors = CardDefaults.cardColors(
                    containerColor = CardInner
                ),
                border = BorderStroke(1.dp, CardBorder),
                shape = RoundedCornerShape(4.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(end = 6.dp, bottom = 4.dp),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Text(
                        modifier = Modifier.layoutId("numbers"),
                        text = lastFour,
                        fontSize = 10.sp,
                        color = Color.White
                    )
                }
            }

            IconButton(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .layoutId("iconButton"),
                onClick = {},
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.White
                )
            ) {

                CustomAsyncImage(
                    modifier = Modifier.size(17.dp),
                    url = logoUrl,
                    description = stringResource(R.string.service_logo)
                )
            }
        }
    }
}
