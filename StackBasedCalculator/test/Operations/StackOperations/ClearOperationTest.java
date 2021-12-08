/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Operations.StackOperations;

import Stack.ObservableStack;
import org.apache.commons.math3.complex.Complex;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Speranza
 */
public class ClearOperationTest {

    private ObservableStack<Complex> stack;

    public ClearOperationTest() {
    }

    @Before
    public void setUp() {
        stack = new ObservableStack<>();
    }

    /**
     * Test of execute method, of class ClearOperation.
     */
    @Test
    public void testExecute() {

        System.out.println("Clear execute");

        ClearOperation instance = new ClearOperation(stack);

        stack.push(new Complex(1, 5));
        stack.push(new Complex(0.256, -70));
        stack.push(new Complex(-3, 3.33));
        
        instance.execute();
        
        assertEquals("Error in Clear call", 0, stack.size());
        
    }
}
