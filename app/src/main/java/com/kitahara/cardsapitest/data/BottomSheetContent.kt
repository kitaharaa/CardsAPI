package com.kitahara.cardsapitest.data

sealed class BottomSheetContent(
    val title: String
) {
    data object Cards : BottomSheetContent("All cards")

    data object Transactions : BottomSheetContent("All transactions")

    data object CardInfo : BottomSheetContent("About card owner") {
        var cardId: String? = null
    }
}