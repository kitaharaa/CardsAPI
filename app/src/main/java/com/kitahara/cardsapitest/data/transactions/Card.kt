package com.kitahara.cardsapitest.data.transactions


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class Card(
    @SerialName("cardHolder")
    val cardHolder: CardHolder? = null,
    @SerialName("cardLast4")
    val cardLast4: String? = null, // 8060
    @SerialName("cardName")
    val cardName: String? = null, // Debit Card
    @SerialName("fundingSource")
    val fundingSource: String? = null, // ACH
    @SerialName("id")
    val id: String? = null, // f0cd6087-2bb6-44f8-ae2b-c99a06b056db
    @SerialName("isLocked")
    val isLocked: Boolean? = null, // false
    @SerialName("isTerminated")
    val isTerminated: Boolean? = null, // false
    @SerialName("issuedAt")
    val issuedAt: String? = null, // 2023-08-30T12:43:17.000Z
    @SerialName("limit")
    val limit: Int? = null, // 5000
    @SerialName("limitType")
    val limitType: String? = null, // PerMonth
    @SerialName("spent")
    val spent: Int? = null // 0
)