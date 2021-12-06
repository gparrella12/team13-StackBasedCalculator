package UserInterface;

import MainMathOperation.RPNSolver;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.exception.MathParseException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

/**
 *
 * @author Ernesto
 */
public class InputValidationTest {

    /**
     * Test of testParseComplex method, of class InputValidation.
     *
     * @author fsonnessa
     */
    @Test
    public void testParseComplex() {
        InputValidation i = new InputValidation();
        RPNSolver rpn = RPNSolver.getInstance();
        System.out.println("\naddNum");

        Scanner sc = new Scanner(new InputStreamReader((InputValidation.class.getResourceAsStream("TestCasesParser.csv"))));
        sc.nextLine();
        sc.useDelimiter(";");

        String input, testResult;
        boolean exceptionFlag = false;

        while (sc.hasNext()) {
            input = sc.next().replace("\n", "").replace("\r", "");
            testResult = sc.next().replace("\n", "").replace("\r", "");

            System.out.println("input: " + input + "\tresult: " + testResult);

            if (testResult.equals("fail")) {
                try {
                    rpn.addNum(i.parseComplex(input, "j"));
                } catch (NumberFormatException ex) {
                    System.out.println(" >> Fail for " + input);
                    exceptionFlag = true;
                }
                if (!exceptionFlag) {
                    throw new RuntimeException(">> Attention! this input < " + input + " > not fail!");
                }
                exceptionFlag = false;
            } else {
                rpn.addNum(i.parseComplex(input, "j"));
                Complex tmp = rpn.getAns();
                assertEquals("Wrong parsing detect : in< " + input + " > out< " + testResult + " >] ", tmp.toString(), testResult);
                rpn.drop();
                System.out.println(" >> OK");
            }

        }
    }

    /**
     * Test of CheckOperation method, of class InputValidation.
     *
     * @author ermancusi
     */
    @Test
    public void testCheckOperation() {
        InputValidation i = new InputValidation();
        String[] stackOperations = {"dup", "over", "clear", "drop", "swap"};
        String[] mathOperations = {"+", "-", "*", "/", "sqrt", "+-"};

        for (String m : mathOperations) {
            assertEquals("The inserted operation is invalid", m, i.checkOperation(m));
        }
        for (String s : stackOperations) {
            assertEquals("The inserted operation is invalid", s, i.checkOperation(s));
        }

        for (int k = 0; k < stackOperations.length; k++) {
            for (int j = 0; j < mathOperations.length; j++) {
                assertNotEquals("The inserted operation is invalid", mathOperations[j], i.checkOperation(stackOperations[k]));
            }
        }

    }
}