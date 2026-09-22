package ExpensesPackage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RecurringExpenseTest {

    /*
        Test constructor
    */
    @Test
    public void testRecurringExpense(){

        RecurringExpense expense = new RecurringExpense("Netflix", false, "9/30/26", 20.00);
        assertEquals("Netflix", expense.getName());
        assertFalse(expense.getIsNeeded());
        assertEquals("9/30/26", expense.getDateDue());
        assertEquals(20.00, expense.getMonthlyPayment(), 0.001);
        assertFalse(expense.getPaidOff());
        assertTrue(expense.getTransfersToNextMonth());

    }

    


}
