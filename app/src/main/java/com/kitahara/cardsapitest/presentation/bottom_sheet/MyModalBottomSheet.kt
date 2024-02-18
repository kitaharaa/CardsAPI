@file:OptIn(ExperimentalMaterial3Api::class)

package com.kitahara.cardsapitest.presentation.bottom_sheet

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kitahara.cardsapitest.data.BottomSheetContent
import com.kitahara.cardsapitest.data.cards_dto.CardHolder
import com.kitahara.cardsapitest.data.cards_dto.CardInfo
import com.kitahara.cardsapitest.data.transactions.TransactionInfo
import com.kitahara.cardsapitest.presentation.main.card.MyCards
import com.kitahara.cardsapitest.presentation.main.recent_transactions.RecentTransactions
import com.kitahara.cardsapitest.presentation.main.service_icon.CustomIcon
import com.kitahara.cardsapitest.presentation.main.templates.ColumnHeader
import kotlinx.coroutines.launch

@Composable
fun MyBottomSheet(
    showBottomSheet: Boolean,
    bottomSheetContent: BottomSheetContent,
    hideSheet: (Boolean) -> Unit,
    navigateCardInfo: (String) -> Unit,
    cards: List<CardInfo>?,
    transactions: List<TransactionInfo>?,
    requestCardInfo: (String) -> CardInfo?
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
                is BottomSheetContent.Cards -> {
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

                is BottomSheetContent.Transactions -> {
                    RecentTransactions(
                        title = bottomSheetContent.title,
                        recentTransactions = transactions,
                        shouldUseCardForBackground = false,
                        shouldShowSeeAll = false,
                        shouldUseLimit = false
                    ) {}
                }

                is BottomSheetContent.CardInfo -> {
                    val cardId = bottomSheetContent.cardId

                    val closeBottomSheet = {
                        closeModalBottomSheet()
                        hideSheet(false)
                    }

                    if (cardId == null) {
                        closeBottomSheet()

                        return@ModalBottomSheet
                    }

                    requestCardInfo(cardId)?.let {
                        CardInfoContent(
                            title = bottomSheetContent.title,
                            info = it
                        )
                    } ?: closeBottomSheet()
                }
            }
        }
    }

    Spacer(Modifier.height(25.dp))
}

@Composable
@Preview
fun CardInfoContent(
    title: String = "About card owner", info: CardInfo = CardInfo(
        cardHolder = CardHolder(
            email = "andrew@partner-way.com",
            fullName = "Andrew Alex",
            id = "4f",
            logoUrl = "https://spendbase.s3.eu-central-1.amazonaws.com/users/4/picture.png%3F1676305861461"
        ),
        cardLast4 = "6545",
        cardName = "Child Card",
        fundingSource = "ACH",
        id = "f1ae558c-fa00-46b6-b04c-a5d6ff131f0f",
        isLocked = false,
        isTerminated = false,
        issuedAt = "2023-11-13T09:00:43.000Z",
        limit = 5000,
        limitType = "PerMonth",
        spent = 0
    )
) {
    Log.e("Info", info.toString())
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ColumnHeader(
            titleContent = title,
            onSeeAllClicked = {},
            isSeeAllVisible = false,
        )

        Spacer(
            modifier = Modifier.height(30.dp)
        )

        CustomIcon(
            size = 70.dp,
            iconSize = 60.dp,
            url = info.cardHolder?.logoUrl
        )

        Text(
            modifier = Modifier
                .padding(top = 7.dp),
            fontWeight = FontWeight.SemiBold,
            text = info.cardHolder?.email ?: "",
            color = Color.Gray,
            fontSize = 10.sp
        )
        Text(
            modifier = Modifier
                .padding(top = 7.dp),
            fontWeight = FontWeight.SemiBold,
            text = info.cardHolder?.fullName ?: "",
            fontSize = 20.sp
        )

        Spacer(
            modifier = Modifier.height(50.dp)
        )
    }
}