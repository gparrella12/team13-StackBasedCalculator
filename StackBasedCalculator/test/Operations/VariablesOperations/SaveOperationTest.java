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
public class SaveOperationTest {

    private ObservableStack<Complex> stack;
    private VariablesStorage vs;
    private SaveOperation instance;

    public SaveOperationTest() {
    }

    @Before
    public void setUp() {
        stack = new ObservableStack<>();
        vs = new VariablesStorage();
    }

    /**
     * Test of execute method, of class SaveOperation.
     */
    @Test
    public void testExecute() {
        instance = new SaveOperation(stack, vs, "a");
        Complex c = new Complex(3.14, 3.14);
        stack.push(c);
        instance.execute();

        assertEquals("Error in save method call", c, vs.getVariableValue("a"));
        assertEquals("Error in save method call - Invalid size", 0, stack.size());

    }
}
