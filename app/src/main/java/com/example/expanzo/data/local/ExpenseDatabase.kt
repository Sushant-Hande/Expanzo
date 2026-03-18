package com.example.expanzo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.expanzo.data.model.Expense

@Database(entities = [Expense::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ExpenseDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao

    companion object {
        const val DATABASE_NAME = "expanzo_db"
    }
}
