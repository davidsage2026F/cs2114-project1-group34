package goalsclasses;

public class RecurringPurchase extends Goals {
    // Fields
    private boolean isExpense;
    // Constructors 
    public RecurringPurchase(String name, double cost) {
        super(name, cost, true);
        isExpense = false;
    }

    public void setPurchaseMade() {
        this.metGoal();
        isExpense = true;
    }

    public boolean isExpense() { 
        return isExpense;
    }

    @Override 
    public String toString() {
        return "Recurring Purchase of " + this.getName() + " that cost $" + this.getCost() + " a month.";
    }
}