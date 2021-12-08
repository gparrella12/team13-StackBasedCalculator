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
public class SwapOperationTest {

    private ObservableStack<Complex> stack;

    public SwapOperationTest() {
    }

    @Before
    public void setUp() {
        stack = new ObservableStack<>();
    }

    /**
     * Test of execute method, of class SwapOperation.
     */
    @Test
    public void testExecute() {

        System.out.println("Swap execute");

        Complex c1 = new Complex(1, 5);
        Complex c2 = new Complex(0.256, -69);
        SwapOperation instance = new SwapOperation(stack);

        stack.push(c1);
        stack.push(c2);
        
        instance.execute();
        assertEquals("Error in swap call", c1, stack.top());
        
        instance.execute();
        assertEquals("Error in swap call - Invalid size", 2, stack.size());

    }
}
