package com.kitahara.cardsapitest.data.transactions


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class CardHolder(
    @SerialName("email")
    val email: String? = null, // andrew@partner-way.com
    @SerialName("fullName")
    val fullName: String? = null, // Andrew Alex
    @SerialName("id")
    val id: String? = null, // 4
    @SerialName("logoUrl")
    val logoUrl: String? = null // https://spendbase.s3.eu-central-1.amazonaws.com/users/4/picture.png%3F1676305861461
)