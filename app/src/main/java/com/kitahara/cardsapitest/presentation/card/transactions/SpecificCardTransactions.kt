@file:OptIn(ExperimentalFoundationApi::class)

package com.kitahara.cardsapitest.presentation.card.transactions

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kitahara.cardsapitest.data.transactions.TransactionInfo
import com.kitahara.cardsapitest.presentation.main.templates.ContentBaseBack

@Composable
fun SpecificCardTransactions(
    operations: List<TransactionInfo>?,
    headers: Set<String>?,
    matchingItems: (String) -> List<TransactionInfo>,
) {
    Log.e("SpecificCardTransactions", "operations = $operations,\n headers = $headers\n matchingItems = $matchingItems", )
    ContentBaseBack(modifier = Modifier.padding(top = 15.dp)) {
        LazyColumn(it.padding(top = 20.dp)) {

            val background: Color = Color.Unspecified
            val dateHeader: (time: String) -> Unit = { time ->
                stickyHeader {
                    Column(Modifier.background(background)) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            color = Color.Gray,
                            text = time,
                            fontSize = 14.sp
                        )

                        Divider(
                            thickness = 1.dp,
                            color = Color.Gray
                        )
                    }
                }
            }

            if (operations?.isNotEmpty() == true && !headers.isNullOrEmpty()) {
                headers.forEach { time ->
                    dateHeader(time)

                    items(matchingItems(time)) { transactionInfo ->
                        SpecificCardTransactionsItem(
                            service = transactionInfo.merchant?.name ?: "Unknown",
                            operationSum = transactionInfo.amount ?: 0f,
                            status = transactionInfo.status,
                            logoUrl = transactionInfo.card?.cardHolder?.logoUrl.toString()
                        )
                    }
                }
            }
        }
    }
}
