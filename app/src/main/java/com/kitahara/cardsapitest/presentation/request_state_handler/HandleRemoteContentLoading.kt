package com.kitahara.cardsapitest.presentation.request_state_handler

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun <T> HandleRemoteContentLoading(
    state: List<T>?,
    content: @Composable () -> Unit,
    restartRequest: () -> Unit,
) {

    when (state) {
        emptyList<T>() -> {
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(Modifier.padding(vertical = 10.dp))
            }
        }

        null -> {
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(top = 15.dp, bottom = 10.dp),
                    fontWeight = FontWeight.SemiBold,
                    text = "Something went wrong",
                    fontSize = 16.sp
                )

                Box(
                    Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    OutlinedButton(
                        modifier = Modifier.padding(bottom = 10.dp),
                        onClick = restartRequest
                    ) {
                        Text(
                            modifier = Modifier,
                            fontWeight = FontWeight.SemiBold,
                            text = "Again",
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }

        else -> content()
    }
}