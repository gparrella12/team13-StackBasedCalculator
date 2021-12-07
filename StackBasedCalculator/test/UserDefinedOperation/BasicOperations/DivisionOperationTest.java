package UserDefinedOperation.BasicOperations;

import Stack.ObservableStack;
import org.apache.commons.math3.complex.Complex;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fsonnessa
 */
public class DivisionOperationTest {
    
    private ObservableStack<Complex> stack;
    
    public DivisionOperationTest() {
    }
    
    @Before
    public void setUp() {
        stack = new ObservableStack<>();
    }

    /**
     * Test of execute method, of class DivisionOperation.
     */
    @Test
    public void testExecute() {
        System.out.println("Division execute");
        
        DivisionOperation instance = new DivisionOperation(stack);
        
        stack.push(new Complex(5, 1));
        stack.push(new Complex(5, 1));
        
        instance.execute();
        Complex result = new Complex(1,0);
        assertEquals("Wrong result : 5+1j / 5+1j ", stack.top(), result);
        
        System.out.println("division - operands order");
        stack.clear();
        
        stack.push(new Complex(5, 1));
        stack.push(new Complex(5, 0));
        
        instance.execute();
        result = new Complex(1, 0.2);
        assertEquals("Wrong result : 5+1j / 5 ", stack.top(), result);
    }
    
}
