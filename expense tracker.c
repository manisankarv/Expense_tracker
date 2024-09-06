#include <stdio.h>
#include <stdlib.h>

#define MAX_EXPENSES 100

typedef struct {
    char description[100];
    float amount;
} Expense;

void addExpense(Expense expenses[], int *numExpenses) {
    if (*numExpenses < MAX_EXPENSES) {
        printf("Enter expense description: ");
        scanf(" %[^\n]", expenses[*numExpenses].description);

        printf("Enter expense amount: ");
        scanf("%f", &expenses[*numExpenses].amount);

        (*numExpenses)++;
        printf("Expense added successfully!\n");
    } else {
        printf("Expense tracker is full. Cannot add more expenses.\n");
    }
}

void viewExpenses(Expense expenses[], int numExpenses) {
    if (numExpenses == 0) {
        printf("No expenses to display.\n");
    } else {
        printf("Expense list:\n");
        printf("%-20s%-10s\n", "Description", "Amount");
        printf("-------------------- ----------\n");
        for (int i = 0; i < numExpenses; i++) {
            printf("%-20s%-10.2f\n", expenses[i].description, expenses[i].amount);
        }
    }
}

float calculateTotal(Expense expenses[], int numExpenses) {
    float total = 0.0;
    for (int i = 0; i < numExpenses; i++) {
        total += expenses[i].amount;
    }
    return total;
}

int main() {
    Expense expenses[MAX_EXPENSES];
    int numExpenses = 0;
    int choice;

    do {
        printf("\nExpense Tracker Menu:\n");
        printf("1. Add Expense\n");
        printf("2. View Expenses\n");
        printf("3. View Total Expenses\n");
        printf("4. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                addExpense(expenses, &numExpenses);
                break;
            case 2:
                viewExpenses(expenses, numExpenses);
                break;
            case 3:
                printf("Total Expenses: $%.2f\n", calculateTotal(expenses, numExpenses));
                break;
            case 4:
                printf("Exiting Expense Tracker. Goodbye!\n");
                break;
            default:
                printf("Invalid choice. Please enter a valid option.\n");
        }
    } while (choice != 4);

    return 0;