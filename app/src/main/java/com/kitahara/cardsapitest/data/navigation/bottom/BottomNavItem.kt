package com.kitahara.cardsapitest.data.navigation.bottom

import com.kitahara.cardsapitest.R

sealed class BottomNavItem(
    var title: String,
    var icon: Int
) {
    data object Home :
        BottomNavItem(
            "Home",
            R.drawable.home
        )

    data object Transactions :
        BottomNavItem(
            "Analytics",
            R.drawable.transactions
        )

    data object MyCards :
        BottomNavItem(
            "My Cards",
            R.drawable.credit_card
        )

    data object Profile :
        BottomNavItem(
            "Profile",
            R.drawable.user_square
        )
}