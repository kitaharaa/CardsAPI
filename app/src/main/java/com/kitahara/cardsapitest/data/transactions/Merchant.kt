package com.kitahara.cardsapitest.data.transactions


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class Merchant(
    @SerialName("icon")
    val icon: String? = null, // null
    @SerialName("name")
    val name: String? = null // Load to Account
)