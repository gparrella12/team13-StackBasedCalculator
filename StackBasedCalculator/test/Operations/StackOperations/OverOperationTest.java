package Operations.StackOperations;

import Stack.ObservableStack;
import org.apache.commons.math3.complex.Complex;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Test class to check the correct execution of the "Over" operation.
 * 
 * @author Speranza
 */
public class OverOperationTest {

    private ObservableStack<Complex> stack;

    /**
     * Constructor.
     */
    public OverOperationTest() {
    }

    @Before
    public void setUp() {
        stack = new ObservableStack<>();
    }

    /**
     * Test of execute method, of class OverOperation.
     */
    @Test
    public void testExecute() {

        System.out.println("Over execute");

        Complex c1 = new Complex(3, -3);
        Complex c2 = new Complex(4, 1);

        OverOperation instance = new OverOperation(stack);
        
        stack.push(c1);
        stack.push(c2);
        
        instance.execute();
        
        assertEquals("Error in over call", c1, stack.top());

        assertEquals("Error in over call - Invalid size", 3, stack.size());

    }
}
