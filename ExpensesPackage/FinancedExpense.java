package ExpensesPackage;

/*
    Subclass of Expenses. Contains fields and methods for financed expenses.
*/
public class FinancedExpense extends Expenses{
    
    //Fields
    private double principal;
    private double interestRate;
    private double monthlyPayment;
    private int durationInMonths;
    private double amountDesignated;
    
    //Constructor 
    public FinancedExpense(String name, boolean isNeeded, double cost, 
        double downPayment, double interestRate, int durationInMonths, double amountDesignated){
        
        super(name, false, true, isNeeded, cost - downPayment);
        this.principal = cost - downPayment;
        this.interestRate = interestRate;
        this.durationInMonths = durationInMonths;
        this.amountDesignated = amountDesignated;
        this.monthlyPayment = calcMonthlyPayment(interestRate, durationInMonths, this.principal);

        
    }
    
    //Methods 
    /*
        Calculates the monthly payment for a financed expense given an annualized percentage rate,
        total principal, and time horizon > 0. Assumes fixed rate with on time payments.
        @return the monthly payment needed to finance an expense
    */
    public double calcMonthlyPayment(double interestRate, int durationInMonths, double principal){
       
        if (!Double.isFinite(principal) || principal < 0 || !Double.isFinite(interestRate) || interestRate < 0|| durationInMonths <= 0) {
        throw new IllegalArgumentException("Cost and interest rate must be finite and nonnegative. "
        + "Duration must be greater than zero.");
}       
        double rate = interestRate / 12;
        if (rate == 0){
            return principal / durationInMonths;
        }
        //essentially (principal * rate) / (1 - (1/(1+r)^n))
        return ((principal * rate) / (1 - Math.pow(1 + rate, -durationInMonths)));
   
    }

    /*
        Checks if amountDesignated is enough to cover monthly payment
    */
    public boolean sufficientFunding(){
        return amountDesignated >= monthlyPayment;
            
    }
    /*
        Overrides makePayment() from parent class. Only provides payment plan.
    */
    @Override 
    public double makePayment(double payment){
        throw new UnsupportedOperationException("FinancedExpense provides a payment plan only.");
    }
    /*
        @return principal - inital principal of the expense
    */
    public double getPrincipal(){
        return principal;
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
