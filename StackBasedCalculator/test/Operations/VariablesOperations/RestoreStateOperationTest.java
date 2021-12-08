package Operations.VariablesOperations;

import Stack.ObservableStack;
import org.apache.commons.math3.complex.Complex;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fsonnessa
 */
public class RestoreStateOperationTest {
    
    private ObservableStack<Complex> stack;
    
    public RestoreStateOperationTest() {
    }
    
    @Before
    public void setUp() {
        stack = new ObservableStack<>();
    }

    /**
     * Test of execute method, of class RestoreStateOperation.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        RestoreStateOperation instance = null;
        instance.execute();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class RestoreStateOperation.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        RestoreStateOperation instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
