@file:OptIn(ExperimentalMaterial3Api::class)

package com.kitahara.cardsapitest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kitahara.cardsapitest.data.BottomSheetContent
import com.kitahara.cardsapitest.data.navigation.AppNavigation
import com.kitahara.cardsapitest.data.navigation.AppNavigation.Companion.pickedCardId
import com.kitahara.cardsapitest.presentation.SharedViewModel
import com.kitahara.cardsapitest.presentation.bottom_sheet.MyBottomSheet
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

                var showBottomSheet by remember { mutableStateOf(false) }

                val cards by viewModel.cardsFlow.collectAsState()
                val transactions by viewModel.transactionsFlow.collectAsState()

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var bottomSheetContent by remember {
                        mutableStateOf<BottomSheetContent>(BottomSheetContent.Cards)
                    }

                    NavHost(
                        navController = navController,
                        startDestination = AppNavigation.MainScreen.destination
                    ) {

                        composable(AppNavigation.MainScreen.destination) {
                            MainScreen(
                                cards = cards,
                                transactions = transactions,
                                navigateCardDetails = {
                                    navController.navigate(AppNavigation.CardScreen.destination + "/$it")
                                    showBottomSheet = false
                                },
                                onAllRecentTransactionsPressed = {
                                    bottomSheetContent = BottomSheetContent.Transactions
                                    showBottomSheet = true
                                },
                                restartRequest = viewModel::requestDataAgain,
                                onAllCardsPressed = {
                                    bottomSheetContent = BottomSheetContent.Cards
                                    showBottomSheet = true
                                }
                            )
                        }

                        composable(AppNavigation.CardScreen.destination + "/{$pickedCardId}",
                            listOf(
                                navArgument(pickedCardId) {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            val argument =
                                it.arguments?.getString(pickedCardId) ?: return@composable
                            CardScreen(
                                viewModel = viewModel,
                                serviceName = viewModel.getCardNameById(argument),
                                cardId = argument,
                                onCardDetailClicked = {
                                    bottomSheetContent = BottomSheetContent.CardInfo.also {
                                        it.cardId = argument
                                    }

                                    showBottomSheet = true
                                }
                            ) {
                                navController.popBackStack()
                            }
                        }
                    }

                    MyBottomSheet(
                        showBottomSheet = showBottomSheet,
                        bottomSheetContent = bottomSheetContent,
                        cards = cards,
                        transactions = transactions,
                        hideSheet = { showBottomSheet = !showBottomSheet },
                        requestCardInfo = viewModel::getCardInfoById,
                        onRequestData = viewModel::requestDataAgain,
                        navigateCardInfo = {
                            navController.navigate(
                                AppNavigation.CardScreen.destination + "/$it"
                            )

                            showBottomSheet = false
                        }
                    )
                }
            }
        }
    }
}