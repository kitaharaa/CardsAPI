@file:OptIn(ExperimentalMaterial3Api::class)

package com.kitahara.cardsapitest.presentation.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kitahara.cardsapitest.R

@Composable
fun CustomTopAppBar() {
    Column(
        modifier = Modifier.padding(horizontal = 15.dp)
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Money",
                    fontWeight = FontWeight.Bold,
                    fontSize = 35.sp
                )
            },
            actions = {
                IconButton(
                    onClick = {
                        //todo implement
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(17.dp),
                        painter = painterResource(id = R.drawable.plus),
                        contentDescription = "Add button"
                    )
                }

                IconButton(
                    onClick = {
                        //todo implement
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(25.dp),
                        painter = painterResource(id = R.drawable.bank),
                        contentDescription = "Bank button"
                    )
                }
            }
        )

        Divider(
            modifier = Modifier.padding(vertical = 10.dp),
            thickness = 1.dp,
            color = Color.Gray,
        )
    }
}
