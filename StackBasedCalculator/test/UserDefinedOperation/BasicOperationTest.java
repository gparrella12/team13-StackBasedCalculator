package UserDefinedOperation;

import MainMathOperation.RPNSolver;
import java.util.NoSuchElementException;
import org.apache.commons.math3.complex.Complex;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gparrella
 */
public class BasicOperationTest {

    private final static String operations[] = {"+", "-", "*", "/", "sqrt", "+-", "dup", "over", "clear", "drop", "swap"};
    private final RPNSolver rpn = RPNSolver.getInstance();
    
    public BasicOperationTest() {
    }
    /**
     * Test of execute method, of class BasicOperation. This test function test
     * only if the correct method of RPN solver was called, because RPN solver
     * is already tested.
     * <p>
     * This test cover only execution with well-defined operations, such as:
     * "+", "-", "*", "/", "sqrt", "+-", "dup", "over", "clear", "drop", "swap".
     */
    @Test
    public void testExecute() {
        System.out.println("Basic Operation - Execute Test <- Correct Operation");
        for (String op : operations) {
            BasicOperation instance = new BasicOperation(op);
            Complex c1 = new Complex(1, 2), c2 = new Complex(0.25, 6.36);
            rpn.addNum(c1);
            rpn.addNum(c2);

            instance.execute();
            switch (op) {
                case "+" -> {
                    assertEquals("Bad invocation of sum function", rpn.getAns(), c1.add(c2));
                }
                case "-" -> {
                    assertEquals("Bad invocation of subtraction function", rpn.getAns(), c1.subtract(c2));
                }
                case "*" -> {
                    assertEquals("Bad invocation of product function", rpn.getAns(), c1.multiply(c2));
                }
                case "/" -> {
                    assertEquals("Bad invocation of division function", rpn.getAns(), c1.divide(c2));
                }
                case "sqrt" -> {
                    assertEquals("Bad invocation of sqrt function", rpn.getAns(), c2.sqrt());
                }
                case "+-" -> {
                    assertEquals("Bad invocation of +- function", rpn.getAns(), c2.negate());
                }
                case "clear" -> {
                    try {
                        rpn.getAns();
                    } catch (NoSuchElementException ex) {
                        break;
                    }
                    fail("The stack is not empty after a clear - Bad invocation of RPN clear function");
                }
                case "dup" -> {
                    Complex top = rpn.getAns();
                    assertEquals("Bad invocation of top function", c2, top);
                }
                case "drop" -> {
                    Complex top = rpn.getAns();
                    assertEquals("Bad invocation of drop function", c1, top);
                }
                case "swap" -> {
                    Complex top = rpn.getAns();
                    assertEquals("Bad invocation of swap function", c1, top);
                }
                case "over" -> {
                    Complex top = rpn.getAns();
                    assertEquals("Bad invocation of over function", c1, top);
                }
            }
            System.out.println("\t"+op+" is correct -> OK");
        }
    }

    /**
     * Test of execute method, of class BasicOperation. This test function test
     * only if the correct method of RPN solver was called, because RPN solver
     * is already tested.
     * <p>
     * This test cover execution with well-defined operations, such as: "+",
     * "-", "*", "/", "sqrt", "+-", "dup", "over", "clear", "drop", "swap" and
     * also with not-well defined operation, for example when we create a new
     * basic operation with name \, but this name is not supported actually by
     * the calculator.
     */
    @Test()
    public void testExecuteWithException() {
        System.out.println("Basic Operation - Execute Test <- Incorrect operation");
        Complex c1 = new Complex(1, 2), c2 = new Complex(0.25, 6.36);
        rpn.addNum(c1);
        rpn.addNum(c2);
        String fakeOperations[] = {"++", "-+", "**", "//", "Sqrt", "dUp", "ovEr", "CleaR", "DROP", "swapp"};
        for (String op : fakeOperations) {
            try {
                BasicOperation instance = new BasicOperation(op);
                fail("Operation : "+ op + " is considered as good operation.");
            } catch (UnsupportedOperationException ex) {
                System.out.println("\t"+op+" is incorrect -> OK");
            }
        }
    }

}
