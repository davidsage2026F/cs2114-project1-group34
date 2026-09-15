package ExpensesPackage;
public class FinancedExpense extends Expenses{
    private double cost;
    private double interestRate;
    private double monthlyPayment;
    private int durationInMonths;
    private double amountDesignated;
    
    public FinancedExpense(String name, boolean paidOff, boolean transfersToNextMonth,
        boolean isNeeded, double cost, double interestRate,
        int durationInMonths, double amountDesignated, double monthlyPayment){
        super(name, paidOff, transfersToNextMonth, isNeeded, 
        durationInMonths, amountDesignated);
        
    }
    
    
    public double getCost(){
        return cost;
    }


    public double getInterestRate(){
        return interestRate;
    }


    public double getMonthlyPayment(){
        return monthlyPayment;
    }


    public int getDurationInMonths(){
        return durationInMonths;
    }


    public double getAmountDesignated(){
        return amountDesignated;
    }

}
