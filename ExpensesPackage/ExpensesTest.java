package ExpensesPackage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExpensesTest {

    @Test 
    public void fullPaymentTest(){
        Expenses expense = new Expenses("TI-84 Calculator", false, 
            false, true, "9/23/26", 200.00);

        expense.makePayment(200);
        assertEquals(expense.getAmountDue(), 0, 0.001);
        assertTrue(expense.getPaidOff());
    }
    
}
