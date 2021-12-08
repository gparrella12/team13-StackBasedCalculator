package UserDefinedOperation;

import MainMathOperation.RPNSolver;
import static UserDefinedOperation.ArithmeticOperation.DIVISION;
import static UserDefinedOperation.ArithmeticOperation.INVSIGN;
import static UserDefinedOperation.ArithmeticOperation.SQRT;
import static UserDefinedOperation.ArithmeticOperation.SUBTRACTION;
import static UserDefinedOperation.ArithmeticOperation.SUM;
import org.apache.commons.math3.complex.Complex;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class test the feature of ArithmeticOperation class. In this case, the
 * test is focused on the correct call on RPNSolver method, that is already
 * tested.
 *
 * @author gparrella
 */
public class ArithmeticOperationTest {

    private RPNSolver rpn = RPNSolver.getInstance();

    public ArithmeticOperationTest() {
    }

    /**
     * Test of execute method, of class ArithmeticOperation.
     */
    @Test
    public void testExecute() {
        System.out.println("Execute - ArithmeticOperation <- Valid operation");
        String operations[] = {"+", "-", "*", "/", "sqrt", "+-"};
        for (String op : operations) {
            ArithmeticOperation instance = new ArithmeticOperation(op, rpn);
            Complex c1 = new Complex(1, 5), c2 = new Complex(0.256, -69);
            rpn.addNum(c1);
            rpn.addNum(c2);
            instance.execute();
            switch (instance.getName()) {
                case SUM -> {
                    assertEquals("Error in sum method call", c1.add(c2), rpn.getAns());
                    assertEquals("Error in sum method call - Invalid size", 1, rpn.getStackSize());
                }
                case SUBTRACTION -> {
                    assertEquals("Error in subtraction method call", c1.subtract(c2), rpn.getAns());
                    assertEquals("Error in subtraction method call - Invalid size", 1, rpn.getStackSize());
                }
                case "*" -> {
                    assertEquals("Error in procut method call", c1.multiply(c2), rpn.getAns());
                    assertEquals("Error in product method call - Invalid size", 1, rpn.getStackSize());
                }
                case DIVISION -> {
                    assertEquals("Error in division method call", c1.divide(c2), rpn.getAns());
                    assertEquals("Error in division method call - Invalid size", 1, rpn.getStackSize());
                }
                case SQRT -> {
                    assertEquals("Error in sqrt method call", c2.sqrt(), rpn.getAns());
                    assertEquals("Error in sqrt method call - Invalid size", 2, rpn.getStackSize());
                }
                case INVSIGN -> {
                    assertEquals("Error in +- method call", c2.negate(), rpn.getAns());
                    assertEquals("Error in +- method call - Invalid size", 2, rpn.getStackSize());
                }
            }
            //Reset the stack
            rpn.clear();
            System.out.println("\t" + instance.getName() + " -> OK");
        }
    }

    /**
     * Test of execute method, of class ArithmeticOperation.
     */
    @Test
    public void testExecuteWithInvalid() {
        System.out.println("Execute - ArithmeticOperation <- Invalid operation");
        String operations[] = {"++", "-+", "/*", ".0+", "\\", "--+", "sqrT", "sqr"};
        for (String op : operations) {
            try {
                ArithmeticOperation instance = new ArithmeticOperation(op, rpn);
                fail(op + " operation accepted");
            } catch (UnsupportedOperationException ex) {
                System.out.println("\t" + op + " not accepted -> OK ");
            }
        }
    }

}
