package Operations.UserDefinedTest;

import ArchiveModule.ArchivableState;
import ArchiveModule.Archive;
import Operations.Operation;
import Operations.OperationsEnum;
import Operations.UserDefinedOperation;
import Stack.ObservableStack;
import UserInterface.Parser.Parser;
import UserInterface.Parser.ParserEnum;
import UserInterface.Parser.ParserFactory;
import UserInterface.SimpleFactoryCommand;
import VariablesManager.VariablesStorage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexFormat;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fsonnessa
 */
public class VariablesOperationUserDefinedTest {

    private ObservableStack<Complex> stack;
    private VariablesStorage variableManager;
    private SimpleFactoryCommand commandCreator;
    private Archive archive;

    private Random generator;
    private List<Complex> myOperands = null;

    public VariablesOperationUserDefinedTest() {
        // Initialize structures
        this.stack = new ObservableStack<>();
        this.variableManager = new VariablesStorage();
        this.archive = new Archive(variableManager);
        this.commandCreator = new SimpleFactoryCommand(stack, variableManager);
        this.commandCreator.setArchive(archive);

        // Initialize utilis structures
        this.generator = new Random(113131646);
        this.myOperands = List.of(new Complex(2, 3), new Complex(-4, 7), new Complex(0, 0.66), new Complex(45, 6), new Complex(-66, -0.235), new Complex(2, -3), new Complex(-2, 3), new Complex(0, -3));
    }

    /**
     * Return a list of Operations from a string that raprresents them
     *
     * @param instructions String of operations
     * @return List of Operations
     */
    public List getOperationsList(String instructions) {
        List<Operation> instr = new ArrayList<>();
        Parser pv = new ParserFactory().getParser(ParserEnum.VARIABLE);

        String variableOp;
        String[] instructionsString = instructions.split(" ");
        for (String opr : instructionsString) {
//            System.out.print("Check: " + opr);
            variableOp = pv.check(opr);

            if (variableOp != null) {
//                System.out.println(" = Variable Operation");
                String varOperation = variableOp.substring(0, 1);
                String variable = variableOp.substring(1);
                this.commandCreator.setOperation(OperationsEnum.valueOfString(varOperation + "var"));
                this.commandCreator.setVariableName(variable);
            } else if (Character.isDigit(opr.charAt(0))) {
                Complex num = new ComplexFormat().parse(new ParserFactory().getParser(ParserEnum.COMPLEXNUMBER).check(opr));
//                System.out.println(" = Insert " + num);
                this.commandCreator.setNumber(num);
                this.commandCreator.setOperation(OperationsEnum.PUSH);
            } else {
//                System.out.println(" = Not Variable Operation");
                this.commandCreator.setOperation(OperationsEnum.valueOfString(opr));
            }
            instr.add(this.commandCreator.pickCommand());
        }

        return List.copyOf(instr);
    }

    /**
     * Populates the class's stack with a given number of elements
     *
     * @param elementsNum number of elements to put in the stack
     * @return An ArrayList<Complex> of stack's elements sorted by insertion
     */
    public ArrayList<Complex> randomlyPopulateStack(int elementsNum) {
        ArrayList<Complex> choice = new ArrayList<>();
        Complex num;
        if (myOperands != null && stack != null && elementsNum != 0) {
            for (int i = 0; i < elementsNum; i++) {
                num = myOperands.get(generator.nextInt(myOperands.size()));
                stack.push(num);
                choice.add(num);
            }
        }
//        System.out.println("\n### STACK ###");
//        for (Complex x : choice){
//            System.out.println(x);
//        }
//        System.out.println("### stack ###\n");
        return choice;
    }

    /**
     * Test execution of an UserDefine operation with VariablesOperation
     */
    @Test
    public void testHypothenuse() {
        System.out.println("\n# Test Hypothenuse");
        // Populate stack
        ArrayList<Complex> operands = randomlyPopulateStack(2);
        // Saving variables' initial state
        ArchivableState initState = variableManager.getCurrentState();
        // Getting the instructions list
        List<Operation> instructions = getOperationsList("save >b >a <a <a * <b <b * + sqrt restore");
        System.out.println("List: " + instructions);
        UserDefinedOperation hypothenuse = new UserDefinedOperation("hypothenuse", 2, instructions);

        hypothenuse.execute();

        Complex result = hypothenuse(operands);
        assertEquals(result, stack.top());
        System.out.println("Expected " + result + "\nActuals " + stack.top());

        assertEquals(initState.getElement(), variableManager.getCurrentState().getElement());
    }
    
    /**
     * Test execution with a wrong definition of the operation 
     * Ex. Wrong number of needed operands
     */
    @Test(expected = Exception.class)
    public void testHypothenuseException() {
        System.out.println("\n# Test Hypothenuse - wrong definition");

        List<Operation> instructions = getOperationsList("save >b >a <a <a * <b <b * + sqrt restore");
        System.out.println("List: " + instructions);
        UserDefinedOperation hypothenuse = new UserDefinedOperation("hypothenuse", 1, instructions);

        hypothenuse.execute();
    }
    
    /**
     * Function to check the correctness of hypotenuse UserDefineOperation
     * @param operands ArrayList<Complex> of operands used in hypotenuse UserDefineOperation
     * @return Correct result for hypotenuse operation
     */
    public Complex hypothenuse(ArrayList<Complex> operands) {
        Complex a = operands.remove(0);
        Complex b = operands.remove(0);

        return a.multiply(a).add(b.multiply(b)).sqrt();
    }

    // ### New UserDefineOperation Tests ####
    
    @Test
    public void test2degree() {
        System.out.println("\n# Test solve2degree");
        ArrayList<Complex> operands = randomlyPopulateStack(3);
        ArchivableState initState = variableManager.getCurrentState();

        Complex[] result = solve2degree(operands);
        
        // Calculate first result
        List<Operation> resutl1 = getOperationsList("save >c >b >a <b <b * 4 <a <c * * - sqrt >d <b +- <d - 2 <a * /");
        System.out.println("List: " + resutl1);
        UserDefinedOperation solve2degree = new UserDefinedOperation("solve2degree", 3, resutl1);
        
        solve2degree.execute();
        
        System.out.println("Result1 - Expected " + result[0] + "\nActuals " + stack.top());
        assertEquals(result[0], stack.top());
        
        // Calculate second result
        List<Operation> resutl2 = getOperationsList("<b +- <d + 2 <a * / restore");
        System.out.println("List: " + resutl2);
        solve2degree = new UserDefinedOperation("solve2degree", 3, resutl2);
        
        solve2degree.execute();
        
        System.out.println("Result2 - Expected " + result[1] + "\nActuals " + stack.top());
        assertEquals(result[1], stack.top());
        
        // Check state restoring
        assertEquals(initState.getElement(), variableManager.getCurrentState().getElement());
    }

    @Test(expected = Exception.class)
    public void test2degreeException() {
        System.out.println("\n# Test solve2degree - wrong definition");
        randomlyPopulateStack(2);
        UserDefinedOperation solve2degree = new UserDefinedOperation("solve2degree", 3, getOperationsList("save >c >b >a <b <b * 4 <a <c * * - sqrt >d <b +- <d - 2 <a * /"));
        solve2degree.execute();
    }

    /**
     * Function to check the correctness of solve2degree UserDefineOperation
     * @param operands ArrayList<Complex> of operands used in solve2degree UserDefineOperation
     * @return Correct result for second degree equation (solve2degree) operation
     */
    public Complex[] solve2degree(ArrayList<Complex> operands) {
        Complex a = operands.remove(0);
        Complex b = operands.remove(0);
        Complex c = operands.remove(0);

        Complex delta = b.multiply(b).subtract(a.multiply(c).multiply(4)).sqrt();
        Complex delta1 = b.negate().subtract(delta);
        Complex delta2 = b.negate().add(delta);

        Complex[] result = {delta1.divide(a.multiply(2)), delta2.divide(a.multiply(2))};

        return result;
    }

}
