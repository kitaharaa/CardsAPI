package com.kitahara.cardsapitest.data

data class Transaction(
    val service: String = "Slack",
    val lastFour: String = "4343",
    val operationSum: Float = 411.32f,
    val status: String = "Success",
    val logoUrl: String = "https://spendbase.s3.eu-central-1.amazonaws.com/users/4/picture.png%3F1676305861461",
    val time: String = "2023-11-13T09:00:43.000Z"
)