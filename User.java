import ExpensesPackage.Expenses;
import ExpensesPackage.OneTimeExpense;
import ExpensesPackage.RecurringExpense;
import ExpensesPackage.FinancedExpense;
import goalsclasses.Goals;
import goalsclasses.Investments;
import goalsclasses.OneTimePurchase;
import goalsclasses.RecurringPurchase;
import goalsclasses.FinancedGoal;
// Gives access to Java's File class. User.java uses in loadProfile() to point at the saved profile .txt file before reading
import java.io.File;
// An exception that java will throw when code tries to open a file that doesn't exist
import java.io.FileNotFoundException;
// Used to write text to a text file
import java.io.PrintWriter;
// Resizable list used for goals, investments, and expenses
import java.util.ArrayList;
// An exception that java will throw when trying to read from text file but there is nothing to read
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * User/ main class: opens the CLI, presents the menu options, and creates,
 * saves and loads the user's profile.
 */
public class User {

    // Range limits used for validation
    private static final double MAX_AMOUNT = 1_000_000_000.0;
    private static final int MAX_AGE = 120;

    // Fields
    private String name;
    private int age;
    private double income;
    private double currentSavings;

    // ArrayLists
    private final ArrayList<Goals> goals = new ArrayList<>(); // any Goals subclass
    private final ArrayList<Investments> investments = new ArrayList<>(); // investments specifically
    private final ArrayList<Expenses> expenses = new ArrayList<>(); // any Expenses subclass

    private final Scanner scanner = new Scanner(System.in);

    /** Sets up a new user profile, and will throw IllegalArgumentException on bad values. */
    public void createUser(String name, int age, double income, double currentSavings) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name can't be empty. Please try again.");
        }
        if (age < 0 || age > MAX_AGE) {
            throw new IllegalArgumentException("Age must be between 0 and " + MAX_AGE + ".");
        }
        if (!isValidAmount(income) || !isValidAmount(currentSavings)) {
            throw new IllegalArgumentException(
                "Income and savings must be between 0 and " + (long) MAX_AMOUNT + ".");
        }
        this.name = name.trim();
        this.age = age;
        this.income = income;
        this.currentSavings = currentSavings;
    }

    /** Stores the profile info in a text file named after the user. */
    public void saveProfile() {
        if (name == null) {
            System.out.println("There is no profile to save yet.");
            return;
        }
        String fileName = fileNameFor(name);
        try (PrintWriter out = new PrintWriter(fileName)) {
            out.println(name);
            out.println(age);
            out.println(income);
            out.println(currentSavings);
            System.out.println("Profile saved to " + fileName + ".");
        } catch (FileNotFoundException e) {
            System.out.println("Could not save profile: " + e.getMessage());
        }
    }

    /** Reads a profile back from a text file written by saveProfile(). */
    public void loadProfile(String fileName) {
        try (Scanner in = new Scanner(new File(fileName))) {
            String loadedName = in.nextLine();
            int loadedAge = Integer.parseInt(in.nextLine().trim());
            double loadedIncome = Double.parseDouble(in.nextLine().trim());
            double loadedSavings = Double.parseDouble(in.nextLine().trim());
            createUser(loadedName, loadedAge, loadedIncome, loadedSavings);
            System.out.println("Loaded profile for " + name + ".");
        } catch (FileNotFoundException e) {
            System.out.println("No saved profile found: " + fileName);
        } catch (NoSuchElementException | IllegalArgumentException e) {
            // NumberFormatException is a kind of IllegalArgumentException
            System.out.println("That profile file is missing data or is corrupted.");
        }
    }

    /** Prints the main menu. */
    public void displayMenu() {
        System.out.println();
        System.out.println("=== Welcome to Budget Buddy! ===");
        System.out.println("1. Create a new profile");
        System.out.println("2. Load an existing profile");
        System.out.println("3. Manage expenses");
        System.out.println("4. Manage goals");
        System.out.println("5. Manage investments");
        System.out.println("6. View summary");
        System.out.println("7. Save and quit");
    }

    public double getIncome() {
        return income;
    }

    public double getCurrentSavings() {
        return currentSavings;
    }

    // CLI loop
    public void run() {
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = readInt("Choose an option: ", 1, 7);
            switch (choice) {
                case 1 -> createProfileFromPrompts();
                case 2 -> {
                    String profileName = readNonEmpty("Profile name to load: ");
                    loadProfile(fileNameFor(profileName));
                }
                case 3 -> { if (hasProfile()) manageExpenses(); }
                case 4 -> { if (hasProfile()) manageGoals(); }
                case 5 -> { if (hasProfile()) manageInvestments(); }
                case 6 -> viewSummary();
                case 7 -> {
                    saveProfile();
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void createProfileFromPrompts() {
        String newName = readNonEmpty("Name: ");
        int newAge = readInt("Age: ", 0, MAX_AGE);
        double newIncome = readDouble("Monthly income: ", 0, MAX_AMOUNT);
        double newSavings = readDouble("Current savings: ", 0, MAX_AMOUNT);
        createUser(newName, newAge, newIncome, newSavings);
        saveProfile();
    }

    // Shows the Expenses menu
    private void manageExpenses() {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("Expenses Menu");
            System.out.println("1. Add a one-time expense");
            System.out.println("2. Add a recurring expense");
            System.out.println("3. Add a financed expense");
            System.out.println("4. Make a payment on an expense");
            System.out.println("5. View all expenses");
            System.out.println("6. Back to main menu");
            switch (readInt("Choose an option: ", 1, 6)) {
                case 1 -> addOneTimeExpense();
                case 2 -> addRecurringExpense();
                case 3 -> addFinancedExpense();
                case 4 -> makeExpensePayment();
                case 5 -> listExpenses();
                case 6 -> back = true;
            }
        }
    }

    private void addOneTimeExpense() {
        String expName = readNonEmpty("Expense name: ");
        boolean isNeeded = readYesNo("Is this expense necessary? (y/n): ");
        String dateDue = readNonEmpty("Date due (e.g. 9/30/26): ");
        double balance = readDouble("Amount owed: ", 0, MAX_AMOUNT);
        Expenses expense = new OneTimeExpense(expName, isNeeded, dateDue, balance);
        expenses.add(expense);
        System.out.println("Added: " + expense);
    }

    private void addRecurringExpense() {
        String expName = readNonEmpty("Expense name: ");
        boolean isNeeded = readYesNo("Is this expense necessary? (y/n): ");
        String dateDue = readNonEmpty("Date due (e.g. 9/30/26): ");
        double monthlyPayment = readDouble("Monthly payment: ", 0, MAX_AMOUNT);
        Expenses expense = new RecurringExpense(expName, isNeeded, dateDue, monthlyPayment);
        expenses.add(expense);
        System.out.println("Added: " + expense);
    }

    private void addFinancedExpense() {
        String expName = readNonEmpty("Expense name: ");
        boolean isNeeded = readYesNo("Is this expense necessary? (y/n): ");
        double cost = readDouble("Total cost: ", 0, MAX_AMOUNT);
        double downPayment = readDouble("Down payment: ", 0, cost);
        double interestRate = readDouble("Annual interest rate (e.g. 0.05 for 5%): ", 0, 1);
        int durationInMonths = readInt("Duration in months: ", 1, 1200);
        double amountDesignated = readDouble("Amount you plan to set aside monthly: ", 0, MAX_AMOUNT);
        Expenses expense = new FinancedExpense(
            expName, isNeeded, cost, downPayment, interestRate, durationInMonths, amountDesignated);
        expenses.add(expense);
        System.out.println("Added: " + expense);
        if (expense instanceof FinancedExpense financed && !financed.sufficientFunding()) {
            System.out.printf("Warning: $%.2f a month isn't enough to cover the required $%.2f payment.%n",
                amountDesignated, financed.getMonthlyPayment());
        }
    }

    private void makeExpensePayment() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses yet.");
            return;
        }
        listExpenses();
        int index = readInt("Which expense number? ", 1, expenses.size()) - 1;
        Expenses expense = expenses.get(index);
        double amount = readDouble("Payment amount: ", 0, MAX_AMOUNT);
        try {
            double remaining = expense.makePayment(amount);
            System.out.printf("Payment applied. $%.2f remaining.%n", remaining);
        } catch (IllegalArgumentException | UnsupportedOperationException e) {
            System.out.println("Payment failed: " + e.getMessage());
        }
    }

    private void listExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses yet.");
            return;
        }
        for (int i = 0; i < expenses.size(); i++) {
            System.out.println((i + 1) + ". " + expenses.get(i));
        }
    }

    // Goals menu

    private void manageGoals() {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("--- Goals ---");
            System.out.println("1. Add a one-time purchase goal");
            System.out.println("2. Add a recurring purchase goal");
            System.out.println("3. Add a financed goal");
            System.out.println("4. Add funding to a one-time purchase goal");
            System.out.println("5. View all goals");
            System.out.println("6. Back to main menu");
            switch (readInt("Choose an option: ", 1, 6)) {
                case 1 -> addOneTimePurchaseGoal();
                case 2 -> addRecurringPurchaseGoal();
                case 3 -> addFinancedGoal();
                case 4 -> addFundingToGoal();
                case 5 -> listGoals();
                case 6 -> back = true;
            }
        }
    }

    private void addOneTimePurchaseGoal() {
        String goalName = readNonEmpty("Goal name: ");
        double cost = readDouble("Cost: ", 0, MAX_AMOUNT);
        double designated = readDouble("Amount already set aside: ", 0, cost);
        Goals goal = new OneTimePurchase(goalName, cost, designated);
        goals.add(goal);
        System.out.println("Added: " + goal);
    }

    private void addRecurringPurchaseGoal() {
        String goalName = readNonEmpty("Goal name: ");
        double cost = readDouble("Monthly cost: ", 0, MAX_AMOUNT);
        Goals goal = new RecurringPurchase(goalName, cost);
        goals.add(goal);
        System.out.println("Added: " + goal);
    }

    private void addFinancedGoal() {
        String goalName = readNonEmpty("Goal name: ");
        double cost = readDouble("Total cost: ", 0, MAX_AMOUNT);
        double moneyDown = readDouble("Money down: ", 0, cost);
        double interestRate = readDouble("Annual interest rate (e.g. 0.05 for 5%): ", 0, 1);
        int duration = readInt("Duration in months: ", 1, 1200);
        Goals goal = new FinancedGoal(goalName, cost, moneyDown, interestRate, duration);
        goals.add(goal);
        System.out.println("Added: " + goal);
    }

    private void addFundingToGoal() {
        // Only OneTimePurchase supports addFunding()
        ArrayList<OneTimePurchase> fundable = new ArrayList<>();
        for (Goals goal : goals) {
            if (goal instanceof OneTimePurchase oneTime) {
                fundable.add(oneTime);
            }
        }
        if (fundable.isEmpty()) {
            System.out.println("No one-time purchase goals to fund yet.");
            return;
        }
        for (int i = 0; i < fundable.size(); i++) {
            System.out.println((i + 1) + ". " + fundable.get(i));
        }
        int index = readInt("Which goal number? ", 1, fundable.size()) - 1;
        double amount = readDouble("Amount to add: ", 0, MAX_AMOUNT);
        OneTimePurchase goal = fundable.get(index);
        goal.addFunding(amount);
        System.out.println(goal.goalMet() ? "Goal met! Overpay: $" + goal.giveOverPay() : "Funding added.");
    }

    private void listGoals() {
        if (goals.isEmpty()) {
            System.out.println("No goals yet.");
            return;
        }
        for (int i = 0; i < goals.size(); i++) {
            Goals goal = goals.get(i);
            System.out.println((i + 1) + ". " + goal + (goal.goalMet() ? " (met)" : ""));
        }
    }

    // Investments menu

    private void manageInvestments() {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("--- Investments ---");
            System.out.println("1. Add an investment");
            System.out.println("2. Add funds to an investment");
            System.out.println("3. View projection");
            System.out.println("4. View all investments");
            System.out.println("5. Back to main menu");
            switch (readInt("Choose an option: ", 1, 5)) {
                case 1 -> addInvestment();
                case 2 -> addFundsToInvestment();
                case 3 -> viewInvestmentProjection();
                case 4 -> listInvestments();
                case 5 -> back = true;
            }
        }
    }

    private void addInvestment() {
        String investName = readNonEmpty("Investment name: ");
        double amount = readDouble("Amount invested: ", 0, MAX_AMOUNT);
        double yearlyROI = readDouble("Expected yearly return (e.g. 0.08 for 8%): ", 0, 1);
        Investments investment = new Investments(investName, amount, yearlyROI);
        investments.add(investment);
        System.out.println("Added: " + investment);
    }

    private void addFundsToInvestment() {
        if (investments.isEmpty()) {
            System.out.println("No investments yet.");
            return;
        }
        listInvestments();
        int index = readInt("Which investment number? ", 1, investments.size()) - 1;
        double amount = readDouble("Amount to add: ", 0, MAX_AMOUNT);
        investments.get(index).addToInvestment(amount);
        System.out.println("Updated: " + investments.get(index));
    }

    private void viewInvestmentProjection() {
        if (investments.isEmpty()) {
            System.out.println("No investments yet.");
            return;
        }
        listInvestments();
        int index = readInt("Which investment number? ", 1, investments.size()) - 1;
        int months = readInt("Project how many months into the future? ", 1, 1200);
        
        double[] projection = investments.get(index).calculateInvestment(months);
        for (int i = 0; i < projection.length; i++) {
                System.out.printf("Interval %d: $%.2f%n", i + 1, projection[i]);
        }
    }

    private void listInvestments() {
        if (investments.isEmpty()) {
            System.out.println("No investments yet.");
            return;
        }
        for (int i = 0; i < investments.size(); i++) {
            System.out.println((i + 1) + ". " + investments.get(i));
        }
    }

    private void viewSummary() {
        if (!hasProfile()) {
            return;
        }
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.printf("Income: $%.2f%n", income);
        System.out.printf("Current savings: $%.2f%n", currentSavings);
        System.out.println();
        System.out.println("Expenses:");
        listExpenses();
        System.out.println();
        System.out.println("Goals:");
        listGoals();
        System.out.println();
        System.out.println("Investments:");
        listInvestments();
    }

    private boolean hasProfile() {
        if (name == null) {
            System.out.println("Create or load a profile first (option 1 or 2).");
            return false;
        }
        return true;
    }

    // Input validation helpers

    /** Re-prompts on empty or missing input. */
    private String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                return line;
            }
            System.out.println("Input can't be empty. Try again.");
        }
    }

    /** Re-prompts until the answer starts with y/n (case-insensitive). */
    private boolean readYesNo(String prompt) {
        while (true) {
            String line = readNonEmpty(prompt).toLowerCase();
            if (line.startsWith("y")) {
                return true;
            }
            if (line.startsWith("n")) {
                return false;
            }
            System.out.println("Please answer y or n.");
        }
    }

    /** Re-prompts on non-numbers and on values outside [min, max]. */
    private int readInt(String prompt, int min, int max) {
        while (true) {
            String line = readNonEmpty(prompt);
            try {
                int value = Integer.parseInt(line);
                if (value < min || value > max) {
                    System.out.println("Enter a number between " + min + " and " + max + ".");
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println("That isn't a valid whole number. Try again.");
            }
        }
    }

    /** Re-prompts on non-numbers and on values outside [min, max]. */
    private double readDouble(String prompt, double min, double max) {
        while (true) {
            String line = readNonEmpty(prompt);
            try {
                double value = Double.parseDouble(line);
                if (Double.isNaN(value)) {
                    System.out.println("That isn't a valid number. Try again.");
                } else if (value < min || value > max) {
                    System.out.println("Enter an amount between " + min + " and " + (long) max + ".");
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println("That isn't a valid number. Try again.");
            }
        }
    }

    private static boolean isValidAmount(double value) {
        return !Double.isNaN(value) && value >= 0 && value <= MAX_AMOUNT;
    }

    private static String fileNameFor(String profileName) {
        return profileName.trim().replaceAll("[^A-Za-z0-9]", "_") + ".txt";
    }

    public static void main(String[] args) {
        new User().run();
    }
}