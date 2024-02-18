package com.kitahara.cardsapitest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kitahara.cardsapitest.data.navigation.AppNavigation
import com.kitahara.cardsapitest.presentation.card.CardScreen
import com.kitahara.cardsapitest.presentation.main.MainScreen
import com.kitahara.cardsapitest.presentation.ui.theme.CardsAPITestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CardsAPITestTheme {
                val navController = rememberNavController()

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    NavHost(
                        navController = navController,
                        startDestination = AppNavigation.MainScreen.destination
                    ) {

                        composable(AppNavigation.MainScreen.destination) {
                            MainScreen()
                        }

                        composable(AppNavigation.CardScreen.destination) {
                            CardScreen()
                        }
                    }
                }
            }
        }
    }
}