package ExpensesPackage;
public class OneTimeExpense extends Expenses{
    
    private double cost;
    
    public OneTimeExpense(String name, boolean paidOff, boolean transfersToNextMonth, 
        boolean isNeeded, String dateDue, double cost){
            super(name, paidOff, transfersToNextMonth, isNeeded, amountDesignated);
                
            }
        }
        

    }
    
    public double getCost(){
        return cost;
    }

}
