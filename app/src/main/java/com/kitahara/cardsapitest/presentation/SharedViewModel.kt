package com.kitahara.cardsapitest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kitahara.cardsapitest.data.Transaction
import com.kitahara.cardsapitest.domain.formatTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {
    private val _transactionFlow = MutableStateFlow<List<Transaction>>(emptyList())
    val transactionFlow = _transactionFlow.asStateFlow()

    private val _headersFlow = MutableStateFlow<Set<String>>(setOf())
    val headersFlow = _headersFlow.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.Default) {
            val operationList = listOf(
                Transaction(),
                Transaction(time = "2023-12-13T09:00:43.000Z"),
                Transaction(time = "2023-12-13T09:00:43.000Z"),
                Transaction(time = "2023-12-13T09:00:43.000Z"),
                Transaction(time = "2023-12-13T09:00:43.000Z"),
                Transaction(time = "2023-12-13T09:00:43.000Z"),
                Transaction(time = "2023-12-13T09:00:43.000Z"),
                Transaction(time = "2023-12-13T09:00:43.000Z"),
                Transaction(time = "2023-12-13T09:00:43.000Z"),
                Transaction(time = "2023-12-13T09:00:43.000Z"),
                Transaction(time = "2023-12-13T09:00:43.000Z"),
                Transaction(time = "2023-10-13T09:00:43.000Z"),
                Transaction(time = "2023-10-13T09:00:43.000Z"),
                Transaction(time = "2023-10-13T09:00:43.000Z"),
                Transaction(time = "2023-10-13T09:00:43.000Z"),
                Transaction(time = "2023-10-13T09:00:43.000Z"),
                Transaction(time = "2023-10-13T09:00:43.000Z"),
                Transaction(time = "2023-10-13T09:00:43.000Z"),
                Transaction(time = "2023-10-13T09:00:43.000Z"),
                Transaction(time = "2023-12-13T00:00:00.000Z"),
                Transaction(time = "2023-12-13T00:00:00.000Z"),
                Transaction(time = "2023-12-13T00:00:00.000Z"),
                Transaction(time = "2023-12-13T00:00:00.000Z"),
                Transaction(time = "2023-12-13T00:00:00.000Z"),
                Transaction(time = "2023-12-13T00:00:00.000Z"),
                Transaction(time = "2023-12-13T00:00:00.000Z"),
                Transaction(time = "2023-10-13T09:00:43.000Z"),
                Transaction(time = "2023-11-13T00:00:00.000Z"),
                Transaction(time = "2023-11-13T00:00:00.000Z"),
                Transaction(time = "2023-11-13T00:00:00.000Z"),
                Transaction(time = "2023-11-13T00:00:00.000Z"),
                Transaction(time = "2023-11-13T00:00:00.000Z"),
                Transaction(time = "2023-11-13T00:00:00.000Z"),
                Transaction(time = "2023-11-13T00:00:00.000Z"),
                Transaction(time = "2023-11-13T00:00:00.000Z"),
            ).sortedBy {
                ZonedDateTime.parse(it.time).toLocalDateTime().toString()
            }

            val headers = operationList.map {
                formatTime(it.time).toString()
            }.toSet()

            withContext(Dispatchers.Main) {
                _transactionFlow.emit(operationList)
                _headersFlow.emit(headers)
            }
        }
    }

    fun getMatchingItems(time: String): List<Transaction> {
        return _transactionFlow.value.filter { info -> (formatTime(info.time) == time) }
    }
}