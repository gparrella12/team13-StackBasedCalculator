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
public class SubVarOperationTest {

    private ObservableStack<Complex> stack;
    private VariablesStorage variableStorage;
    
    public SubVarOperationTest() {
    }

    @Before
    public void setUp() {
        stack = new ObservableStack<>();
        variableStorage = new VariablesStorage();
    }

    /**
     * Test of execute method, of class SubVarOperation.
     */
    @Test
    public void testExecute() {

        Complex c = new Complex(1, 1);
        variableStorage.save("x", c);
        stack.push(c);
        
        SubVarOperation instance = new SubVarOperation(stack, variableStorage, "x");
        
        instance.execute();
        
        assertEquals("Error in subtract method call", variableStorage.getVariableValue("x"), new Complex(0, 0));
        assertEquals("Error in subtract method call - Invalid size", 1, stack.size());
    }
}
