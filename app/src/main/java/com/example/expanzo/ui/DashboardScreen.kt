package com.example.expanzo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expanzo.data.model.Expense
import com.example.expanzo.data.model.ExpenseCategory
import com.example.expanzo.ui.theme.*
import java.text.NumberFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    viewModel: MainViewModel,
    onAddExpenseClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    DashboardContent(
        uiState = uiState,
        onAddExpenseClick = onAddExpenseClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardContent(
    uiState: MainUiState,
    onAddExpenseClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Expanzo", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddExpenseClick,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Expense")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            TotalSpentCard(totalAmount = uiState.totalSpent)
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                text = "Recent Transactions",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            if (uiState.expenses.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No transactions yet. Tap + to add one!", style = MaterialTheme.typography.bodyLarge)
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(uiState.expenses) { expense ->
                        ExpenseItem(expense = expense)
                    }
                }
            }
        }
    }
}

@Composable
fun TotalSpentCard(totalAmount: Double) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Total Spent",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = formatCurrency(totalAmount),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = 36.sp
            )
        }
    }
}

@Composable
fun ExpenseItem(expense: Expense) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CategoryIndicator(category = expense.category)
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = expense.description,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = expense.category.name.lowercase().replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
            
            Text(
                text = formatCurrency(expense.amount),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
fun CategoryIndicator(category: ExpenseCategory) {
    val color = when (category) {
        ExpenseCategory.FOOD -> CategoryFood
        ExpenseCategory.TRANSPORT -> CategoryTransport
        ExpenseCategory.SHOPPING -> CategoryShopping
        ExpenseCategory.ENTERTAINMENT -> CategoryEntertainment
        ExpenseCategory.HEALTH -> CategoryHealth
        ExpenseCategory.BILLS -> CategoryBills
        ExpenseCategory.EDUCATION -> CategoryEducation
        ExpenseCategory.OTHERS -> CategoryOthers
    }
    
    Box(
        modifier = Modifier
            .size(12.dp)
            .background(color, CircleShape)
    )
}

fun formatCurrency(amount: Double): String {
    val format = NumberFormat.getCurrencyInstance(Locale.US)
    return format.format(amount)
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp,navigation=buttons")
@Composable
fun DashboardPreview() {
    ExpanzoTheme {
        DashboardContent(
            uiState = MainUiState(
                expenses = listOf(
                    Expense(1, ExpenseCategory.FOOD, 25.50, System.currentTimeMillis(), "Lunch"),
                    Expense(2, ExpenseCategory.TRANSPORT, 15.00, System.currentTimeMillis(), "Uber"),
                    Expense(3, ExpenseCategory.SHOPPING, 120.00, System.currentTimeMillis(), "New Shoes")
                ),
                totalSpent = 160.50
            ),
            onAddExpenseClick = {}
        )
    }
}
