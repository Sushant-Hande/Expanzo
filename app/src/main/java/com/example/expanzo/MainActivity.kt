package com.example.expanzo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.*
import com.example.expanzo.ui.AddExpenseBottomSheet
import com.example.expanzo.ui.DashboardScreen
import com.example.expanzo.ui.MainViewModel
import com.example.expanzo.ui.MainViewModelFactory
import com.example.expanzo.ui.theme.ExpanzoTheme

class MainActivity : ComponentActivity() {
    
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as ExpanzoApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExpanzoTheme {
                var showBottomSheet by remember { mutableStateOf(false) }

                DashboardScreen(
                    viewModel = viewModel,
                    onAddExpenseClick = { showBottomSheet = true }
                )

                if (showBottomSheet) {
                    AddExpenseBottomSheet(
                        onDismiss = { showBottomSheet = false },
                        onSave = { expense ->
                            viewModel.addExpense(expense)
                            showBottomSheet = false
                        }
                    )
                }
            }
        }
    }
}
