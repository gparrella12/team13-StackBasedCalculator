package Operations.UserDefinedTest;

import Operations.Operation;
import Operations.OperationsEnum;
import Operations.UserDefinedOperation;
import Stack.ObservableStack;
import UserInterface.SimpleFactoryCommand;
import VariablesManager.VariablesStorage;
import java.util.HashMap;
import java.util.Random;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.util.Precision;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class to check the correct definition and execution of nested user
 * defined operations.
 *
 * @author Speranza
 */
public class NestedUserDefinedOperationTest {

    private final HashMap<String, UserDefinedOperation> newOperations;
    private final HashMap<String, Operation> supportedOp;
    private VariablesStorage variableManager;
    private ObservableStack<Complex> stack;
    private SimpleFactoryCommand commandCreator;
    private Operation operation;

    private final int NUM_TESTS;

    /**
     * Constructor.
     */
    public NestedUserDefinedOperationTest() {

        //number of tests that will be executed on a single custom operation
        NUM_TESTS = 5;

        // Initializes the structures
        this.variableManager = new VariablesStorage();
        this.variableManager = new VariablesStorage();
        this.stack = new ObservableStack<>();
        this.commandCreator = new SimpleFactoryCommand(stack, variableManager);

        //Inizializes supported operations
        supportedOp = new HashMap<>();
        for (OperationsEnum op : OperationsEnum.values()) {
            this.commandCreator.setOperation(op);
            supportedOp.put(op.toString(), this.commandCreator.pickCommand());
        }

        newOperations = new HashMap<>();

    }

    /**
     * Test of execute method, of class UserDefinedOperation for nested
     * operations.
     */
    @Test
    public void testExecute() {

        System.out.println("Execute - Nested User Defined Operations");

        //starting operation
        UserDefinedOperation add2operands = new UserDefinedOperation("add2operands", 2,
                supportedOp.get("+"));
        newOperations.put("add2operands", add2operands);

        //first nested operation
        UserDefinedOperation add4operands = new UserDefinedOperation("add4operands", 4,
                add2operands, add2operands, add2operands);
        newOperations.put("add4operands", add4operands);

        //second nested operation
        UserDefinedOperation add6operands = new UserDefinedOperation("add6operands", 6,
                add4operands, add2operands, add2operands);
        newOperations.put("add6operands", add6operands);

        for (Operation op : newOperations.values()) {
            for (int i = 0; i < NUM_TESTS; i++) {
                stack.clear();
                Random generator = new Random();
                Complex op1 = new Complex(generator.nextDouble(), generator.nextDouble());
                Complex op2 = new Complex(generator.nextDouble(), generator.nextDouble());
                Complex op3 = new Complex(generator.nextDouble(), generator.nextDouble());
                Complex op4 = new Complex(generator.nextDouble(), generator.nextDouble());
                Complex op5 = new Complex(generator.nextDouble(), generator.nextDouble());
                Complex op6 = new Complex(generator.nextDouble(), generator.nextDouble());

                stack.push(op1);
                stack.push(op2);
                stack.push(op3);
                stack.push(op4);
                stack.push(op5);
                stack.push(op6);

                op.execute();

                switch (op.toString()) {
                    case "add2operands" -> {
                        //result obtained after the execution of the private method
                        Complex result = new Complex(Precision.round(
                                this.sum2operands(op6, op5).getReal(), 10),
                                Precision.round(this.sum2operands(op6, op5).getImaginary(), 10));
                        //result obtained after the execution of the user defined operation
                        Complex myResult = new Complex(Precision.round(stack.top().getReal(), 10),
                                Precision.round(stack.top().getImaginary(), 10));

                        assertEquals("Invalid result ", result, myResult);
                    }
                    case "add4operands" -> {

                        Complex result = new Complex(Precision.round(
                                this.sum4operands(op6, op5, op4, op3).getReal(), 10),
                                Precision.round(this.sum4operands(op6, op5, op4, op3).getImaginary(), 10));

                        Complex myResult = new Complex(Precision.round(stack.top().getReal(), 10),
                                Precision.round(stack.top().getImaginary(), 10));

                        assertEquals("Invalid result ", result, myResult);
                    }
                    case "add6operands" -> {

                        Complex result = new Complex(Precision.round(
                                this.sum6operands(op6, op5, op4, op3, op2, op1).getReal(), 10),
                                Precision.round(this.sum6operands(op6, op5, op4, op3, op2, op1).getImaginary(), 10));

                        Complex myResult = new Complex(Precision.round(stack.top().getReal(), 10),
                                Precision.round(stack.top().getImaginary(), 10));

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
     * Test of execute method, of class UserDefinedOperation for nested
     * operations when it's used an invalid operation.
     */
    @Test(expected = NullPointerException.class)
    public void testExecuteInvalidOperations() {

        System.out.println("Execute - invalid operations");

        stack.clear();

        Random generator = new Random();

        stack.push(new Complex(generator.nextDouble(), generator.nextDouble()));
        stack.push(new Complex(generator.nextDouble(), generator.nextDouble()));
        stack.push(new Complex(generator.nextDouble(), generator.nextDouble()));

        // base user defined operation
        UserDefinedOperation add2operands = new UserDefinedOperation("add2operands", 2,
                supportedOp.get("+"));
        newOperations.put("add2operands", add2operands);

        // nested user defined operation with an invalid operation inside
        UserDefinedOperation add3operands = new UserDefinedOperation("add3operands", 3,
                add2operands, supportedOp.get("NotExists"));
        newOperations.put("add3operands", add3operands);

        //user defined operation with an invalid nested operation
        UserDefinedOperation perimeter = new UserDefinedOperation("perimeter", 1,
                add3operands);
        newOperations.put("perimeter", perimeter);
        
        operation = newOperations.get("add3operands");
        operation.execute();
        
        operation = newOperations.get("perimeter");
        operation.execute();

    }

    /**
     * Test of execute method, of class UserDefinedOperation for nested
     * operations when it's performed the division by zero.
     */
    @Test(expected = ArithmeticException.class)
    public void testExecuteInvalidOperands() {

        System.out.println("Execute - invalid operands");

        stack.clear();

        stack.push(new Complex(0));
        stack.push(new Complex(-4, 7));
        stack.push(new Complex(2, 3));

        // base user defined operation
        UserDefinedOperation add2operands = new UserDefinedOperation("add2operands", 2,
                supportedOp.get("+"));
        newOperations.put("add2operands", add2operands);

        UserDefinedOperation hypotenuse = new UserDefinedOperation("hypotenuse", 2,
                supportedOp.get("dup"), supportedOp.get("*"), supportedOp.get("swap"),
                supportedOp.get("dup"), supportedOp.get("*"), add2operands,
                supportedOp.get("sqrt"), supportedOp.get("swap"));
        newOperations.put("hypotenuse", hypotenuse);

        // nested user defined operation with an invalid operation inside
        UserDefinedOperation divisionByZero = new UserDefinedOperation("divisionByZero", 3,
                hypotenuse, supportedOp.get("/"));
        newOperations.put("divisionByZero", divisionByZero);
        
        operation = newOperations.get("divisionByZero");
        operation.execute();

    }

    /*      Private methods used to verify the operation's result      */
    
    private Complex sum2operands(Complex c1, Complex c2) {

        Complex myOp1 = new Complex(c1.getReal(), c1.getImaginary());
        Complex myOp2 = new Complex(c2.getReal(), c2.getImaginary());

        return myOp1.add(myOp2);
    }

    private Complex sum4operands(Complex c1, Complex c2, Complex c3, Complex c4) {

        Complex myOp1 = new Complex(c1.getReal(), c1.getImaginary());
        Complex myOp2 = new Complex(c2.getReal(), c2.getImaginary());
        Complex myOp3 = new Complex(c3.getReal(), c3.getImaginary());
        Complex myOp4 = new Complex(c4.getReal(), c4.getImaginary());

        return myOp1.add(myOp2).add(myOp3).add(myOp4);
    }

    private Complex sum6operands(Complex c1, Complex c2, Complex c3, Complex c4,
            Complex c5, Complex c6) {

        Complex myOp1 = new Complex(c1.getReal(), c1.getImaginary());
        Complex myOp2 = new Complex(c2.getReal(), c2.getImaginary());
        Complex myOp3 = new Complex(c3.getReal(), c3.getImaginary());
        Complex myOp4 = new Complex(c4.getReal(), c4.getImaginary());
        Complex myOp5 = new Complex(c5.getReal(), c5.getImaginary());
        Complex myOp6 = new Complex(c6.getReal(), c6.getImaginary());

        return myOp1.add(myOp2).add(myOp3).add(myOp4).add(myOp5).add(myOp6);
    }

    /*------------------------------------------------------------------*/
}
