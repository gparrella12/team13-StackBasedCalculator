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
public class ProductOperationTest {
    
    private ObservableStack<Complex> stack;
    private ProductOperation instance;
    
    public ProductOperationTest() {
    }
    
    @Before
    public void setUp() {
        stack = new ObservableStack<>();
        instance = new ProductOperation(stack);
    }

    @Test(expected = NoSuchElementException.class)
    public void testProductExcetpion() {
        System.out.println("\nTest product without numbers");
        stack.clear();
        instance.execute();
    }

    /**
     * Test of product method, of class RPNSolver.
     */
    @Test
    public void testProduct() {
        System.out.println("\nproduct");

        stack.clear();
        stack.push(new Complex(2, 1));
        stack.push(new Complex(1, -3));

        instance.execute();

        Complex result = new Complex(5, -5);
        assertEquals("Wrong result : 2+1j * 1-3j = 5-5j" + result, stack.top(), result);
    }
    
}
