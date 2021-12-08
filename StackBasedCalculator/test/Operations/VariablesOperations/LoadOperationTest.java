package Operations.VariablesOperations;

import Stack.ObservableStack;
import VariablesManager.VariablesStorage;
import org.apache.commons.math3.complex.Complex;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Speranza
 */
public class LoadOperationTest {

    private ObservableStack<Complex> stack;
    private VariablesStorage vs;
    private LoadOperation instance;

    public LoadOperationTest() {
    }

    @Before
    public void setUp() {
        stack = new ObservableStack<>();
        vs = new VariablesStorage();
    }

    /**
     * Test of execute method, of class LoadOperation.
     */
    @Test
    public void testExecute() {
        Complex c = new Complex(3.14, 3.14);

        vs.save("a", c);
        instance = new LoadOperation(stack, vs, "a");

        instance.execute();

        assertEquals("Error in load method call", new Complex(3.14, 3.14), stack.top());
        assertEquals("Error in load method call - Invalid size", 1, stack.size());

    }
}
