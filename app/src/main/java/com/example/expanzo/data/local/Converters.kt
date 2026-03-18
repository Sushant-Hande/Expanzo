package com.example.expanzo.data.local

import androidx.room.TypeConverter
import com.example.expanzo.data.model.ExpenseCategory

class Converters {
    @TypeConverter
    fun fromExpenseCategory(value: ExpenseCategory): String {
        return value.name
    }

    @TypeConverter
    fun toExpenseCategory(value: String): ExpenseCategory {
        return ExpenseCategory.valueOf(value)
    }
}
