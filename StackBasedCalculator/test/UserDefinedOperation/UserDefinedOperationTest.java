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
 * This class test the UserDefinedOperation's feature.
 *
 * @author gparrella
 */
public class UserDefinedOperationTest {

    private final HashMap<String, UserDefinedOperation> myOperations2;
    private final HashMap<String, SupportedOperation> supported;
    private final RPNSolver rpn = RPNSolver.getInstance();
    private final String[] arithmeticOperation = {"+", "-", "*", "/", "sqrt", "+-"};
    private final String[] stackOperations = {"dup", "over", "clear", "drop", "swap"};
    private VariablesStorage variableManager;
    private List<Complex> myOperands;

    public UserDefinedOperationTest() {
        // Initialize the structures
        this.variableManager = new VariablesStorage();
        this.myOperands = new ArrayList<>();
        this.variableManager = new VariablesStorage();
        // Set some operands
        myOperands = List.of(new Complex(2, 3), new Complex(-4, 7), new Complex(0, 0.66), new Complex(45, 6), new Complex(-66, -0.235), new Complex(2, -3), new Complex(-2, 3), new Complex(0, -3));
        //Inizialize supported operation - Arithmetic
        supported = new HashMap<>();
        for (String op : arithmeticOperation) {
            supported.put(op, new ArithmeticOperation(op, rpn));
        }
        //Inizialize supported operation - Stack
        for (String op : stackOperations) {
            supported.put(op, new StackOperation(op, rpn));
        }
        // Initialize some operations that require 2 operands
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

    /**
     * Test of getName method, of class UserDefinedOperation.
     */
    @Test
    public void testGetName() {
        UserDefinedOperation hypotenuse = new UserDefinedOperation("hypotenuse", 2, supported.get("dup"), supported.get("*"), supported.get("swap"), supported.get("dup"), supported.get("*"), supported.get("+"), supported.get("sqrt"));
        assertEquals("Name not valid ", "hypotenuse", hypotenuse.getName());
    }

    /**
     * Test of setName method, of class UserDefinedOperation.
     */
    @Test
    public void testSetName() {
        UserDefinedOperation hypotenuse = new UserDefinedOperation("hypotenuse", 2, supported.get("dup"), supported.get("*"), supported.get("swap"), supported.get("dup"), supported.get("*"), supported.get("+"), supported.get("sqrt"));
        hypotenuse.setName("myHypotenuse");
        assertEquals("Name not valid ", "myHypotenuse", hypotenuse.getName());
    }

    /**
     * Test of getRequiredOperands method, of class UserDefinedOperation.
     */
    @Test
    public void testGetRequiredOperands() {
        UserDefinedOperation hypotenuse = new UserDefinedOperation("hypotenuse", 2, supported.get("dup"), supported.get("*"), supported.get("swap"), supported.get("dup"), supported.get("*"), supported.get("+"), supported.get("sqrt"));
        assertEquals("Operands not valid ", 2, hypotenuse.getRequiredOperands());
    }

    /**
     * Test of setRequiredOperands method, of class UserDefinedOperation.
     */
    @Test
    public void testSetRequiredOperands() {
        UserDefinedOperation hypotenuse = new UserDefinedOperation("hypotenuse", 2, supported.get("dup"), supported.get("*"), supported.get("swap"), supported.get("dup"), supported.get("*"), supported.get("+"), supported.get("sqrt"));
        hypotenuse.setRequiredOperands(3);
        assertEquals("Operands not valid ", 3, hypotenuse.getRequiredOperands());
    }

    /**
     * Test of updateList method, of class UserDefinedOperation.
     */
    @Test
    public void testUpdateList() {
        UserDefinedOperation hypotenuse = new UserDefinedOperation("hypotenuse", 2, supported.get("dup"), supported.get("*"), supported.get("swap"), supported.get("dup"), supported.get("*"), supported.get("+"), supported.get("sqrt"));
        // Remove sqrt at the end of the list 
        hypotenuse.updateList(supported.get("dup"), supported.get("*"), supported.get("swap"), supported.get("dup"), supported.get("*"), supported.get("+"));
        rpn.addNum(new Complex(3, 0));
        rpn.addNum(new Complex(4, 0));
        // Verify if now there are the correct result
        hypotenuse.execute();
        Complex result = new Complex(Precision.round(this.squareModule(new Complex(3, 0), new Complex(4, 0)).getReal(), 10), Precision.round(this.squareModule(new Complex(3, 0), new Complex(4, 0)).getImaginary(), 10));
        Complex myResult = new Complex(Precision.round(rpn.getAns().getReal(), 10), Precision.round(rpn.getAns().getImaginary(), 10));
        assertEquals("Invalid result - List not updated", result, myResult);
        System.out.println("UpdateList -> OK");
    }

    /**
     * Test of contains method, of class UserDefinedOperation.
     */
    @Test
    public void testContains() {
        System.out.println("Test contains");
        UserDefinedOperation hypotenuse = new UserDefinedOperation("hypotenuse", 2, supported.get("dup"), supported.get("*"), supported.get("swap"), supported.get("dup"), supported.get("*"), supported.get("+"), supported.get("sqrt"));
        // Test with SupportedOperation
        assertEquals("Operation not included: ", true, hypotenuse.contains(supported.get("dup")));
        assertEquals("Operation not included: ", true, hypotenuse.contains(supported.get("+")));
        assertEquals("Operation not included: ", true, hypotenuse.contains(supported.get("*")));
        assertEquals("Operation not included: ", true, hypotenuse.contains(supported.get("sqrt")));
        assertEquals("Operation included: ", false, hypotenuse.contains(supported.get("+-")));
        assertEquals("Operation included: ", false, hypotenuse.contains(supported.get("drop")));
        // Test with VariableOperation
        UserDefinedOperation sumWithVar = new UserDefinedOperation("sumWithVar", 2, supported.get("+"), new VariableOperation(variableManager, "a", rpn, ">"), new StackOperation("push", rpn, new Complex(1, 0)));
        assertEquals("Operation not included: ", true, sumWithVar.contains(new VariableOperation(variableManager, "a", rpn, ">")));
        assertEquals("Operation not included: ", true, sumWithVar.contains(new StackOperation("push", rpn, new Complex(1, 0))));
        System.out.println("\t" + "Test with SupportedOperation -> OK");
        // Test with nested User-Defined Operation
        UserDefinedOperation squareModule = new UserDefinedOperation("squareModule", 2, hypotenuse, supported.get("dup"), supported.get("*"));
        assertEquals(true, squareModule.contains(hypotenuse));
        System.out.println("\t" + "Test with Nested UserDefinedOperation -> OK");
        // Double nested check
        UserDefinedOperation squareNegativeModule = new UserDefinedOperation("squareNegativeModule", 1, squareModule, supported.get("+-"));
        assertEquals("Operation not included: ",true,squareNegativeModule.contains(squareModule));
        assertEquals("Operation not included: ",true,squareNegativeModule.contains(hypotenuse));
        assertEquals("Operation not included: ", true, squareNegativeModule.contains(supported.get("sqrt")));
        assertEquals("Operation not included: ", true, squareNegativeModule.contains(supported.get("+-")));
        System.out.println("\t" + "Test with Double-Nested UserDefinedOperation -> OK");
    }

    /**
     * Test of exportOperation method, of class UserDefinedOperation.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testExportOperation() {
        // the method isn't implemented
        UserDefinedOperation hypotenuse = new UserDefinedOperation("hypotenuse", 2, supported.get("dup"), supported.get("*"), supported.get("swap"), supported.get("dup"), supported.get("*"), supported.get("+"), supported.get("sqrt"));
        hypotenuse.exportOperation();
    }

    /*      Private method used to verify the operation's result      */
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
    /*------------------------------------------------------------------*/

}
