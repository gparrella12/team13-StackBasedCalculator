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
public class SumOperationTest {
    
    private ObservableStack<Complex> stack;
    private SumOperation instance;
    
    public SumOperationTest() {
    }
    
    @Before
    public void setUp() {
        stack = new ObservableStack<>();
        instance = new SumOperation(stack);
    }

    @Test(expected = NoSuchElementException.class)
    public void testSumExcetpion() {
        System.out.println("\nTest sum without numbers");
        stack.clear();
        instance.execute();
    }

    @Test
    public void testSum() {
        System.out.println("\nsum");
        stack.clear();
        stack.push(new Complex(5, 1));
        stack.push(new Complex(5, 0));
        instance.execute();

        Complex result = new Complex(10, 1);
        assertEquals("Wrong result : 5+1j + 5 ", stack.top(), result);
    }
    
}
