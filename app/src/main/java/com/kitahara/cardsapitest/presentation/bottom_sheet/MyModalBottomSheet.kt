@file:OptIn(ExperimentalMaterial3Api::class)

package com.kitahara.cardsapitest.presentation.bottom_sheet

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kitahara.cardsapitest.data.BottomSheetContent
import com.kitahara.cardsapitest.data.cards_dto.CardInfo
import com.kitahara.cardsapitest.data.transactions.TransactionInfo
import com.kitahara.cardsapitest.presentation.main.card.MyCards
import com.kitahara.cardsapitest.presentation.main.recent_transactions.RecentTransactions
import kotlinx.coroutines.launch

@Composable
fun MyBottomSheet(
    showBottomSheet: Boolean,
    bottomSheetContent: BottomSheetContent,
    hideSheet: (Boolean) -> Unit,
    navigateCardInfo: (String) -> Unit,
    cards: List<CardInfo>?,
    transactions: List<TransactionInfo>?,
) {
    val sheetState = rememberModalBottomSheetState()

    val scope = rememberCoroutineScope()
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                hideSheet(false)
            },
            sheetState = sheetState
        ) {

            val closeModalBottomSheet = {
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        hideSheet(false)
                    }
                }
            }

            when (bottomSheetContent) {
                BottomSheetContent.Cards -> {
                    MyCards(
                        title = bottomSheetContent.title,
                        cards = cards,
                        shouldUseCardForBackground = false,
                        isSeeAllVisible = false,
                        shouldShowLimited = false,
                        onCardClicked = {
                            closeModalBottomSheet()

                            navigateCardInfo(it)

                            hideSheet(false)
                        },
                        onAllCardsPressed = {}
                    )
                }

                BottomSheetContent.Transactions -> {
                    RecentTransactions(
                        title = bottomSheetContent.title,
                        recentTransactions = transactions,
                        shouldUseCardForBackground = false,
                        shouldShowSeeAll = false,
                        shouldUseLimit = false
                    ) {}
                }
            }

            Spacer(Modifier.height(25.dp))
        }
    }
}