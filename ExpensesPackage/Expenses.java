package ExpensesPackage;
public class Expenses {
    Boolean transfersToNextMonth = false;
    Boolean isNeeded= false;
    int dateDue = 0;
    String name = "";
    Boolean paidOff = false;
    double amount = 0;
    
    public  Expenses(String name, boolean paidOff, boolean transfersToNextMonth, 
        boolean isNeeded, 
        int dateDue, 
        double amount){
            this.name = name;
            this.paidOff = paidOff;
            this.transfersToNextMonth = transfersToNextMonth;
            this.isNeeded = isNeeded;
            this.dateDue = dateDue;
            this.amount = amount;
            Expenses expense = new Expenses(name, paidOff, 
            transfersToNextMonth, isNeeded, dateDue, amount);

    }


    /**
     * Returns the name of the expense
     * @return name of expense
     */
    public String getName(){
        return name;
    }
    public boolean getPaidOff(){
        return paidOff;
    }
    public boolean getTransfersToNextMonth(){
        return transfersToNextMonth;
    }
    public boolean getIsNeeded(){
        return isNeeded;
    }
    public int getDateDue(){
        return dateDue;

    }
    public double getAmountDue(){
        return amount;

    }

    //@param amount the amount made towards a payment
    public void makePayment(double amount){
        
}
}

