package UserDefinedOperation;

import MainMathOperation.RPNSolver;
import static UserDefinedOperation.VariableOperation.ADD;
import static UserDefinedOperation.VariableOperation.LOAD;
import static UserDefinedOperation.VariableOperation.SAVE;
import static UserDefinedOperation.VariableOperation.SUBTRACT;
import VariablesManager.VariablesStorage;
import java.util.List;
import org.apache.commons.math3.complex.Complex;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class test the VariableOperation feature. In this case, we test only the
 * correct call to RPNSolver and VariablesStorage method, that are already
 * tested.
 *
 * @author gparrella
 */
public class VariableOperationTest {

    private final RPNSolver rpn = RPNSolver.getInstance();
    private final VariablesStorage variableStorage = new VariablesStorage();
    private final List<String> operationList = List.of(SAVE, LOAD, ADD, SUBTRACT);

    public VariableOperationTest() {
    }

    /**
     * Test of execute method, of class VariableOperation.
     */
    @Test
    public void testExecute() {
        System.out.println("Execute Variable Operation <- Correct instances");
        Complex c = new Complex(1, 1);
        for (String op : operationList) {
            rpn.addNum(c);
            VariableOperation instance = new VariableOperation(variableStorage, "a", rpn, op);
            variableStorage.save("a", new Complex(1, -9));
            instance.execute();
            switch (instance.getName()) {
                case SAVE -> {
                    assertEquals("Error in save method call", c, variableStorage.getVariableValue("a"));
                    assertEquals("Error in save method call - Invalid size", 0, rpn.getStackSize());
                }
                case LOAD -> {
                    assertEquals("Error in load method call", new Complex(1, -9), rpn.getAns());
                    assertEquals("Error in load method call - Invalid size", 2, rpn.getStackSize());
                }
                case ADD -> {
                    assertEquals("Error in add method call", variableStorage.getVariableValue("a"), new Complex(2, -8));
                    assertEquals("Error in add method call - Invalid size", 1, rpn.getStackSize());
                }
                case SUBTRACT -> {
                    assertEquals("Error in subtract method call", variableStorage.getVariableValue("a"), new Complex(0, -10));
                    assertEquals("Error in subtract method call - Invalid size", 1, rpn.getStackSize());
                }
            }
            //Reset the stack
            rpn.clear();
            System.out.println("\t" + instance.getName() + " -> OK");
        }

    }

    /**
     * Test of execute method, of class VariableOperation.
     */
    @Test
    public void testExecuteWithError() {
        System.out.println("Execute Variable Operation <- Incorrect instances");
        String[] operations = {"<<", "<>", "-+", "*/", "->", "<-<"};
        Complex c = new Complex(1, 1);
        for (String op : operations) {
            rpn.addNum(c);
            try {
                VariableOperation instance = new VariableOperation(variableStorage, "a", rpn, op);
                fail(op + " accepted!");
            } catch (UnsupportedOperationException ex) {
                System.out.println("\t" + op + " not accepted -> OK");
            }

        }
    }

}
