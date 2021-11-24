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
        //Insert 10 elements into the stack
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        //Clear all elements and check the stack's size
        stack.clear();
        assertEquals("The stack isn't cleared after a clear operation", true, stack.isEmpty());
    }

    /**
     * Test of drop method, of class ObservableStackExtended.
     */
    @Test
    public void testDrop() {
        System.out.println("drop");
        //Drop on a empty stack, the stack should not be modified
        stack.drop();
        stack.push(1);
        stack.drop();
        assertEquals("The stack isn't empty after a drop", true, stack.isEmpty());
        //Insert 10 elements
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        // Drop 1 elements and check stack's size
        stack.drop();
        assertEquals("Incorrect size:", 9, stack.size());
        // Check if the drop operation has removed the correct element
        int top = stack.top();
        assertEquals("Incorrect element:", 8, top);
    }

    /**
     * Test of dup method, of class ObservableStackExtended.
     */
    @Test
    public void testDup() {
        int dim, top;
        //dup on an emtpy stack should not change it
        stack.dup();
        //Insert 10 elements and then duplicate the last element inserted, checking the element
        // duplicated and the new stack size
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
    @Test(expected=NoSuchElementException.class)
    public void testSwap() {
        System.out.println("swap");
        // Swap on an empty stack, the stack should not be modified
        stack.swap();
        // Insert some elements
        stack.push(500);
        stack.push(1);
        stack.push(2);
        // Swap operation
        stack.swap();
        // Check elements and the new size
        int top = stack.top();
        assertEquals("Invalid element", 1, top);
        assertEquals("Incorrect size", 3, stack.size());
        // Check if swap operate correctly
        stack.pop();
        top = stack.pop();
        assertEquals("Invalid element after a swap operation", 2, top);
        //swap on a stack of 1 elements, an exception should be thrown
        stack.swap();
    }

    /**
     * Test of over method, of class ObservableStackExtended.
     */
    @Test(expected = NoSuchElementException.class)
    public void testOver() {
        System.out.println("copy");
        // over on an empty stack, the stack should not be modified
        stack.over();
        // Insert 2 elements into the stack
        stack.push(1);
        stack.push(2);
        // Over operation
        stack.over();
        // Check stack size
        assertEquals("Invalid size",3, stack.size());
        int top = stack.pop();
        // Check if the correct element is copied
        assertEquals("Invalid element copied", top, 1);
        // Clear stack
        stack.clear();
        stack.push(2);
        // over on a stack of 1 element, an exception should be thrown
        stack.over();
        
    }

}
