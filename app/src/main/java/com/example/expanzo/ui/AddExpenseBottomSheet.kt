package com.example.expanzo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.expanzo.data.model.Expense
import com.example.expanzo.data.model.ExpenseCategory
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExpenseBottomSheet(
    onDismiss: () -> Unit,
    onSave: (Expense) -> Unit
) {
    var amount by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(ExpenseCategory.FOOD) }
    var expanded by remember { mutableStateOf(false) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .navigationBarsPadding()
        ) {
            Text(
                text = "Add New Expense",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = amount,
                onValueChange = { if (it.isEmpty() || it.toDoubleOrNull() != null) amount = it },
                label = { Text("Amount") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth(),
                prefix = { Text("$") }
            )

            Spacer(modifier = Modifier.height(8.dp))

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = selectedCategory.name.lowercase().replaceFirstChar { it.uppercase() },
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Category") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier
                        .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    ExpenseCategory.entries.forEach { category ->
                        DropdownMenuItem(
                            text = { Text(category.name.lowercase().replaceFirstChar { it.uppercase() }) },
                            onClick = {
                                selectedCategory = category
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val amountDouble = amount.toDoubleOrNull() ?: 0.0
                    if (amountDouble > 0 && description.isNotBlank()) {
                        onSave(
                            Expense(
                                category = selectedCategory,
                                amount = amountDouble,
                                date = System.currentTimeMillis(),
                                description = description
                            )
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = amount.toDoubleOrNull() != null && description.isNotBlank()
            ) {
                Text("Save Expense")
            }
            
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
