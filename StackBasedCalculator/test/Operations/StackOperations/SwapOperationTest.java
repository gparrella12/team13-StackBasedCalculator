package Operations.StackOperations;

import Stack.ObservableStack;
import org.apache.commons.math3.complex.Complex;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Test class to check the correct execution of the "Swap" operation.
 * 
 * @author Speranza
 */
public class SwapOperationTest {

    private ObservableStack<Complex> stack;

    /**
     * Constructor.
     */
    public SwapOperationTest() {
    }

    @Before
    public void setUp() {
        stack = new ObservableStack<>();
    }

    /**
     * Test of execute method, of class SwapOperation.
     */
    @Test
    public void testExecute() {

        System.out.println("Swap execute");

        Complex c1 = new Complex(1, 5);
        Complex c2 = new Complex(0.256, -69);
        SwapOperation instance = new SwapOperation(stack);

        stack.push(c1);
        stack.push(c2);
        
        instance.execute();
        assertEquals("Error in swap call", c1, stack.top());
        
        instance.execute();
        assertEquals("Error in swap call - Invalid size", 2, stack.size());

    }
}
