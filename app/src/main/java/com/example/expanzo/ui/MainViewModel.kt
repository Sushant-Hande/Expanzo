package com.example.expanzo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expanzo.data.model.Expense
import com.example.expanzo.data.repository.ExpenseRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class MainUiState(
    val expenses: List<Expense> = emptyList(),
    val totalSpent: Double = 0.0
)

class MainViewModel(private val repository: ExpenseRepository) : ViewModel() {

    val uiState: StateFlow<MainUiState> = repository.getAllExpenses()
        .map { expenses ->
            MainUiState(
                expenses = expenses,
                totalSpent = expenses.sumOf { it.amount }
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = MainUiState()
        )

    fun addExpense(expense: Expense) {
        viewModelScope.launch {
            repository.insertExpense(expense)
        }
    }
}
