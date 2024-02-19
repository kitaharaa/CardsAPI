package com.kitahara.cardsapitest.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
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
import com.kitahara.cardsapitest.presentation.bottom_sheet.MyBottomSheet
import com.kitahara.cardsapitest.presentation.card.CardScreen
import com.kitahara.cardsapitest.presentation.main.MainScreen

@Composable
fun BaseContent(modifier: Modifier) {
    var showBottomSheet by remember { mutableStateOf(false) }
    var bottomSheetContent by remember {
        mutableStateOf<BottomSheetContent>(BottomSheetContent.Cards)
    }

    val navController = rememberNavController()
    val viewModel = hiltViewModel<SharedViewModel>()
    val cards by viewModel.cardsFlow.collectAsState()
    val transactions by viewModel.transactionsFlow.collectAsState()

    // A surface container using the 'background' color from the theme
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
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

            composable(
                AppNavigation.CardScreen.destination + "/{${AppNavigation.pickedCardId}}",
                listOf(
                    navArgument(AppNavigation.pickedCardId) {
                        type = NavType.StringType
                    }
                )
            ) {
                val argument =
                    it.arguments?.getString(AppNavigation.pickedCardId) ?: return@composable
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