package com.kitahara.cardsapitest.data.cards_dto

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class CardsData(
    val cards: List<CardInfo>? = null
)