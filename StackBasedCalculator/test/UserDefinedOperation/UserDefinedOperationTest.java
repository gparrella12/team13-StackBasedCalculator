package UserDefinedOperation;

import MainMathOperation.RPNSolver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.math3.complex.Complex;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gparrella
 */
public class UserDefinedOperationTest {

    private List<UserDefinedOperation> myOperations;

    private class MyOperands {

        private RPNSolver rpn = RPNSolver.getInstance();
        private List<Complex> myOperand;

        public MyOperands(Complex... operands) {
            this.myOperand = new ArrayList<>();
            this.myOperand.addAll(Arrays.asList(operands));
        }

        private void pushIntoStack() {
            myOperand.forEach(c -> {
                rpn.addNum(c);
            });
        }

        public void execute(Operation operation, Complex expected) {
            this.pushIntoStack();
            operation.execute();
            assertEquals(operation + " error: ", expected, rpn.getAns());
            rpn.clear();
        }
    }

    public UserDefinedOperationTest() {
        myOperations = new ArrayList<>();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        UserDefinedOperation hypotenuse = new UserDefinedOperation("hypotenuse", 2, "dup", "*", "swap", "dup", "*", "+", "sqrt");
        myOperations.add(hypotenuse);
        UserDefinedOperation squareModule = new UserDefinedOperation("squareModule", 2,hypotenuse,new BasicOperation("dup"),new BasicOperation("*"));
        myOperations.add(squareModule);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of execute method, of class UserDefinedOperation.
     */
    @Test
    public void testExecute() {
        System.out.println("Execute");

        MyOperands twoOperands = new MyOperands(new Complex(9, 0), new Complex(8, 0));
        for (Operation op : myOperations) {
            if (op.toString().equals("hypotenuse")) {
                
                twoOperands.execute(myOperations.get(0), new Complex(145, 0).sqrt());
            }else{
                twoOperands.execute(myOperations.get(1), new Complex(145, 0));
            }
            System.out.println("\tMy operation: " + op + " - test");
        }

    }

    /**
     * Test of getName method, of class UserDefinedOperation.
     */
    @Test
    public void testGetName() {
        System.out.println("getName - Test");
        UserDefinedOperation instance = new UserDefinedOperation("sum", 4, "+", "+", "+");
        String expResult = "sum";
        String result = instance.getName();
        assertEquals(expResult, result);

    }

    /**
     * Test of setName method, of class UserDefinedOperation.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "myNewSum";
        UserDefinedOperation instance = new UserDefinedOperation("ciao", 1, "+");
        instance.setName(name);
    }

    /**
     * Test of getRequiredOperands method, of class UserDefinedOperation.
     */
    @Test
    public void testGetRequiredOperands() {
        System.out.println("getRequiredOperands");
        UserDefinedOperation instance = new UserDefinedOperation("ciao", 1, "+");
        int expResult = 1;
        int result = instance.getRequiredOperands();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRequiredOperands method, of class UserDefinedOperation.
     */
    @Test
    public void testSetRequiredOperands() {
        System.out.println("setRequiredOperands");
        int requiredOperands = 2;
        UserDefinedOperation instance = new UserDefinedOperation("ciao", 1, "+");
        instance.setRequiredOperands(requiredOperands);
        fail("Prototype");
    }

    /**
     * Test of updateList method, of class UserDefinedOperation.
     */
    @Test
    public void testUpdateList() {
        fail("Not yet implemented");
    }

    /**
     * Test of getListAsString method, of class UserDefinedOperation.
     */
    @Test
    public void testGetListAsString() {
        System.out.println("getListAsString");
        UserDefinedOperation instance = null;
        String expResult = "";
        String result = instance.getListAsString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of exportOperation method, of class UserDefinedOperation.
     */
    @Test
    public void testExportOperation() {
        System.out.println("exportOperation");
        UserDefinedOperation instance = null;
        instance.exportOperation();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
