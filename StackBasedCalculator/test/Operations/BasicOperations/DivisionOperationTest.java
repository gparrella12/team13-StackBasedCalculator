package Operations.BasicOperations;

import Stack.ObservableStack;
import java.util.NoSuchElementException;
import org.apache.commons.math3.complex.Complex;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fsonnessa
 */
public class DivisionOperationTest {
    
    private ObservableStack<Complex> stack;
    private DivisionOperation instance;
    
    public DivisionOperationTest() {
    }
    
    @Before
    public void setUp() {
        stack = new ObservableStack<>();
        instance = new DivisionOperation(stack);
    }

    /**
     * Test of execute method, of class DivisionOperation.
     */
    @Test
    public void testDivision() {
        System.out.println("Division execute");
        
        stack.push(new Complex(5, 1));
        stack.push(new Complex(5, 1));
        
        instance.execute();
        Complex result = new Complex(1,0);
        assertEquals("Wrong result : 5+1j / 5+1j ", stack.top(), result);
        
        System.out.println("division - operands order");
        stack.clear();
        
        stack.push(new Complex(5, 1));
        stack.push(new Complex(5, 0));
        
        instance.execute();
        result = new Complex(1, 0.2);
        assertEquals("Wrong result : 5+1j / 5 ", stack.top(), result);
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testDivisionExcetpion() {
        System.out.println("\nTest division without numbers");
        stack.clear();
        instance.execute();
    }
    
    @Test(expected = ArithmeticException.class)
    public void testDivisionByZero() {
        System.out.println("division by 0");
        stack.clear();
        stack.push(new Complex(5, 1));
        stack.push(new Complex(0));
        instance.execute();
        Complex result = new Complex(Double.NaN, Double.NaN);
        assertEquals("Wrong result : 5+1j / 0 ", stack.top(), result);
    }
    
}
