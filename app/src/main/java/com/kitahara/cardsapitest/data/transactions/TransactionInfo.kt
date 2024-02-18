package com.kitahara.cardsapitest.data.transactions


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class TransactionInfo(
    @SerialName("account")
    val account: Account? = null,
    @SerialName("amount")
    val amount: Float? = null, // -19.99
    @SerialName("attachments")
    val attachments: List<Attachment?>? = null,
    @SerialName("card")
    val card: Card? = null,
    @SerialName("category")
    val category: String? = null, // null
    @SerialName("completeDate")
    val completeDate: String? = null, // 2023-09-15T00:00:00.000Z
    @SerialName("createDate")
    val createDate: String? = null, // 2023-09-15T00:00:00.000Z
    @SerialName("id")
    val id: String? = null, // 1f2f4777-342b-4306-a0e3-bd4618ea0fa9
    @SerialName("merchant")
    val merchant: Merchant? = null,
    @SerialName("origin")
    val origin: String? = null, // Account
    @SerialName("publicId")
    val publicId: String? = null, // 30003
    @SerialName("status")
    val status: String , // Success
    @SerialName("type")
    val type: String? = null // Load
)