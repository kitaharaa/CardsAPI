package com.kitahara.cardsapitest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.kitahara.cardsapitest.data.navigation.bottom.BottomNavItem
import com.kitahara.cardsapitest.presentation.BaseContent
import com.kitahara.cardsapitest.presentation.ui.theme.CardsAPITestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CardsAPITestTheme {
                val bottomAppBarNavigation = rememberNavController()

                Scaffold(
                    bottomBar = {
                        BottomNavigation()
                    },
                ) { innerPadding ->

                    BaseContent(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun BottomNavigation() {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Transactions,
        BottomNavItem.MyCards,
        BottomNavItem.Profile
    )

    NavigationBar {
        items.forEach { item ->
            AddItem(
                screen = item
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomNavItem
) {
    NavigationBarItem(
        label = {
            Text(
                modifier = Modifier,
                text = screen.title,
                fontSize = 10.sp
            )
        },
        icon = {
            Icon(
                painterResource(id = screen.icon),
                contentDescription = screen.title
            )
        },

        selected = false,
        alwaysShowLabel = true,

        onClick = { /*TODO*/ },
        colors = NavigationBarItemDefaults.colors()
    )
}