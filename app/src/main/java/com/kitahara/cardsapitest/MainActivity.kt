package com.kitahara.cardsapitest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kitahara.cardsapitest.data.navigation.AppNavigation
import com.kitahara.cardsapitest.data.navigation.AppNavigation.Companion.pickedCardId
import com.kitahara.cardsapitest.presentation.SharedViewModel
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
                val viewModel = hiltViewModel<SharedViewModel>()
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
                            MainScreen(viewModel = viewModel) {
                                navController.navigate(AppNavigation.CardScreen.destination + "/$it")
                            }
                        }

                        composable(AppNavigation.CardScreen.destination + "/{$pickedCardId}",
                            listOf(
                                navArgument(pickedCardId) {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            val argument =  it.arguments?.getString(pickedCardId) ?: return@composable
                            CardScreen(viewModel = viewModel, cardId = argument) {
                                navController.popBackStack()
                            }
                        }
                    }
                }
            }
        }
    }
}