package goalsclasses;

public class OneTimePurchase extends Goals{
    //Fields
    private double amountDesignated;
    private double overPay;

    // Constructor
    public OneTimePurchase(String name, double cost, double designated) {
        super(name, cost, false);
        amountDesignated = designated;
        overPay = checkIfMet();
    }

    public double getAmountDesignated() {
        return amountDesignated;
    }

    public void addFunding(double amount) { 
        amountDesignated += amount;
        checkIfMet();
    }

    private double checkIfMet() { 
        if (amountDesignated >= super.getCost()) {
            super.metGoal();
            return amountDesignated - super.getCost();
        }
        return 0.0;
    }

    public double giveOverPay() {
        return overPay;
    }
}
