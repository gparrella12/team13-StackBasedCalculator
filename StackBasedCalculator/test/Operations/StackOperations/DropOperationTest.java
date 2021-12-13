package Operations.StackOperations;

import Stack.ObservableStack;
import org.apache.commons.math3.complex.Complex;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Test class to check the correct execution of the "Drop" operation.
 * 
 * @author Speranza
 */
public class DropOperationTest {

    private ObservableStack<Complex> stack;

    /**
     * Constructor.
     */
    public DropOperationTest() {
    }

    @Before
    public void setUp() {
        stack = new ObservableStack<>();
    }

    /**
     * Test of execute method, of class DropOperation.
     */
    @Test
    public void testExecute() {

        System.out.println("Drop execute");

        Complex c1 = new Complex(1, 5);
        Complex c2 = new Complex(2, 7);
        
        DropOperation instance = new DropOperation(stack);

        stack.push(c1);
        stack.push(c2);
        
        instance.execute();
        
        assertEquals("Error in drop call", c1, stack.top());

        assertEquals("Error in drop call - Invalid size", 1, stack.size());
 

    }
}
