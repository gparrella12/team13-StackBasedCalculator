package Operations.UserDefinedTest;

import Operations.Operation;
import Operations.OperationsEnum;
import Operations.UserDefinedOperation;
import Stack.ObservableStack;
import UserInterface.SimpleFactoryCommand;
import VariablesManager.VariablesStorage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.apache.commons.math3.complex.Complex;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * This class tests the stack and the arithmetics operations
 *
 * @author ermancusi
 */
public class StackAndArithmeticOperationUserDefined {

    //Declare the util objects
    private final HashMap<String, Operation> supported;
    private VariablesStorage variableManager;
    private List<Complex> myOperands;
    private ObservableStack<Complex> stack;
    private SimpleFactoryCommand commandCreator;
    private final HashMap<String, UserDefinedOperation> myOperations;
    private int numberOfTest;

    public StackAndArithmeticOperationUserDefined() {
        //Initialize the util objects
        this.variableManager = new VariablesStorage();
        this.myOperands = new ArrayList<>();
        this.variableManager = new VariablesStorage();
        this.stack = new ObservableStack<>();
        this.commandCreator = new SimpleFactoryCommand(stack, variableManager);
        this.supported = new HashMap<>();
        this.myOperations = new HashMap<>();
        numberOfTest = 5;

        //Initialize the structure that contains the operations
        for (OperationsEnum op : OperationsEnum.values()) {
            this.commandCreator.setOperation(op);
            supported.put(op.toString(), this.commandCreator.pickCommand());
        }
        //Prepare the user defined operations, containing stack operations, that will test
        UserDefinedOperation clear = new UserDefinedOperation("clear", 1, supported.get("clear"));
        UserDefinedOperation drop = new UserDefinedOperation("drop", 1, supported.get("drop"));
        UserDefinedOperation swap = new UserDefinedOperation("swap", 2, supported.get("swap"));
        UserDefinedOperation over = new UserDefinedOperation("over", 2, supported.get("over"));
        UserDefinedOperation dup = new UserDefinedOperation("dup", 1, supported.get("dup"));

        //Prepare the user defined operations, containing arithmetics operations, that will test
        UserDefinedOperation addition = new UserDefinedOperation("addition", 2, supported.get("+"));
        UserDefinedOperation subtraction = new UserDefinedOperation("subtraction", 2, supported.get("-"));
        UserDefinedOperation multiplication = new UserDefinedOperation("multiplication", 2, supported.get("*"));
        UserDefinedOperation division = new UserDefinedOperation("division", 2, supported.get("/"));
        UserDefinedOperation squareRoot = new UserDefinedOperation("squareRoot", 1, supported.get("sqrt"));
        UserDefinedOperation invertSign = new UserDefinedOperation("invertSign", 1, supported.get("+-"));

        myOperations.put("clear", clear);
        myOperations.put("drop", drop);
        myOperations.put("swap", swap);
        myOperations.put("over", over);
        myOperations.put("dup", dup);

        myOperations.put("+", addition);
        myOperations.put("-", subtraction);
        myOperations.put("*", multiplication);
        myOperations.put("/", division);
        myOperations.put("sqrt", squareRoot);
        myOperations.put("+-", invertSign);

    }

    /**
     * Test of a clear operation inserted in a user defined operation
     */
    @Test
    public void testClear() {
        for (int i = 0; i < numberOfTest; i++) {
            Random generator = new Random();
            stack.push(new Complex(generator.nextDouble(), generator.nextDouble()));
            stack.push(new Complex(generator.nextDouble(), generator.nextDouble()));
            Operation op = myOperations.get("clear");
            op.execute();
            assertEquals("Invalid operation!", true, stack.isEmpty());
        }
    }

    /**
     * Test of a drop operation inserted in a user defined operation
     */
    @Test
    public void testDrop() {
        for (int i = 0; i < numberOfTest; i++) {
            Random generator = new Random();
            stack.push(new Complex(generator.nextDouble(), generator.nextDouble()));
            stack.push(new Complex(generator.nextDouble(), generator.nextDouble()));
            Operation op = myOperations.get("drop");
            op.execute();
            assertEquals("Invalid operation!", 1 + i, stack.size());
        }
    }

    /**
     * Test of a swap operation inserted in a user defined operation
     */
    @Test
    public void testSwap() {
        for (int i = 0; i < numberOfTest; i++) {
            Random generator = new Random();
            Complex c1 = new Complex(generator.nextDouble(), generator.nextDouble());
            Complex c2 = new Complex(generator.nextDouble(), generator.nextDouble());
            stack.push(c1);
            stack.push(c2);
            Operation op = myOperations.get("swap");
            op.execute();
            assertEquals("Invalid operation!", c1, stack.top());
        }

        for (int i = 0; i < numberOfTest; i++) {
            Random generator = new Random();
            Complex c1 = new Complex(generator.nextDouble(), generator.nextDouble());
            Complex c2 = new Complex(generator.nextDouble(), generator.nextDouble());
            stack.push(c1);
            stack.push(c2);
            Operation op = myOperations.get("swap");
            op.execute();
            assertEquals("Invalid operation!", c1, stack.pop());
            assertEquals("Invalid operation!", c2, stack.pop());
        }
    }

    /**
     * Test of an over operation inserted in a user defined operation
     */
    @Test
    public void testOver() {
        for (int i = 0; i < numberOfTest; i++) {
            Random generator = new Random();
            Complex c1 = new Complex(generator.nextDouble(), generator.nextDouble());
            Complex c2 = new Complex(generator.nextDouble(), generator.nextDouble());
            stack.push(c1);
            stack.push(c2);
            Operation op = myOperations.get("over");
            op.execute();
            assertEquals("Invalid operation!", c1, stack.top());
            //at each iteration 3 elements are added
            assertEquals("Invalid operation!", 3 * i + 3, stack.size());

        }
    }

    /**
     * Test of a dup operation inserted in a user defined operation
     */
    @Test
    public void testDup() {
        for (int i = 0; i < numberOfTest; i++) {
            Random generator = new Random();
            Complex c1 = new Complex(generator.nextDouble(), generator.nextDouble());
            Complex c2 = new Complex(generator.nextDouble(), generator.nextDouble());
            stack.push(c1);
            stack.push(c2);
            Operation op = myOperations.get("dup");
            op.execute();
            assertEquals("Invalid operation!", c2, stack.top());
            //at each iteration 3 elements are added
            assertEquals("Invalid operation!", 3 * i + 3, stack.size());

        }
    }

    /**
     * Test of a sum operation inserted in a user defined operation
     */
    @Test
    public void testSum() {
        for (int i = 0; i < numberOfTest; i++) {
            Random generator = new Random();
            Complex c1 = new Complex(generator.nextDouble(), generator.nextDouble());
            Complex c2 = new Complex(generator.nextDouble(), generator.nextDouble());
            Complex c3 = c1.add(c2);
            stack.push(c1);
            stack.push(c2);
            Operation op = myOperations.get("+");
            op.execute();
            assertEquals("Invalid operation!", c3, stack.top());
        }
    }

    /**
     * Test of a subtraction operation inserted in a user defined operation
     */
    @Test
    public void testSubtraction() {
        for (int i = 0; i < numberOfTest; i++) {
            Random generator = new Random();
            Complex c1 = new Complex(generator.nextDouble(), generator.nextDouble());
            Complex c2 = new Complex(generator.nextDouble(), generator.nextDouble());
            Complex c3 = c1.subtract(c2);
            stack.push(c1);
            stack.push(c2);
            Operation op = myOperations.get("-");
            op.execute();
            assertEquals("Invalid operation!", c3, stack.top());
        }
    }

    /**
     * Test of a multiplication operation inserted in a user defined operation
     */
    @Test
    public void testMultiplication() {
        for (int i = 0; i < numberOfTest; i++) {
            Random generator = new Random();
            Complex c1 = new Complex(generator.nextDouble(), generator.nextDouble());
            Complex c2 = new Complex(generator.nextDouble(), generator.nextDouble());
            Complex c3 = c1.multiply(c2);
            stack.push(c1);
            stack.push(c2);
            Operation op = myOperations.get("*");
            op.execute();
            assertEquals("Invalid operation!", c3, stack.top());
        }

    }

    /**
     * Test of a division operation inserted in a user defined operation
     */
    @Test
    public void testDivision() {
        for (int i = 0; i < numberOfTest; i++) {
            Random generator = new Random();
            Complex c1 = new Complex(generator.nextDouble(), generator.nextDouble());
            Complex c2 = new Complex(generator.nextDouble(), generator.nextDouble());
            Complex c3 = c1.divide(c2);
            stack.push(c1);
            stack.push(c2);
            Operation op = myOperations.get("/");
            op.execute();
            assertEquals("Invalid operation!", c3, stack.top());
        }
    }

    /**
     * Test of a square root operation inserted in a user defined operation
     */
    @Test
    public void testSquareRoot() {
        for (int i = 0; i < numberOfTest; i++) {
            Random generator = new Random();
            Complex c1 = new Complex(generator.nextDouble(), generator.nextDouble());
            Complex c3 = c1.sqrt();
            stack.push(c1);
            Operation op = myOperations.get("sqrt");
            op.execute();
            assertEquals("Invalid operation!", c3, stack.top());
        }

    }

    /**
     * Test of an invert sign operation inserted in a user defined operation
     */
    @Test
    public void testInvertSign() {
        for (int i = 0; i < numberOfTest; i++) {
            Random generator = new Random();
            Complex c1 = new Complex(generator.nextDouble(), generator.nextDouble());
            Complex c3 = c1.negate();
            stack.push(c1);
            Operation op = myOperations.get("+-");
            op.execute();
            assertEquals("Invalid operation!", c3, stack.top());
        }

    }

}
