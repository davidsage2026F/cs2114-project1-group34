package ExpensesPackage;

/*
    The Expenses class acts as the parent class containing the 
    fields and methods provided for all expenses subclasses. 

*/
public class Expenses {
    private Boolean transfersToNextMonth;
    private Boolean isNeeded;
    private String dateDue;
    private String name;
    private Boolean paidOff;
    private double balance;
    
    /*
        Constructs expenses and instantiates corresponding fields.

    */
    public  Expenses(String name, boolean paidOff, boolean transfersToNextMonth, 
        boolean isNeeded, String dateDue, double balance){
            this.name = name;
            this.paidOff = paidOff;
            this.transfersToNextMonth = transfersToNextMonth;
            this.isNeeded = isNeeded;
            this.dateDue = dateDue;
            this.balance = balance;

    }
    /*
        Alternative constuctor for financedExpense with no dateDue.
    */
        public Expenses(String name, boolean paidOff, boolean transfersToNextMonth, boolean isNeeded, double balance) {
        this(name, paidOff, transfersToNextMonth, isNeeded, "", balance);
        }

    //Methods 

    /*
        @return name of expense - Returns the name of 
        the expense
    */
    public String getName(){
        return name;
    }
    /*
        @return paidOff - boolean value that indicates if
        an expense has been paid off
    */
    public boolean getPaidOff(){
        return paidOff;
    }
    /*
        @return transfersToNextMonth - Determines if an expense 
        transfers to the next month
    */
    public boolean getTransfersToNextMonth(){
        return transfersToNextMonth;
    }
    /*
        @return isNeeded - User ranks an expense as either nescessary or
        optional 
    */
    public boolean getIsNeeded(){
        return isNeeded;
    }
    /*
        @return dateDue - The date an expense payment is due
    */
    public String getDateDue(){
        return dateDue;

    }
    /*
        @return amount - Balance remaining on an expense 
    */
    public double getAmountDue(){
        return balance;

    }

    //@param payment the payment made towards an expense
    //@return amount the balance left on an expense after a payment
    public double makePayment(double payment){
        if (!Double.isFinite(payment)){
            throw new IllegalArgumentException("Payment must be finite.");
        }
        if (payment <= 0){
            throw new IllegalArgumentException("Payment must be a positive amount.");
        }
        if (payment > balance){
            throw new IllegalArgumentException("Payment cannot exceed the amount due on an outstanding expense.");
        }

        balance -= payment;
        if (balance == 0){
            paidOff = true;
        }
        return balance;

    }

}

