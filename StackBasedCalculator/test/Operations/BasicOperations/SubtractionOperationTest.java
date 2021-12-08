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
public class SubtractionOperationTest {
    
    private ObservableStack<Complex> stack;
    private SubtractionOperation instance;
    
    public SubtractionOperationTest() {
    }
    
    @Before
    public void setUp() {
        stack = new ObservableStack<>();
        instance = new SubtractionOperation(stack);
    }

    @Test(expected = NoSuchElementException.class)
    public void testSubtractionExcetpion() {
        System.out.println("\nTest subtraction without numbers");
        stack.clear();
        instance.execute();
    }

    /**
     * Test of subtraction method, of class RPNSolver.
     */
    @Test
    public void testSubtraction() {
        System.out.println("\nsubtraction - simple");

        stack.clear();
        stack.push(new Complex(5, 1));
        stack.push(new Complex(5, 0));
        instance.execute();
        Complex result = new Complex(0, 1);
        assertEquals("Wrong result : 5+1j - 5 ", stack.top(), result);

        System.out.println("subtraction - operands order");
        stack.clear();
        stack.push(new Complex(5, 0));
        stack.push(new Complex(9, -1));

        instance.execute();
        result = new Complex(-4, 1);
        assertEquals("Wrong result : 5 - 9-1j ", stack.top(), result);
    }
}
