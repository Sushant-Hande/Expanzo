# Project Plan

Expanzo: A simple Personal Expense Tracker.
Features:
- Total Spent card at the top.
- Scrollable list of recent transactions.
- Floating Action Button (FAB) to add an expense via a BottomSheet or new screen.
- Material 3 design elements.
- Light-green "financial" color motif.
- Specific colors for categories (e.g., Red for Food, Blue for Transport).
- Full Edge-to-Edge Display.
- Adaptive app icon.
- Material color system for light and dark themes.

## Project Brief

# Expanzo: Personal Expense Tracker Project Brief

Expanzo is a streamlined personal finance application designed to help users monitor their spending with ease. It emphasizes a clean, Material 3 aesthetic with a focus on financial clarity
 and intuitive interaction.

### Features
*   **Total Spent Dashboard**: A prominent card at the top of the main screen providing an immediate overview of total expenditures.
*   **Recent Transactions List**: A scrollable history of expenses featuring category-specific color-coding (e.g., Red for Food, Blue for
 Transport) for quick visual identification.
*   **Quick Add Expense**: A Floating Action Button (FAB) that triggers a Material 3 Bottom Sheet, allowing users to rapidly log new transactions.
*   **Adaptive & Edge-to-Edge UI**: A modern interface that supports full edge-to-edge display and includes
 an adaptive app icon to match the system's design.

### High-Level Technical Stack
*   **Kotlin**: The primary programming language for robust and concise development.
*   **Jetpack Compose**: A modern toolkit for building native, declarative UI with Material 3 components.
*   **Kotlin Coroutines**: For managing asynchronous tasks and ensuring a smooth, non-blocking user experience.
*   **KSP (Kotlin Symbol Processing)**: Used for efficient code generation, replacing KAPT.
*   **Android Architecture Components**: Utilizing ViewModel for state management and Navigation Compose for seamless screen transitions.
*   **Material
 Color System**: Implementation of dynamic light and dark themes using Material 3 color utilities.

## Implementation Steps

### Task_1_SetupDataLayer: Set up the Room database, including the Expense entity (category, amount, date, description), DAO with CRUD operations, and a Repository to manage data flow.
- **Status:** COMPLETED
- **Updates:** Room-based data layer implemented and verified. Expense entity, ExpenseDao, ExpenseDatabase, and ExpenseRepository are in place under `com.example.expanzo.data`. CRUD operations and reactive data flow using Flow are supported. All dependencies (Room, KSP) are configured.
- **Acceptance Criteria:**
  - Room database and entities are defined
  - DAO provides methods for Insert and Query
  - Repository provides a Flow of all expenses

### Task_2_MainDashboardUI: Develop the main dashboard UI with a 'Total Spent' card and a scrollable list of recent transactions, including category-specific color-coding.
- **Status:** COMPLETED
- **Updates:** Dashboard UI implemented and verified. MainViewModel, DashboardScreen, and custom Theme (Light-green "financial" motif) are in place. Total Spent card and categorized transaction list are functional. ExpenseRepository is integrated with the ViewModel. Application class and Manifest updated. Project builds successfully.
- **Acceptance Criteria:**
  - Summary card displays total spending accurately
  - Transaction list items use specific colors for categories (Red for Food, Blue for Transport, etc.)
  - ViewModel correctly provides state to the UI

### Task_3_AddExpenseFeature: Implement a Floating Action Button that opens a Material 3 Bottom Sheet to allow users to add new expense records.
- **Status:** COMPLETED
- **Updates:** Add Expense feature implemented and verified. FAB on the dashboard successfully launches the Material 3 `ModalBottomSheet`. Users can input amount, category, and description. New expenses are immediately reflected in the "Total Spent" card and "Recent Transactions" list. ViewModel updated with `addExpense` function. Project builds successfully.
- **Acceptance Criteria:**
  - FAB is visible and functional
  - BottomSheet allows entering amount, category, and description
  - Newly added expenses appear in the list immediately

### Task_4_PolishAndVerify: Implement Material 3 light/dark themes with a light-green motif, full Edge-to-Edge display, and an adaptive app icon. Run and verify the app.
- **Status:** COMPLETED
- **Updates:** Final polish and verification completed. Material 3 themes (Light/Dark) with a "Financial Green" motif are implemented. Adaptive app icon is integrated. Edge-to-Edge display is functional. Critic agent verified the app is stable, functionally complete, and follows Material Design 3 guidelines. No crashes or core feature issues found. All acceptance criteria met.
- **Acceptance Criteria:**
  - Light and Dark themes are supported with M3 color system
  - App displays edge-to-edge
  - Adaptive app icon is present
  - App builds, runs, and does not crash
  - All existing tests pass
- **Duration:** N/A

