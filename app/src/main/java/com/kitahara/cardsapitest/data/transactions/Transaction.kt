package com.kitahara.cardsapitest.data.transactions


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class Transaction(
    @SerialName("transactions")
    val transactions: List<TransactionInfo>? = listOf()
)