package MainMathOperation;

import java.util.NoSuchElementException;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.exception.MathParseException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fsonnessa
 */
public class RPNSolverTest {

    private RPNSolver rpn = null;

    public RPNSolverTest() {
    }

    @Before
    public void setUp() {
        rpn = RPNSolver.getInstance();
    }

    /**
     * Test of addNum method, of class RPNSolver.
     */
    @Test
    public void testAddNum() {
        System.out.println("\naddNum");

        String[] input = {"2j+1", "1-3j", "0 + 0j", "5.1 + 0j", "2+j", "1 -2.1 j", "1 + 2.1j", "3.3 - j15", "j", "-j", "2 j", "4"};
        String[] result = {"(1.0, 2.0)", "(1.0, -3.0)", "(0.0, 0.0)", "(5.1, 0.0)", "(2.0, 1.0)", "(1.0, -2.1)", "(1.0, 2.1)", "(3.3, -15.0)", "(0.0, 1.0)", "(0.0, -1.0)", "(0.0, 2.0)", "(4.0, 0.0)"};

        rpn.clear();
        for (String s : input) {
            rpn.addNum(s);
        }

        for (int i = result.length - 1; i > -1; i--) {
            Complex tmp = rpn.getAns();
            assertEquals("Wrong parsing detect [" + i + "]: ", tmp.toString(), result[i]);
            rpn.drop();
        }
    }

    @Test(expected = MathParseException.class)
    public void testAddNumExcetpion() {
        System.out.println("addNum - bad string format");

        String[] input = {"abc", "--2j+2"};
        rpn.clear();
        for (String s : input) {
            rpn.addNum(s);
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void testSumExcetpion() {
        System.out.println("\nTest sum without numbers");
        rpn.clear();
        rpn.sum();
    }

    public void testSum() {
        System.out.println("\nsum");
        rpn.clear();
        rpn.addNum("5+j");
        rpn.addNum("5");
        rpn.sum();

        Complex result = new Complex(10, 1);
        assertEquals("Wrong result : 5+j + 5 ", rpn.getAns(), result);
    }

    @Test(expected = NoSuchElementException.class)
    public void testSubtractionExcetpion() {
        System.out.println("\nTest subtraction without numbers");
        rpn.clear();
        rpn.subtraction();
    }

    /**
     * Test of subtraction method, of class RPNSolver.
     */
    @Test
    public void testSubtraction() {
        System.out.println("\nsubtraction - simple");

        rpn.clear();
        rpn.addNum("5+j");
        rpn.addNum("5");
        rpn.subtraction();
        Complex result = new Complex(0, 1);
        assertEquals("Wrong result : 5+j - 5 ", rpn.getAns(), result);

        System.out.println("subtraction - operands order");
        rpn.clear();
        rpn.addNum("5");
        rpn.addNum("9-j");
        rpn.subtraction();
        result = new Complex(-4, 1);
        assertEquals("Wrong result : 5 - 9-j ", rpn.getAns(), result);
    }

    @Test(expected = NoSuchElementException.class)
    public void testProductExcetpion() {
        System.out.println("\nTest product without numbers");
        rpn.clear();
        rpn.product();
    }

    /**
     * Test of product method, of class RPNSolver.
     */
    @Test
    public void testProduct() {
        System.out.println("\nproduct");

        rpn.clear();
        rpn.addNum("2+j");
        rpn.addNum("1-3j");
        rpn.product();

        Complex result = new Complex(5, -5);
        assertEquals("Wrong result : 2+j * 1-3j = 5-5j" + result, rpn.getAns(), result);
    }

    @Test(expected = NoSuchElementException.class)
    public void testDivisionExcetpio() {
        System.out.println("\nTest division without numbers");
        rpn.clear();
        rpn.division();
    }

    /**
     * Test of division method, of class RPNSolver.
     */
    @Test
    public void testDivision() {
        System.out.println("\ndivision - simple");

        rpn.clear();
        rpn.addNum("5+j");
        rpn.addNum("5+j");
        rpn.division();
        Complex result = new Complex(1, 0);
        assertEquals("Wrong result : 5+j / 5+j ", rpn.getAns(), result);

        System.out.println("division - operands order");
        rpn.clear();
        rpn.addNum("5+j");
        rpn.addNum("5");
        rpn.division();
        result = new Complex(1, 0.2);
        assertEquals("Wrong result : 5+j / 5 ", rpn.getAns(), result);

        System.out.println("division by 0");
        rpn.clear();
        rpn.addNum("5+j");
        rpn.addNum("0");
        rpn.division();
        result = new Complex(Double.NaN, Double.NaN);
        assertEquals("Wrong result : 5+j / 0 ", rpn.getAns(), result);
    }

    @Test(expected = NoSuchElementException.class)
    public void testSqrtExcetpio() {
        System.out.println("\nTest sqrt without number");
        rpn.clear();
        rpn.sqrt();
    }

    /**
     * Test of sqrt method, of class RPNSolver.
     */
    @Test
    public void testSqrt() {
        System.out.println("\nsqrt");

        rpn.clear();
        rpn.addNum("-4");
        rpn.sqrt();
        Complex result = new Complex(0, 2);
        assertEquals("Wrong result : sqrt(-4) ", rpn.getAns(), result);
    }

    @Test(expected = NoSuchElementException.class)
    public void testInvertSignExcetpio() {
        System.out.println("\nTest invertSign without number");
        rpn.clear();
        rpn.invertSign();
    }

    /**
     * Test of invertSign method, of class RPNSolver.
     */
    @Test
    public void testInvertSign() {
        System.out.println("\ninvertSign");

        rpn.clear();
        rpn.addNum("-4+3.5j");
        rpn.invertSign();
        Complex result = new Complex(4, -3.5);
        assertEquals("Wrong result : +-(-4+.35j) ", rpn.getAns(), result);
    }
}
