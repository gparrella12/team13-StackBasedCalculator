package MainMathOperation;

import MainMathOperation.Stack;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class test the Stack's features.
 *
 * @author gparrella
 */
public class StackTest {

    private Stack<Integer> stack;

    public StackTest() {
    }

    @Before
    public void setUp() {
        stack = new Stack<>();
    }

    /**
     * Test of isEmpty method, of class Stack.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        assertEquals("The stack isn't empty after creation", true, stack.isEmpty());
        stack.push(1);
        assertEquals("The stack is empty after a push", false, stack.isEmpty());
        stack.top();
        assertEquals("The stack is empty after a pop", false, stack.isEmpty());
        stack.pop();
        assertEquals("The stack isn't empty after pop", true, stack.isEmpty());
    }

    /**
     * Test of size method, of class Stack.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        assertEquals("The stack isn't empty after creation", 0, stack.size());
        for (int i = 0; i < 5; i++) {
            stack.push(i + 1);
        }
        assertEquals("Invalid number of elements", 5, stack.size());
        stack.top();
        stack.pop();
        assertEquals("Invalid number of elements", 4, stack.size());
        stack.pop();
        assertEquals("Invalid number of elements", 3, stack.size());
        stack.push(45);
        assertEquals("Invalid number of elements", 4, stack.size());
    }

    /**
     * Test of push method, of class Stack.
     */
    @Test
    public void testPush() {
        System.out.println("push");
        for (int i = 0; i < 10; i++) {
            stack.push(i);
            int top = stack.top();
            assertEquals("Invalid element inserted", i, top);
        }
    }

    /**
     * Test of pop method, of class Stack.
     */
    @Test(expected = NoSuchElementException.class)
    public void testPop() {
        System.out.println("pop");
        for (int i = 0; i < 10; i++) {
            stack.push(i);
            int top = stack.pop();
            assertEquals("Invalid element inserted", i, top);
        }
        // An exception must be thrown
        stack.pop();
    }

    /**
     * Test of top method, of class Stack.
     */
    @Test(expected = NoSuchElementException.class)
    public void testTop() {
        System.out.println("top");
        for (int i = 0; i < 10; i++) {
            stack.push(i);
            int top = stack.top();
            assertEquals("Invalid element inserted", i, top);
            stack.pop();
        }
        // An exception must be thrown
        stack.top();
    }

    /**
     * Test of clear method, of class Stack.
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
     * Test of drop method, of class Stack.
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
     * Test of dup method, of class Stack.
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
     * Test of swap method, of class Stack.
     */
    @Test(expected = NoSuchElementException.class)
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
     * Test of over method, of class Stack.
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
        assertEquals("Invalid size", 3, stack.size());
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
