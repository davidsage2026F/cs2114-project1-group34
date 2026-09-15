package ExpensesPackage;
public class RecurringExpense extends Expenses{
    private int durationInMonths;
    private double monthlyPayment;

    public RecurringExpense(String name, boolean paidOff, boolean transfersToNextMonth, 
        boolean isNeeded, int dateDue, double amount, double amountDesignated, 
        int durationInMonths, 
        double monthlyPayment){
        
            super(name, paidOff, transfersToNextMonth, isNeeded, 
        durationInMonths, amountDesignated);
    }

    public int getDurationInMonths(){
        return durationInMonths;
    }
    public double getMonthlyPayment(){
        return monthlyPayment;
    }

}
