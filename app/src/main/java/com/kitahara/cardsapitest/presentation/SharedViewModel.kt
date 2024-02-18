package com.kitahara.cardsapitest.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kitahara.cardsapitest.data.cards_dto.CardInfo
import com.kitahara.cardsapitest.data.cards_dto.CardsData
import com.kitahara.cardsapitest.data.transactions.Transaction
import com.kitahara.cardsapitest.data.transactions.TransactionInfo
import com.kitahara.cardsapitest.domain.formatTime
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val client: HttpClient
) : ViewModel() {
    private val _headersFlow = MutableStateFlow<Set<String>?>(setOf())
    val headersFlow = _headersFlow.asStateFlow()

    private val _cardsFlow = MutableStateFlow<List<CardInfo>?>(listOf())
    val cardsFlow = _cardsFlow.asStateFlow()

    private val _transactionsFlow = MutableStateFlow<List<TransactionInfo>?>(listOf())
    val transactionsFlow = _transactionsFlow.asStateFlow()

    private val _specificTransactionsFlow = MutableStateFlow<List<TransactionInfo>?>(listOf())
    val specificTransactionsFlow = _specificTransactionsFlow.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.Default) {
            _cardsFlow.emit(client.get("https://dev.spendbase.co/cards").body<CardsData>().cards)

            val transactions = client.get("https://dev.spendbase.co/cards/transactions")
                .body<Transaction>().transactions?.sortedBy {
                    ZonedDateTime.parse(it.completeDate).toLocalDateTime().toString()
                }

            Log.e("transactions", transactions.toString())

            withContext(Dispatchers.Main) {
                _transactionsFlow.emit(transactions)

            }
        }
    }

    fun getMatchingItems(time: String): List<TransactionInfo> {
        return _transactionsFlow.value?.filter { info -> (formatTime(info.completeDate.toString()) == time) }
            ?: emptyList()
    }

    fun extractTransactionWithCardId(cardId: String) {
        viewModelScope.launch(Dispatchers.Default) {
            val cardOperations = _transactionsFlow
                .value
                ?.filter { info ->
                    info.card?.id == cardId
                }

            val headers = cardOperations?.map {
                formatTime(it.completeDate.toString()).toString()
            }?.toSet()

            Log.e("cardOperations", cardOperations.toString())
            Log.e("SpecificHeaders", headers.toString())

            withContext(Dispatchers.Main) {
                _specificTransactionsFlow.emit(
                    cardOperations
                )

                _headersFlow.emit(headers)
            }
        }
    }

    fun clearCardData() {
        _headersFlow.value = setOf()
        _specificTransactionsFlow.value = listOf()
    }
}