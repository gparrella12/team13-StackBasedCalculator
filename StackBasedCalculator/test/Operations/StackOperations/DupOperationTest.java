package Operations.StackOperations;

import Stack.ObservableStack;
import org.apache.commons.math3.complex.Complex;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Test class to check the correct execution of the "Dup" operation.
 * 
 * @author Speranza
 */
public class DupOperationTest {

    private ObservableStack<Complex> stack;

    /**
     * Constructor.
     */
    public DupOperationTest() {
    }

    @Before
    public void setUp() {
        stack = new ObservableStack<>();
    }

    /**
     * Test of execute method, of class DupOperation.
     */
    @Test
    public void testExecute() {

        System.out.println("Dup execute");

        Complex c1 = new Complex(3, -3);
        DupOperation instance = new DupOperation(stack);

        stack.push(c1);
        instance.execute();
        assertEquals("Error in dup call", c1, stack.top());

    }
}
