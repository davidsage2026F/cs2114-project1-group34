package goalsclasses;
/*
    OneTimePurchase class specifies that the goal is a purchase made once
    with an overpay variable in case the user pays more than the goal
    costs. Updates automatically if the goal is met and allows for the
    user to add funding over time.

*/
public class OneTimePurchase extends Goals{
    //Fields
    private double amountDesignated;
    private double overPay;

    // Constructor

    public OneTimePurchase(String name, double cost, double designated) {
        super(name, cost, true);
        amountDesignated = designated;
        overPay = checkIfMet();
    }
    /*
        @return double
        - amount set aside for the goal
    */
    public double getAmountDesignated() {
        return amountDesignated;
    }
    /*
        Calls to check if the goal is met after adding funding
        @param double
        - amount to be added towards goal
    */
    public void addFunding(double amount) { 
        amountDesignated += amount;
        overPay = checkIfMet();
    }
    /*
        Checks if the goal is met and returns overpay amount
        @return double
        - amount of overpay
    */
    private double checkIfMet() { 
        if (amountDesignated >= super.getCost()) {
            super.metGoal();
            return amountDesignated - super.getCost();
        }
        return 0.0;
    }
    /*
        @return double
        - amount of overpay
    */
    public double giveOverPay() {
        return overPay;
    }

    /*
        @return String
        - the toString return for the class
    */
    @Override
    public String toString() {
        return "One time purchase of " + this.getName() + " that cost $" + this.getCost() + ".";
    }
}
