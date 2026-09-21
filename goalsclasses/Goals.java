package goalsclasses;
/*
    The Goals class acts as the parent class of all goals and investments,
    it holds information about the cost of the goal and if the goal is met.
    Child classes follow specific goals and contain more information while
    the parent holds most the information for the user class to know and get.

*/
public class Goals {
    // Fields
    private boolean transfersToNextMonth;
    private boolean goalMet = false;
    private String name;
    private double cost;

    // Constructors
    /*
        Constructs goals without specifying if it transfers month to month,
        assumes not if not specified and is overloaded.
    */
    public Goals(String name, double cost) {
        this(name,cost,false);
    }

    public Goals(String name, double cost, boolean transfers) {
        this.name = name;
        this.cost = cost;
        transfersToNextMonth = transfers;
    }

    // Methods
    /*
        @return String 
        - name of the goal
    */
    public String getName() {
        return name;
    }
    /*
        @return transfersToNextMonth
        - boolean on if the goal is carried to the following month
    */
    public boolean getTransfersToNextMonth() {
        return transfersToNextMonth;
    }
    /*
        @return boolean
        - boolean on if the goal has been completed
    */
    public boolean goalMet() {
        return goalMet;
    }
    /*
        Called by children to declare goal has been met
    */
    protected void metGoal() {
        goalMet = true;
    }
    /*
        @return double 
        - gives the cost of the goal
    */
    public double getCost() {
        return cost;
    }
    /*
        sets cost of the goal
        @param cost
        - cost of goal
    */
    public void setCost(double cost){
        this.cost = cost;
    }

}
