package ExpensesPackage;

/*
    Subclass of Expenses designed for users to track one time expenses. 
 */
public class OneTimeExpense extends Expenses{

    //Fields
    private double cost;
    
    //Methods

    /*
        Constructs OneTimeExpense objects that the user pays off in one payment. 
    */
    public OneTimeExpense(String name, boolean isNeeded, String dateDue, double balance){
            
            super(name, false, false, isNeeded, dateDue, balance);
            
            this.cost = balance;
    }
    
    /*
        Enforces that a one time expense is paid in full
    */
    @Override
    public double makePayment(double payment){
        if (payment != getAmountDue()){
            throw new IllegalArgumentException("A one time expense must be paid in full.");
        }
        return super.makePayment(payment);
    }
    
    /*
        @return cost - the original cost of the expense
    */  

    public double getCost() {
        return cost;
    }
    /*
        @return message to the user about their expense and balance remaining.
    */
    @Override 
    public String toString(){
        return "One time expense of " + this.getName() + " with an original cost of $" + this.getCost()
        + " and $" + getAmountDue() + " remaining.";
    }

}
