package ExpensesPackage;
/*
    Subclass of Expenses
*/

public class RecurringExpense extends Expenses{
    

    //Fields 
    private int durationInMonths;
    private double monthlyPayment;

    //Constructor 
    public RecurringExpense(String name, boolean paidOff, boolean transfersToNextMonth, 
        boolean isNeeded, String dateDue, double balance, int durationInMonths, double monthlyPayment){
        
        super(name, paidOff, transfersToNextMonth, isNeeded, dateDue, balance);

        this.durationInMonths = durationInMonths;
        this.monthlyPayment = monthlyPayment;

    }
    //Methods

    /*
        @return durationInMonths - how long the 
        recurring expense lasts for
    */
    public int getDurationInMonths(){
        return durationInMonths;
    }
    /*
        @return monthlyPayment - the monthly payment
        required for a recurring expense
    */
    public double getMonthlyPayment(){
        return monthlyPayment;
    }
    /*
        @return message to the user about their expense and monthly payment.
    */
    @Override 
    public String toString(){
        return "Recurring Expense of " + this.getName() + " that requires $" + this.getMonthlyPayment()
         + " a month.";
    }

}
