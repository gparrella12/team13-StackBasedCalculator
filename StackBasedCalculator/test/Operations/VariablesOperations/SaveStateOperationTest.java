/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Operations.VariablesOperations;

import Stack.ObservableStack;
import org.apache.commons.math3.complex.Complex;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fsonnessa
 */
public class SaveStateOperationTest {
    
    private ObservableStack<Complex> stack;
    
    public SaveStateOperationTest() {
    }
    
    @Before
    public void setUp() {
        stack = new ObservableStack<>();
    }

    /**
     * Test of execute method, of class SaveStateOperation.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        SaveStateOperation instance = null;
        instance.execute();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class SaveStateOperation.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        SaveStateOperation instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
