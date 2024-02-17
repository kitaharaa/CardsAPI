package com.kitahara.cardsapitest.data.navigation

sealed class AppNavigation(val destination: String) {
    data object MainScreen: AppNavigation("MainScreen")
    data object CardScreen: AppNavigation("CardScreen")
}