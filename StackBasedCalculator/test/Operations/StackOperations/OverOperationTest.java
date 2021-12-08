/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Operations.StackOperations;

import Stack.ObservableStack;
import java.util.NoSuchElementException;
import org.apache.commons.math3.complex.Complex;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Speranza
 */
public class OverOperationTest {

    private ObservableStack<Complex> stack;

    public OverOperationTest() {
    }

    @Before
    public void setUp() {
        stack = new ObservableStack<>();
    }

    /**
     * Test of execute method, of class OverOperation.
     */
    @Test
    public void testExecute() {

        System.out.println("Over execute");

        Complex c1 = new Complex(3, -3);
        Complex c2 = new Complex(4, 1);

        OverOperation instance = new OverOperation(stack);
        
        stack.push(c1);
        stack.push(c2);
        
        instance.execute();
        
        assertEquals("Error in over call", c1, stack.top());

        assertEquals("Error in over call - Invalid size", 3, stack.size());

    }
}
