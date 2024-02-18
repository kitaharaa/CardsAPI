package com.kitahara.cardsapitest.data.transactions


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class Attachment(
    @SerialName("createdAt")
    val createdAt: String? = null, // 2024-02-17T04:29:21.665Z
    @SerialName("deletedAt")
    val deletedAt: String? = null, // null
    @SerialName("externalTransactionId")
    val externalTransactionId: String? = null, // 30071
    @SerialName("fileName")
    val fileName: String? = null, // e0e828b-4baf-424f-bee8-ac8aa5b577f6.jpeg
    @SerialName("fileSize")
    val fileSize: String? = null, // 33991
    @SerialName("fileType")
    val fileType: String? = null, // image/jpeg
    @SerialName("fileUrl")
    val fileUrl: String? = null, // https://storage.googleapis.com/spendbase-prod-bucket/498%2Fb3f45b26-2665-4180-b3ad-f64e5201f8d6%2Fe0e828b-4baf-424f-bee8-ac8aa5b577f6.jpeg
    @SerialName("id")
    val id: String? = null, // da5c3d7b-b9b5-4ed6-8c00-0a4cb74037e8
    @SerialName("note")
    val note: String? = null,
    @SerialName("sourceId")
    val sourceId: String? = null, // 00c46b67-de1f-48da-ae6e-aa69237332a9
    @SerialName("transactionId")
    val transactionId: String? = null, // b3f45b26-2665-4180-b3ad-f64e5201f8d6
    @SerialName("updatedAt")
    val updatedAt: String? = null // 2024-02-17T04:29:21.665Z
)