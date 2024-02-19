package com.kitahara.cardsapitest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kitahara.cardsapitest.data.RequestType
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

    private val doInMain: suspend (suspend () -> Unit) -> Unit = {
        withContext(Dispatchers.Main) {
            it.invoke()
        }
    }

    init {
        requestDataAgain(RequestType.Transactions)
        requestDataAgain(RequestType.Cards)
    }

    fun getMatchingItems(time: String): List<TransactionInfo> {
        return _transactionsFlow.value?.filter { info -> (formatTime(info.completeDate.toString()) == time) }
            ?: emptyList()
    }

    fun getCardInfoById(id: String): CardInfo? {
        return _cardsFlow.value?.find { it.id == id }
    }

    fun clearCardData() {
        _headersFlow.value = setOf()
        _specificTransactionsFlow.value = listOf()
    }

    fun getCardNameById(argument: String): String {
        return getCardInfoById(argument)?.cardName ?: "Card"
    }

    fun requestDataAgain(it: RequestType) {
        viewModelScope.launch(Dispatchers.IO) {
            when (it) {
                RequestType.Cards -> {
                    doInMain {
                        _cardsFlow.emit(emptyList())
                    }

                    val cards = try {
                        client.get("https://dev.spendbase.co/cards").body<CardsData>().cards
                    } catch (e: Exception) {
                        null
                    }

                    doInMain {
                        _cardsFlow.emit(
                            cards
                        )
                    }
                }

                RequestType.Transactions -> {
                    doInMain {
                        _transactionsFlow.emit(emptyList())
                    }

                    val transactions = makeTransactionRequest()

                    doInMain {
                        _transactionsFlow.emit(transactions)
                    }
                }


            }
        }
    }

    fun extractTransactionWithCardId(cardId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (_transactionsFlow.value.isNullOrEmpty()) {
                val response = makeTransactionRequest()

                doInMain {
                    _transactionsFlow.emit(response)
                }
            }

            val cardOperations = _transactionsFlow
                .value
                ?.filter { info ->
                    info.card?.id == cardId
                }

            val headers = cardOperations?.map {
                formatTime(it.completeDate.toString()).toString()
            }?.toSet()

            withContext(Dispatchers.Main) {
                _headersFlow.emit(headers)

                _specificTransactionsFlow.emit(
                    cardOperations
                )
            }
        }
    }

    private suspend fun makeTransactionRequest() = try {
        client.get("https://dev.spendbase.co/cards/transactions")
            .body<Transaction>().transactions?.sortedBy {
                ZonedDateTime.parse(it.completeDate).toLocalDateTime().toString()
            }
    } catch (e: Exception) {
        null
    }
}