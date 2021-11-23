package MainMathOperation;

import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class test the ObservableStack's features.
 *
 * @author gparrella
 */
public class ObservableStackTest {

    private ObservableStack<Integer> stack;

    public ObservableStackTest() {
    }

    @Before
    public void setUp() {
        stack = new ObservableStack<>();
    }

    /**
     * Test of isEmpty method, of class ObservableStack.
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
     * Test of size method, of class ObservableStack.
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
     * Test of push method, of class ObservableStack.
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
     * Test of pop method, of class ObservableStack.
     */
    @Test(expected = NoSuchElementException.class)
    public void testPop() {
        System.out.println("pop");
        for (int i = 0; i < 10; i++) {
            stack.push(i);
            int top = stack.pop();
            assertEquals("Invalid element inserted", i, top);
        }
        stack.pop();
    }

    /**
     * Test of top method, of class ObservableStack.
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
        stack.pop();
    }

}
