package com.kitahara.cardsapitest.data.cards_dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class CardInfo(
    @SerialName("cardHolder")
    val cardHolder: CardHolder? = null,
    @SerialName("cardLast4")
    val cardLast4: String? = null, // 6545
    @SerialName("cardName")
    val cardName: String? = null, // Child Card
    @SerialName("fundingSource")
    val fundingSource: String? = null, // ACH
    @SerialName("id")
    val id: String? = null, // f1ae558c-fa00-46b6-b04c-a5d6ff131f0f
    @SerialName("isLocked")
    val isLocked: Boolean? = null, // false
    @SerialName("isTerminated")
    val isTerminated: Boolean? = null, // false
    @SerialName("issuedAt")
    val issuedAt: String? = null, // 2023-11-13T09:00:43.000Z
    @SerialName("limit")
    val limit: Int? = null, // 5000
    @SerialName("limitType")
    val limitType: String? = null, // PerMonth
    @SerialName("spent")
    val spent: Int? = null // 0
)