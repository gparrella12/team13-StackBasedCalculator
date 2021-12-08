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
public class PushOperationTest {

    private ObservableStack<Complex> stack;

    public PushOperationTest() {
    }

    @Before
    public void setUp() {
        stack = new ObservableStack<>();
    }

    /**
     * Test of execute method, of class PushOperation.
     */
    @Test
    public void testExecute() {

        System.out.println("Push execute");

        Complex num = new Complex(5, 1);
        PushOperation instance = new PushOperation(stack, num);

        instance.execute();
        
        assertEquals("Error in push call - Invalid size", 1, stack.size());
        assertEquals("Error in push call", num, stack.top());
        stack.clear();

        fail("Push is correctly executed with incorrect operands");
    }
}
