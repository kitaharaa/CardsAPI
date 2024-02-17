package com.kitahara.cardsapitest.presentation.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kitahara.cardsapitest.R
import com.kitahara.cardsapitest.presentation.main.templates.ContentBaseBack
import java.text.NumberFormat
import java.util.Locale

@Composable
fun AccountInfo(currency: String, sum: Int, sign: String, countryFlag: Int) {
    ContentBaseBack {
        Column(
            modifier = it,
            horizontalAlignment = Alignment.Start
        ) {

            Row(
                Modifier
                    .height(24.dp)
                    .padding(bottom = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                Image(
                    modifier = Modifier
                        .width(22.dp)
                        .clip(RoundedCornerShape(2.dp)),
                    painter = painterResource(countryFlag),
                    contentDescription = stringResource(R.string.currency_flag)
                )

                Text(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    text = "$currency account"
                )
            }

            val formatter = NumberFormat.getNumberInstance(Locale.US)
            Text(
                modifier = Modifier.padding(
                    start = 10.dp,
                    bottom = 5.dp
                ),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                text = "$sign${formatter.format(sum)}"
            )
        }
    }
}