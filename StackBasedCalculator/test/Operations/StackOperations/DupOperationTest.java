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
public class DupOperationTest {

    private ObservableStack<Complex> stack;

    public DupOperationTest() {
    }

    @Before
    public void setUp() {
        stack = new ObservableStack<>();
    }

    /**
     * Test of execute method, of class DupOperation.
     */
    @Test
    public void testExecute() {

        System.out.println("Dup execute");

        Complex c1 = new Complex(3, -3);
        DupOperation instance = new DupOperation(stack);

        stack.push(c1);
        instance.execute();
        assertEquals("Error in dup call", c1, stack.top());

    }
}
