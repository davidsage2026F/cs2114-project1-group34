package goalsclasses;
/*
    OneTimePurchase class specifies that the goal is a purchase made once
    with an overpay variable in case the user pays more than the goal
    costs. Updates automatically if the goal is met and allows for the
    user to add funding over time.

*/
public class Investments extends Goals{

    // Fields
    private double amountInvested;
    private double yearlyROI;

    // Constructor
    public Investments(String name, double amountInvested, double yearlyROI){
        super(name, -1.0, false);
        this.amountInvested = amountInvested;
        this.yearlyROI = yearlyROI;
    }

    public Investments(String name, double amountInvested) {
        super(name, -1, false);
        this.amountInvested = amountInvested;
        this.yearlyROI = 0.08;
    }

    // Getter and setter methods
    public double getAmountInvested(){
        return amountInvested;
    }

    public double getYearlyROI(){
        return yearlyROI;
    }

    // Methods
    public void addToInvestment(double investAmount){
        amountInvested = amountInvested + investAmount;
    }

    public double[] calculateInvestment(int monthsIntoFuture) {
        double[] amounts = new double[10];
        for (int i = 0; i <= 9; i++) {
            amounts[i] = monthAmount((monthsIntoFuture / 10) * i);
        }
        return amounts;
    }

    public void updateInvestment() {
        addToInvestment(amountInvested * (yearlyROI / 12));
    }

    private double monthAmount(int month) {
        return amountInvested * (yearlyROI / 12) * month;
    }

    @Override
    public double getCost() {
        return getAmountInvested();
    }
    
    @Override
    public String toString() {
        return "Investment " + this.getName() + " with a yearly ROI of " + yearlyROI * 100 + "% and an amount invested of $" + amountInvested + ".";
    }
}
