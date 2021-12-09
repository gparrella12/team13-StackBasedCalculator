package Operations.StackOperations;

import Stack.ObservableStack;
import org.apache.commons.math3.complex.Complex;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Test class to check the correct execution of the "Push" operation.
 * 
 * @author Speranza
 */
public class PushOperationTest {

    private ObservableStack<Complex> stack;

    /**
     * Constructor.
     */
    public PushOperationTest() {
    }

    @Before
    public void setUp() {
        stack = new ObservableStack<>();
    }

    /**
     * Test of execute method, of class PushOperation.
     */
    @Test
    public void testExecute() {

        System.out.println("Push execute");

        Complex num = new Complex(5, 1);
        PushOperation instance = new PushOperation(stack, num);

        instance.execute();
        
        assertEquals("Error in push call - Invalid size", 1, stack.size());
        assertEquals("Error in push call", num, stack.top());
        stack.clear();

        // Test push method with invalid element
        instance = new PushOperation(stack, Complex.NaN);
        try {
            instance.execute();
        } catch (NumberFormatException ex) {
            System.out.println("\t Push with incorrect operand -> OK");
            return;
        }
    }
}
