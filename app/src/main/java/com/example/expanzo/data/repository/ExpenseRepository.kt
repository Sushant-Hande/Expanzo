package com.example.expanzo.data.repository

import com.example.expanzo.data.local.ExpenseDao
import com.example.expanzo.data.model.Expense
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {
    fun getAllExpenses(): Flow<List<Expense>>
    fun getTotalSpent(): Flow<Double?>
    suspend fun insertExpense(expense: Expense)
    suspend fun deleteExpense(expense: Expense)
}

class OfflineExpenseRepository(private val expenseDao: ExpenseDao) : ExpenseRepository {
    override fun getAllExpenses(): Flow<List<Expense>> = expenseDao.getAllExpenses()

    override fun getTotalSpent(): Flow<Double?> = expenseDao.getTotalSpent()

    override suspend fun insertExpense(expense: Expense) {
        expenseDao.insertExpense(expense)
    }

    override suspend fun deleteExpense(expense: Expense) {
        expenseDao.deleteExpense(expense)
    }
}
