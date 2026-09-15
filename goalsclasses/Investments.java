package goalsclasses;

public class Investments extends Goals{

    // Fields
    private double amountInvested;
    private double yearlyROI;
    private int timeFrame;

    // Constructor
    public Investment(double amountInvested, double yearlyROI, int timeFrame){
        this.amountInvested = amountInvested;
        this.yearlyROI = yearlyROI;
        this.timeFrame = timeFrame;
    }

    // Getter and setter methods
    public void setAmountInvested(amountInvested){
        this.amountInvested = amountInvested;
    }
    public double getAmountInvested(){
        return amountInvested;
    }

    public void setYearlyReturnOnInvestment(yearlyROI){
        this.yearlyROI = yearlyROI;
    }
    public int getYearlyReturnOnInvestment(){
        return yearlyROI;
    }

    public void setTimeFrame(timeFrame){
        this.timeFrame = timeFrame
    }
    public int getTimeFrame(return timeFrame){
        return timeFrame;
    }

    // Methods
    public void addToInvestment(double investAmount){
        amountInvested = amountInvested + investAmount;
    }


    
}
