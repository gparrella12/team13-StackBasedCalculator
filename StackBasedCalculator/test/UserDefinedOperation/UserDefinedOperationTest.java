package UserDefinedOperation;

import MainMathOperation.RPNSolver;
import VariablesManager.VariablesStorage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.util.Precision;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gparrella
 */
public class UserDefinedOperationTest {

    private HashMap<String, UserDefinedOperation> myOperations2;
    private HashMap<String, SupportedOperation> supported;
    private RPNSolver rpn = RPNSolver.getInstance();
    private final String[] arithmeticOperation = {"+", "-", "*", "/", "sqrt", "+-"};
    private final String[] stackOperations = {"dup", "over", "clear", "drop", "swap"};
    private final VariablesStorage variableManager;
    private List<Complex> myOperands;

    public UserDefinedOperationTest() {
        this.variableManager = new VariablesStorage();
        this.myOperands = new ArrayList<>();
        myOperands = List.of(new Complex(2, 3), new Complex(-4, 7), new Complex(0, 0.66), new Complex(45, 6), new Complex(-66, -0.235), new Complex(2, -3), new Complex(-2, 3), new Complex(0, -3));

        supported = new HashMap<>();
        for (String op : arithmeticOperation) {
            supported.put(op, new ArithmeticOperation(op, rpn));
        }
        for (String op : stackOperations) {
            supported.put(op, new StackOperation(op, rpn));
        }

        myOperations2 = new HashMap<>();
        UserDefinedOperation hypotenuse = new UserDefinedOperation("hypotenuse", 2, supported.get("dup"), supported.get("*"), supported.get("swap"), supported.get("dup"), supported.get("*"), supported.get("+"), supported.get("sqrt"));
        myOperations2.put("hypotenuse", hypotenuse);
        UserDefinedOperation squareModule = new UserDefinedOperation("squareModule", 2, hypotenuse, supported.get("dup"), supported.get("*"));
        myOperations2.put("squareModule", squareModule);
    }

    /**
     * Test of execute method, of class UserDefinedOperation.
     */
    @Test
    public void testExecute() {
        System.out.println("Execute - User Defined Operation");
        for (Operation op : myOperations2.values()) {
            
            for (int i = 0; i < 5; i++) {
                rpn.clear();
                Random generator = new Random(113131646);
                Complex op1 = myOperands.get(generator.nextInt(myOperands.size()));
                Complex op2 = myOperands.get(generator.nextInt(myOperands.size()));
                rpn.addNum(op1);
                rpn.addNum(op2);
                op.execute();
                switch (op.toString()) {
                    case "hypotenuse" -> {
                        Complex result = new Complex(Precision.round(this.hypotenuse(op1, op2).getReal(), 10), Precision.round(this.hypotenuse(op1, op2).getImaginary(), 10));
                        Complex myResult = new Complex(Precision.round(rpn.getAns().getReal(), 10), Precision.round(rpn.getAns().getImaginary(), 10));
                        assertEquals("Invalid result ", result, myResult);
                    }
                    case "squareModule" -> {
                        Complex result = new Complex(Precision.round(this.squareModule(op1, op2).getReal(), 10), Precision.round(this.squareModule(op1, op2).getImaginary(), 10));
                        Complex myResult = new Complex(Precision.round(rpn.getAns().getReal(), 10), Precision.round(rpn.getAns().getImaginary(), 10));
                        assertEquals("Invalid result ", result, myResult);
                    }
                    default -> {
                        fail("Cannot verify this operation: " + op.toString());
                    }
                }
                System.out.println("\t" + op.toString() + ": test #" + (i + 1) + "-> OK");
            }
            System.out.println("\t---------------------------------");

        }
    }

    private Complex hypotenuse(Complex c1, Complex c2) {
        Complex myOp1 = new Complex(c1.getReal(), c1.getImaginary());
        Complex myOp2 = new Complex(c2.getReal(), c2.getImaginary());
        return myOp1.pow(2).add(myOp2.pow(2)).sqrt();
    }

    private Complex squareModule(Complex c1, Complex c2) {
        Complex myOp1 = new Complex(c1.getReal(), c1.getImaginary());
        Complex myOp2 = new Complex(c2.getReal(), c2.getImaginary());
        return myOp1.pow(2).add(myOp2.pow(2));
    }

    /**
     * Test of getName method, of class UserDefinedOperation.
     */
    @Test
    public void testGetName() {
        fail("prototype");
    }

    /**
     * Test of setName method, of class UserDefinedOperation.
     */
    @Test
    public void testSetName() {
        fail("prototype");

    }

    /**
     * Test of getRequiredOperands method, of class UserDefinedOperation.
     */
    @Test
    public void testGetRequiredOperands() {
        fail("prototype");

    }

    /**
     * Test of setRequiredOperands method, of class UserDefinedOperation.
     */
    @Test
    public void testSetRequiredOperands() {
        fail("prototype");

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
        fail("prototype");

    }

    /**
     * Test of exportOperation method, of class UserDefinedOperation.
     */
    @Test
    public void testExportOperation() {
        fail("prototype");

    }

}
