package UserInterface;

import Operations.OperationsEnum;
import Stack.ObservableStack;
import UserInterface.Parser.ParserEnum;
import UserInterface.Parser.ParserFactory;
import java.io.InputStreamReader;
import java.util.Scanner;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexFormat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Ernesto
 */
public class InputValidationTest {

    private ObservableStack<Complex> stack;

    @Before
    public void setUp() {
        stack = new ObservableStack<>();
    }

    /**
     * Test of testParseComplex method, of class InputValidation.
     *
     * @author fsonnessa
     */
    @Test
    public void testParseComplex() {
        ParserFactory parser = new ParserFactory();
        System.out.println("\naddNum");

        Scanner sc = new Scanner(new InputStreamReader(InputValidationTest.class.getResourceAsStream("TestCasesParser.csv")));
        sc.nextLine();
        sc.useDelimiter(";");

        String input, testResult;
        boolean exceptionFlag = false;
        Complex number;
        while (sc.hasNext()) {
            input = sc.next().replace("\n", "").replace("\r", "");
            testResult = sc.next().replace("\n", "").replace("\r", "");

            System.out.println("input: " + input + "\tresult: " + testResult);

            if (testResult.equals("fail")) {

                try {
                    number = new ComplexFormat().parse(parser.getParser(ParserEnum.COMPLEXNUMBER).check(input));
                } catch (NullPointerException e) {
                    number = null;
                }
                stack.push(number);
                if (number == null) {
                    System.out.println(" >> Fail for " + input);
                    exceptionFlag = true;
                }

                if (!exceptionFlag) {
                    throw new RuntimeException(">> Attention! this input < " + input + " > not fail!");
                }
                exceptionFlag = false;
            } else {
                try {
                    number = new ComplexFormat().parse(parser.getParser(ParserEnum.COMPLEXNUMBER).check(input));
                } catch (NullPointerException e) {
                    number = null;
                }

                if (number == null) {
                    throw new RuntimeException(">> Attention! this input < " + input + " > not parsed!");
                }

                stack.push(number);
                Complex tmp = stack.top();

                assertEquals("Wrong parsing detect : in< " + input + " > out< " + testResult + " >] ", tmp.toString(), testResult);
                stack.pop();
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
        ParserFactory parser = new ParserFactory();
        OperationsEnum operation;
        for (OperationsEnum e : OperationsEnum.values()) {
            try {
                operation = OperationsEnum.valueOfString(parser.getParser(ParserEnum.OPERATION).check(e.toString()));
            } catch (UnsupportedOperationException ex) {
                operation = null;
            }
            /*if (!e.equals(e.PUSH)) {
                assertEquals("The inserted operation is invalid", e, operation);
            }*/

        }

        OperationsEnum[] o = OperationsEnum.values();
        for (int k = 0; k < o.length; k++) {
            for (int j = o.length - 1; j > -1; j--) {
                try {
                    operation = OperationsEnum.valueOfString(parser.getParser(ParserEnum.OPERATION).check(o[j].toString()));
                } catch (UnsupportedOperationException ex) {
                    operation = null;
                }

                if (o[j] != o[k] && !o[j].equals(OperationsEnum.PUSH)) {
                    assertNotEquals("The inserted operation is invalid", o[k], operation);
                }

            }
        }

    }

}
