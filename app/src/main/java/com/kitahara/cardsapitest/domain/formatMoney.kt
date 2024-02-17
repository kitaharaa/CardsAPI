package com.kitahara.cardsapitest.domain

import java.text.NumberFormat
import java.util.Locale

fun formatMoney(value: Float): String {
    val locale = Locale("en", "US")
    val currencyFormatter: NumberFormat = NumberFormat.getCurrencyInstance(locale)

    return currencyFormatter.format(value)
}