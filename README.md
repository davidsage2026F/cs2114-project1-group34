READM# CS 2114 Project 1 — Personal Finance CLI

**Group 34** — repo: `cs2114-project1-group34`

## Overview

A command-line personal finance application written in Java. Users can create,
save, and load a profile, then track financial goals and expenses through a menu-driven interface.

## Features

- Create a new user profile or load an existing one from a saved text file
- Save profile data back to a text file
- Track financial goals:
  - One-time purchases
  - Recurring purchases
  - Financed goals
  - Investments
- Track expenses:
  - One-time expenses
  - Recurring expenses
  - Financed expenses

## Project Structure

```
cs2114-project1-group34/
├── User.java                  # Entry point; displays menu, manages profile
├── goalsclasses/
│   ├── Goals.java             # Base class for financial goals
│   ├── OneTimePurchase.java
│   ├── RecurringPurchase.java
│   ├── FinancedGoal.java
│   └── Investment.java
└── ExpensesPackage/
    ├── Expenses.java          # Base class for expenses
    ├── OneTimeExpense.java
    ├── RecurringExpense.java
    └── FinancedExpense.java
```

## Class Design

- **`User`** — Opens the main menu, and handles creating, saving, and loading
  a user profile (persisted as a text file).
- **`Goals`** — Base class extended by `OneTimePurchase`, `RecurringPurchase`,
  `FinancedGoal`, and `Investment`.
- **`Expenses`** — Base class extended by `OneTimeExpense`,
  `RecurringExpense`, and `FinancedExpense`.

## Getting Started

1. Clone the repository:
   ```
   git clone <repo-url>
   cd cs2114-project1-group34
   ```
2. Open the project in VS Code (or your preferred Java IDE).
3. Compile and run:
   ```
   javac User.java goalsclasses/*.java ExpensesPackage/*.java
   java User
   ```