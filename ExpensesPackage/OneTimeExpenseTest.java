package ExpensesPackage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/*
    Tests methods in OneTimeExpense
 */
public class OneTimeExpenseTest {



    /*
        Tests makePayment() when expense is paid in full
    */
    @Test 
    public void oneTimeFullPaymentTest(){
        OneTimeExpense expense = new OneTimeExpense("TI-84 Calculator", true, 
        "9/23/26", 200.00);

        expense.makePayment(200.00);
        assertEquals(expense.getAmountDue(), 0, 0.001);
        assertTrue(expense.getPaidOff());
    }
    
    /*
        Tests makePayment() when the payment does not match the oustanding balance
    */
    @Test
    public void oneTimeRejectsPartialPayment() {
        OneTimeExpense expense = new OneTimeExpense("TI-84 Calculator", true, "9/23/26", 200.00);
        IllegalArgumentException e = null;
        try {
            expense.makePayment(199.99);
        }
        catch (IllegalArgumentException exception){
            e = exception;
        }
        assertNotNull(e);
        assertEquals(200.00, expense.getAmountDue(), 0.001);
        assertFalse(expense.getPaidOff());
    
    }
}
