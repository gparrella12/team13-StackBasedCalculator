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
public class InvertSignOperationTest {
    
    private ObservableStack<Complex> stack;
    private InvertSignOperation instance;
    
    public InvertSignOperationTest() {
    }
    
    @Before
    public void setUp() {
        stack = new ObservableStack<>();
        instance = new InvertSignOperation(stack);
    }

    /**
     * Test of execute method, of class InvertSignOperation.
     */
    @Test
    public void testInvertSign() {
        System.out.println("\ninvertSign");

        stack.clear();
        stack.push(new Complex(-4, 3.5));

        instance.execute();
        Complex result = new Complex(4, -3.5);
        assertEquals("Wrong result : +-(-4+.35j) ", stack.top(), result);
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testInvertSignExcetpion() {
        System.out.println("\nTest invertSign without number");
        stack.clear();
        instance.execute();
    }
    
}
