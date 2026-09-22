package ExpensesPackage;
/*
    Subclass of Expenses to keep track of recurring expenses. 
*/

public class RecurringExpense extends Expenses{
    

    //Fields 
    private double monthlyPayment;

    //Constructor 
    public RecurringExpense(String name, boolean isNeeded, String dateDue, double monthlyPayment){
        
        super(name, false, true, isNeeded, dateDue, monthlyPayment);

        this.monthlyPayment = monthlyPayment;

    }
    //Methods
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
