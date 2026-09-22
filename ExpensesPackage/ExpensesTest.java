package ExpensesPackage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExpensesTest {

    /*
        Tests makePayment() when expense is paid in full
    */
    @Test 
    public void fullPaymentTest(){
        Expenses expense = new Expenses("TI-84 Calculator", false, 
            false, true, "9/23/26", 200.00);

        expense.makePayment(200);
        assertEquals(expense.getAmountDue(), 0, 0.001);
        assertTrue(expense.getPaidOff());
    }
    
    /*
        Tests Expenses constructor
    */
    @Test 
    public void testConstruct(){
        Expenses expense = new Expenses("Car payment", false, true, 
        true, "9/30/26", 500.00);

        assertNotNull(expense);
        assertEquals("Car payment", expense.getName());
        assertFalse(expense.getPaidOff());
        assertTrue(expense.getTransfersToNextMonth());
        assertTrue(expense.getIsNeeded());
        assertEquals("9/30/26", expense.getDateDue());
        assertEquals(500.00, expense.getAmountDue(), 0.001);


    }
}
