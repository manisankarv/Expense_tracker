import java.util.ArrayList;
import java.util.Scanner;

class Expense {
    String description;
    float amount;

    Expense(String description, float amount) {
        this.description = description;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("%-20s $%.2f", description, amount);
    }
}

public class ExpenseTracker {

    private static final int MAX_EXPENSES = 100;
    private static final ArrayList<Expense> expenses = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n=== Expense Tracker Menu ===");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. View Total Expenses");
            System.out.println("4. Delete Expense");
            System.out.println("5. Edit Expense");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = getIntInput();

            switch (choice) {
                case 1 -> addExpense();
                case 2 -> viewExpenses();
                case 3 -> viewTotal();
                case 4 -> deleteExpense();
                case 5 -> editExpense();
                case 6 -> System.out.println("Exiting Expense Tracker. Goodbye!");
                default -> System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 6);
    }

    private static void addExpense() {
        if (expenses.size() >= MAX_EXPENSES) {
            System.out.println("Expense tracker is full. Cannot add more expenses.");
            return;
        }

        System.out.print("Enter expense description: ");
        String description = scanner.nextLine();

        System.out.print("Enter expense amount: ");
        float amount = getFloatInput();

        expenses.add(new Expense(description, amount));
        System.out.println("Expense added successfully!");
    }

    private static void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses to display.");
            return;
        }

        System.out.println("\nExpense List:");
        System.out.printf("%-5s%-20s%-10s%n", "No.", "Description", "Amount");
        System.out.println("--------------------------------------");

        for (int i = 0; i < expenses.size(); i++) {
            System.out.printf("%-5d%s%n", i + 1, expenses.get(i));
        }
    }

    private static void viewTotal() {
        float total = 0;
        for (Expense expense : expenses) {
            total += expense.amount;
        }
        System.out.printf("Total Expenses: $%.2f%n", total);
    }

    private static void deleteExpense() {
        viewExpenses();
        if (expenses.isEmpty()) return;

        System.out.print("Enter expense number to delete: ");
        int index = getIntInput() - 1;

        if (index >= 0 && index < expenses.size()) {
            expenses.remove(index);
            System.out.println("Expense deleted successfully!");
        } else {
            System.out.println("Invalid index. No expense deleted.");
        }
    }

    private static void editExpense() {
        viewExpenses();
        if (expenses.isEmpty()) return;

        System.out.print("Enter expense number to edit: ");
        int index = getIntInput() - 1;

        if (index >= 0 && index < expenses.size()) {
            System.out.print("Enter new description: ");
            String newDescription = scanner.nextLine();

            System.out.print("Enter new amount: ");
            float newAmount = getFloatInput();

            expenses.get(index).description = newDescription;
            expenses.get(index).amount = newAmount;
            System.out.println("Expense updated successfully!");
        } else {
            System.out.println("Invalid index. No expense updated.");
        }
    }

    private static int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }

    private static float getFloatInput() {
        while (true) {
            try {
                return Float.parseFloat(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid amount. Please enter a valid number: ");
            }
        }
    }
}
