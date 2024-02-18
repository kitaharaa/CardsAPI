package com.kitahara.cardsapitest.data.transactions


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class Account(
    @SerialName("accountLast4")
    val accountLast4: String? = null, // 3798
    @SerialName("accountName")
    val accountName: String? = null, // 1700113798
    @SerialName("accountType")
    val accountType: String? = null // Checking
)