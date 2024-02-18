package com.kitahara.cardsapitest.presentation.main.templates

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId

@Composable
fun ColumnHeader(
    titleContent: String,
    isSeeAllVisible: Boolean = true,
    onSeeAllClicked: () -> Unit
) {
    val constraintSet = ConstraintSet {
        val title = createRefFor("headerTitle")
        val seeAll = createRefFor("seeAll")

        constrain(title) {
            start.linkTo(parent.start)
        }

        constrain(seeAll) {
            end.linkTo(parent.end)
            top.linkTo(title.top)
            bottom.linkTo(title.bottom)
        }
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 7.dp),
        constraintSet = constraintSet
    ) {
        Text(
            modifier = Modifier.layoutId("headerTitle"),
            fontSize = 24.sp,
            text = titleContent,
            fontWeight = FontWeight.SemiBold
        )

        if (isSeeAllVisible)
            Text(
                modifier = Modifier
                    .layoutId("seeAll")
                    .clickable {
                        onSeeAllClicked()
                    },
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                text = "See all",
                textDecoration = TextDecoration.Underline
            )
    }
}
