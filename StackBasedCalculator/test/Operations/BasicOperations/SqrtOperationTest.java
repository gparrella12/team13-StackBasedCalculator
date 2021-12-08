package Operations.BasicOperations;

import Stack.ObservableStack;
import java.util.NoSuchElementException;
import org.apache.commons.math3.complex.Complex;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fsonnessa
 */
public class SqrtOperationTest {
    
    private ObservableStack<Complex> stack;
    private SqrtOperation instance;
    
    public SqrtOperationTest() {
    }
    
    @Before
    public void setUp() {
        stack = new ObservableStack<>();
        instance = new SqrtOperation(stack);
    }

    @Test(expected = NoSuchElementException.class)
    public void testSqrtExcetpio() {
        System.out.println("\nTest sqrt without number");
        stack.clear();
        instance.execute();;
    }

    /**
     * Test of sqrt method, of class RPNSolver.
     */
    @Test
    public void testSqrt() {
        System.out.println("\nsqrt");

        stack.clear();
        stack.push(new Complex(-4, 0));
        instance.execute();;
        Complex result = new Complex(0, 2);
        assertEquals("Wrong result : sqrt(-4) ", stack.top(), result);
    }
    
}
