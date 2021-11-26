package MainMathOperation;

import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Scanner;
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
    @Test(expected = MathParseException.class)
    public void testAddNum() {
        System.out.println("\naddNum");
        
        Scanner sc = new Scanner(new InputStreamReader(RPNSolver.class.getResourceAsStream("TestCasesParser.csv")));
        sc.nextLine();
        sc.useDelimiter(";|\\n|\\r");
        
        String input, testResult;

        while (sc.hasNext()){
            input = sc.next();
            testResult = sc.next();
            
            if (testResult.equals("fail"))
                try{
                    rpn.addNum(input);
                } catch (MathParseException e){
                    System.out.println("Fail for " + input);
                }
            else{
                rpn.addNum(input);
                Complex tmp = rpn.getAns();
                assertEquals("Wrong parsing detect [: in<"+ input +"> out<"+testResult+">] ", tmp.toString(), testResult);
                rpn.drop();
            }
        }
    }

//    @Test(expected = MathParseException.class)
//    public void testAddNumExcetpion() {
//        System.out.println("addNum - bad string format");
//        rpn.addNum();
//    }

    @Test(expected = NoSuchElementException.class)
    public void testSumExcetpion() {
        System.out.println("\nTest sum without numbers");
        rpn.clear();
        rpn.sum();
    }

    public void testSum() {
        System.out.println("\nsum");
        rpn.clear();
        rpn.addNum("5+1j");
        rpn.addNum("5");
        rpn.sum();

        Complex result = new Complex(10, 1);
        assertEquals("Wrong result : 5+1j + 5 ", rpn.getAns(), result);
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
        rpn.addNum("5+1j");
        rpn.addNum("5");
        rpn.subtraction();
        Complex result = new Complex(0, 1);
        assertEquals("Wrong result : 5+1j - 5 ", rpn.getAns(), result);

        System.out.println("subtraction - operands order");
        rpn.clear();
        rpn.addNum("5");
        rpn.addNum("9-1j");
        rpn.subtraction();
        result = new Complex(-4, 1);
        assertEquals("Wrong result : 5 - 9-1j ", rpn.getAns(), result);
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
        rpn.addNum("2+1j");
        rpn.addNum("1-3j");
        rpn.product();

        Complex result = new Complex(5, -5);
        assertEquals("Wrong result : 2+1j * 1-3j = 5-5j" + result, rpn.getAns(), result);
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
        rpn.addNum("5+1j");
        rpn.addNum("5+1j");
        rpn.division();
        Complex result = new Complex(1, 0);
        assertEquals("Wrong result : 5+1j / 5+1j ", rpn.getAns(), result);

        System.out.println("division - operands order");
        rpn.clear();
        rpn.addNum("5+1j");
        rpn.addNum("5");
        rpn.division();
        result = new Complex(1, 0.2);
        assertEquals("Wrong result : 5+1j / 5 ", rpn.getAns(), result);
    }
    
    @Test(expected = ArithmeticException.class)
    public void testDivisionByZero(){
        System.out.println("division by 0");
        rpn.clear();
        rpn.addNum("5+1j");
        rpn.addNum("0");
        rpn.division();
        Complex result = new Complex(Double.NaN, Double.NaN);
        assertEquals("Wrong result : 5+1j / 0 ", rpn.getAns(), result);
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
    public void testInvertSignExcetpion() {
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
