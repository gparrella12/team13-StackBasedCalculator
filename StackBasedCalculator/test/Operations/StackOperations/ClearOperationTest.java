package Operations.StackOperations;

import Stack.ObservableStack;
import org.apache.commons.math3.complex.Complex;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Test class to check the correct execution of the "Clear" operation.
 * 
 * @author Speranza
 */
public class ClearOperationTest {

    private ObservableStack<Complex> stack;

    /**
     * Constructor.
     */
    public ClearOperationTest() {
    }

    @Before
    public void setUp() {
        stack = new ObservableStack<>();
    }

    /**
     * Test of execute method, of class ClearOperation.
     */
    @Test
    public void testExecute() {

        System.out.println("Clear execute");

        ClearOperation instance = new ClearOperation(stack);

        stack.push(new Complex(1, 5));
        stack.push(new Complex(0.256, -70));
        stack.push(new Complex(-3, 3.33));
        
        instance.execute();
        
        assertEquals("Error in Clear call", 0, stack.size());
        
    }
}
