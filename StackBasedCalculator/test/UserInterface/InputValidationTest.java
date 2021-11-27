package UserInterface;

import MainMathOperation.RPNSolver;
import java.io.InputStreamReader;
import java.util.Scanner;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.exception.MathParseException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ernesto
 */
public class InputValidationTest {

    public InputValidationTest() {
    }

    @Before
    public void setUp() {

    }

    /**
     * Test of addNum method, of class RPNSolver.
     *
     * @author fsonnessa
     */
    @Test
    public void testAddNum() {
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

            System.out.print("input: " + input + "\tresult: " + testResult);

            if (testResult.equals("fail")) {
                try {
                    rpn.addNum(i.parser(input, "j"));
                } catch (MathParseException e) {
                    System.out.println(" >> Fail for " + input);
                    exceptionFlag = true;
                }
                if (!exceptionFlag) {
                    throw new RuntimeException(">> Attention! this input < " + input + " > not fail!");
                }
                exceptionFlag = false;
            } else {
                rpn.addNum(i.parser(input, "j"));
                Complex tmp = rpn.getAns();
                assertEquals("Wrong parsing detect : in< " + input + " > out< " + testResult + " >] ", tmp.toString(), testResult);
                rpn.drop();
                System.out.println(" >> OK");
            }
        }
    }

}
