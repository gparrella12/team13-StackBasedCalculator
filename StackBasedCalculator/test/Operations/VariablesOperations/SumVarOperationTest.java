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
public class SumVarOperationTest {

    private ObservableStack<Complex> stack;
    VariablesStorage variableStorage;

    public SumVarOperationTest() {
    }

    @Before
    public void setUp() {
        stack = new ObservableStack<>();
        variableStorage = new VariablesStorage();
    }

    /**
     * Test of execute method, of class SumVarOperation.
     */
    @Test
    public void testExecute() {
        
        Complex c = new Complex(1, 1);
        variableStorage.save("x", c);
        stack.push(c);

        SumVarOperation instance = new SumVarOperation(stack, variableStorage, "x");
        
        instance.execute();

        assertEquals("Error in add method call", variableStorage.getVariableValue("x"), new Complex(2, 2));

        assertEquals("Error in add method call - Invalid size", 1, stack.size());

    }
}
