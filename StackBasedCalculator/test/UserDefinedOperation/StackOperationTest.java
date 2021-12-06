package UserDefinedOperation;

import MainMathOperation.RPNSolver;
import static UserDefinedOperation.StackOperation.CLEAR;
import static UserDefinedOperation.StackOperation.DROP;
import static UserDefinedOperation.StackOperation.DUP;
import static UserDefinedOperation.StackOperation.OVER;
import static UserDefinedOperation.StackOperation.SWAP;
import org.apache.commons.math3.complex.Complex;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class test the feature of StackOperation class.
 * In this case, the test is focused on the correct call on RPNSolver method, that is
 * already tested.
 * 
 * @author gparrella
 */
public class StackOperationTest {

    private RPNSolver rpn = RPNSolver.getInstance();

    public StackOperationTest() {
    }

    /**
     * Test of execute method, of class StackOperation. In this case, we test
     * only that the correct method of RPN solver class will be invoked.
     */
    @Test
    public void testExecute() {
        System.out.println("Execute - StackOperation <- Valid operation");
        String operations[] = {"dup", "over", "clear", "drop", "swap"};
        for (String op : operations) {
            StackOperation instance = new StackOperation(op, rpn);
            Complex c1 = new Complex(1, 5), c2 = new Complex(0.256, -69);
            rpn.addNum(c1);
            rpn.addNum(c2);
            instance.execute();
            switch (instance.getName()) {
                case CLEAR -> {
                    assertEquals("Error in clear call", 0, rpn.getStackSize());
                }
                case DUP -> {
                    assertEquals("Error in dup call", c2, rpn.getAns());
                }
                case DROP -> {
                    assertEquals("Error in drop call - Invalid size", 1, rpn.getStackSize());
                    assertEquals("Error in drop call", c1, rpn.getAns());
                }
                case SWAP -> {
                    assertEquals("Error in swap call - Invalid size", 2, rpn.getStackSize());
                    assertEquals("Error in swap call", c1, rpn.getAns());
                }
                case OVER -> {
                    assertEquals("Error in over call - Invalid size", 3, rpn.getStackSize());
                    assertEquals("Error in over call", c1, rpn.getAns());
                    rpn.over();
                }

            }
            //Reset the stack
            rpn.clear();
            System.out.println("\t" + instance.getName() + " -> OK");
        }
        // Test push method
        Complex toInsert = new Complex(1, 15);
        StackOperation op = new StackOperation(StackOperation.PUSH, rpn, toInsert);
        op.execute();
        assertEquals("Error in addNum call - Invalid size", 1, rpn.getStackSize());
        assertEquals("Error in addNum call", toInsert, rpn.getAns());
        System.out.println("\t" + op.getName() + "with correct operand -> OK");
        rpn.clear();
        // Test push method with invalid element
        op = new StackOperation(StackOperation.PUSH, rpn);
        try {
            op.execute();
        } catch (NumberFormatException ex) {
            System.out.println("\t" + op.getName() + "with incorrect operand -> OK");
            return;
        }
        fail("Push is correctly executed with incorrect operands");
    }

    /**
     * Test of execute method, of class StackOperation.
     */
    @Test
    public void testExecuteWithInvalid() {
        System.out.println("Execute - StackOperation <- Invalid operation");
        String operations[] = {"duP", "oVer", "CLEAR", "dropp", "swaP", "\\", "clearstack"};
        for (String op : operations) {
            try {
                StackOperation instance = new StackOperation(op, rpn);
                fail(op + " operation accepted");
            } catch (UnsupportedOperationException ex) {
                System.out.println("\t" + op + " not accepted -> OK ");
            }
        }
    }

}
