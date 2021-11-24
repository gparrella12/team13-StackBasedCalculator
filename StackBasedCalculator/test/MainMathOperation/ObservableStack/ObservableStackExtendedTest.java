package MainMathOperation.ObservableStack;

import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class test the features of extended observable stack.
 *
 * @author gparrella
 */
public class ObservableStackExtendedTest {

    private ObservableStackExtended<Integer> stack;

    public ObservableStackExtendedTest() {
    }

    @Before
    public void setUp() {
        stack = new ObservableStackExtended<>();
    }

    /**
     * Test of clear method, of class ObservableStackExtended.
     */
    @Test(timeout = 15)
    public void testClear() {
        System.out.println("clear");
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        stack.clear();
        assertEquals("The stack isn't cleared after a clear operation", true, stack.isEmpty());
    }

    /**
     * Test of drop method, of class ObservableStackExtended.
     */
    @Test
    public void testDrop() {
        System.out.println("drop");
        stack.drop();
        stack.push(1);
        stack.drop();
        assertEquals("The stack isn't empty after a drop", true, stack.isEmpty());
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        stack.drop();
        assertEquals("Incorrect size:", 9, stack.size());
        int top = stack.top();
        assertEquals("Incorrect element:", 8, top);
    }

    /**
     * Test of dup method, of class ObservableStackExtended.
     */
    @Test
    public void testDup() {
        int dim, top;
        stack.dup();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
            dim = stack.size();
            stack.dup();
            top = stack.top();
            assertEquals("Size error:", dim + 1, stack.size());
            assertEquals("Element error:", i, top);
        }
    }

    /**
     * Test of swap method, of class ObservableStackExtended.
     */
    @Test(expected = NoSuchElementException.class)
    public void testSwap() {
        System.out.println("swap");
        stack.swap();
        stack.push(500);
        stack.push(1);
        stack.push(2);
        stack.swap();
        int top = stack.top();
        assertEquals("Invalid element", 1, top);
        assertEquals("Incorrect size", 3, stack.size());
        stack.pop();
        top = stack.pop();
        assertEquals("Invalid element after a swap operation", 2, top);
        stack.swap();
    }

    /**
     * Test of over method, of class ObservableStackExtended.
     */
    @Test
    public void testOver() {
        System.out.println("copy");
        stack.push(1);
        stack.push(2);
        stack.over();
        assertEquals("Invalid size",3, stack.size());
        int top = stack.pop();
        assertEquals("Invalid element copied", top, 1);
    }

}
