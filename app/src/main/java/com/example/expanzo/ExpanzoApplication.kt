package com.example.expanzo

import android.app.Application
import androidx.room.Room
import com.example.expanzo.data.local.ExpenseDatabase
import com.example.expanzo.data.repository.ExpenseRepository
import com.example.expanzo.data.repository.OfflineExpenseRepository

class ExpanzoApplication : Application() {
    
    private val database: ExpenseDatabase by lazy {
        Room.databaseBuilder(
            this,
            ExpenseDatabase::class.java,
            ExpenseDatabase.DATABASE_NAME
        ).build()
    }
    
    val repository: ExpenseRepository by lazy {
        OfflineExpenseRepository(database.expenseDao())
    }
}
