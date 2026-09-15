package goalsclasses;
/*
docstring class

*/
public class Goals {
    // Fields
    private boolean transfersToNextMonth;
    private boolean goalMet;
    private String name;
    private double cost;

    // Constructor
    public Goals(String name, double cost) {
        this(name,cost,false);
    }

    public Goals(String name, double cost, boolean transfers) {
        this.name = name;
        this.cost = cost;
        transfersToNextMonth = transfers;
    }

    // Methods
    public String getName() {
        return name;
    }

    public boolean getTransfersToNextMonth() {
        return transfersToNextMonth;
    }
    
    public boolean goalMet() {
        return goalMet;
    }

    public double getCost() {
        return cost;
    }
    

}
