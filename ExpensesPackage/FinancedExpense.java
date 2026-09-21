package ExpensesPackage;

/*
    Subclass of Expenses. Contains fields and methods for financed expenses.
*/
public class FinancedExpense extends Expenses{
    
    //Fields
    private double cost;
    private double interestRate;
    private double monthlyPayment;
    private int durationInMonths;
    private double amountDesignated;
    
    //Constructor 
    public FinancedExpense(String name, boolean paidOff, boolean transfersToNextMonth,
        boolean isNeeded, String dateDue, double balance, double cost, double interestRate,
        int durationInMonths, double amountDesignated){
        
        super(name, paidOff, transfersToNextMonth, isNeeded, dateDue, balance);
        this.cost = cost;
        this.interestRate = interestRate;
        this.durationInMonths = durationInMonths;
        this.amountDesignated = amountDesignated;
        this.monthlyPayment = calcMonthlyPayment(interestRate, durationInMonths, cost);

        
    }
    
    //Methods 
    /*
        Calculates the monthly payment for a financed expense given an annualized percentage rate,
        total cost, and time horizon > 0
        @return the monthly payment needed to finance an expense
    */
    public double calcMonthlyPayment(double interestRate, int durationInMonths, double cost){
       
        if (!Double.isFinite(cost) || cost < 0 || !Double.isFinite(interestRate) || interestRate < 0|| durationInMonths <= 0) {
        throw new IllegalArgumentException("Cost and interest rate must be finite and nonnegative. "
        + "Duration must be greater than zero.");
}
        double rate = interestRate / 12;
        if (rate == 0){
            return cost / durationInMonths;
        }

        return ((cost * rate) / (1 - Math.pow(1 + rate, -durationInMonths)));
   
    }


    /*
        @return cost - inital cost of the expense
    */
    public double getCost(){
        return cost;
    }

    /*
        @return interestRate - the rate at which an expense
        is financed at 
    */
    public double getInterestRate(){
        return interestRate;
    }


    /*
        @return monthlyPayment - the combined interest and principal
        payment required every month for an expense
    */
    public double getMonthlyPayment(){
        
        return monthlyPayment;
    }

    /*
        @return durationInMonths - # of months required to pay
        off an expense
    */
    public int getDurationInMonths(){
        return durationInMonths;
    }

    /*
        @return amountDesignated - amount of money to be set aside
        to finance an entire expense
    */
    public double getAmountDesignated(){
        return amountDesignated;
    }
    /*
        @return message to the user about their expense, 
        remaining duration, and oustanding balance.
    */
    @Override 
    public String toString(){
        return "Financed expense of " + this.getName() + " that requires " + this.getMonthlyPayment()
         + "a month for " + this.getDurationInMonths() + " months.";
    }
}
