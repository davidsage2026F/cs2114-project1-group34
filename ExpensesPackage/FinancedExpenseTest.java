package ExpensesPackage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class FinancedExpenseTest {

    /*
        Tests the constructor
    */
    @Test
    public void testFinancedExpense(){

        FinancedExpense expense = new FinancedExpense("Student loan", true, 50000.00, 10000.00, 0.05, 60, 300.00);
        assertEquals("Student loan", expense.getName());
        assertTrue(expense.getIsNeeded());
        assertEquals(40000.00, expense.getPrincipal(), 0.001);
        assertFalse(expense.getPaidOff());
        assertTrue(expense.getTransfersToNextMonth());

    }
 
    /*
        Tests calcMonthlyPayment()
    */
   @Test 
   public void testCalcMonthlyPayment(){
        FinancedExpense expense = new FinancedExpense("Student loan", true, 50000.00, 10000.00, 0.05, 60, 300.00);          
        double payment = expense.calcMonthlyPayment(expense.getInterestRate(),expense.getDurationInMonths(), expense.getPrincipal());
        assertEquals(754.85, payment, 0.001);
        
   }
   /*
    Tests calcMonthlyPayme() with 0% interest
   */
   @Test
   public void testCalcMonthlyPaymentZero(){
        FinancedExpense expense = new FinancedExpense("Student loan", true, 50000.00, 10000.00, 0.00, 60, 300.00);
        double payment = expense.calcMonthlyPayment(expense.getInterestRate(),expense.getDurationInMonths(), expense.getPrincipal());
        assertEquals(666.6667, payment, 0.001);
   }

}
