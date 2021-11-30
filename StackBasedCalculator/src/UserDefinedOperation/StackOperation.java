package UserDefinedOperation;

import MainMathOperation.RPNSolver;
import java.util.List;
import org.apache.commons.math3.complex.Complex;

/**
 * This class implement a Stack Operation supported by the calculator. The stack's
 * operation are executed using an instance of RPNSolver class in order to
 * execute the operation in the calculator.
 * 
 * @author gparrella
 */
public class StackOperation extends SupportedOperation {

    /* The following constant can be used in the constructor to avoid manually insert of operation */
    /**
     * 
     */
    public static final String CLEAR = "clear";

    /**
     * Public constant for clear operation.
     */
    public static final String DROP = "drop";

    /**
     * Public constant for drop operation.
     */
    public static final String DUP = "dup";

    /**
     * Public constant for swap operation.
     */
    public static final String SWAP = "swap";

    /**
     * Public constant for over operation.
     */
    public static final String OVER = "over";

    /**
     * Public constant for push operation.
     */
    public static final String PUSH = "push";
    private static List<String> operationList = List.of(CLEAR, DROP, DUP, SWAP, OVER, PUSH);
// An instance of RPNSolver is used to execute arithmetic operation
    private final RPNSolver rpn;
    private final Complex number = null;

    /**
     * Create a StackOperation given its name and an rpn instance to execute it.
     * @param name the operation's name
     * @param rpn the rpn istance that execute this operation
     * @throws UnsupportedOperationException if the name of operation is not valid
     */
    public StackOperation(String name, RPNSolver rpn) {
        this.chechOperation(name);
        this.rpn = rpn;
        super.setName(name);
    }

    /**
     * Create a StackOperation given its name, an rpn instance to execute it and a complex number.
     * The complex number is used in case of push operation.
     * 
     * @param name the operation's name
     * @param rpn the rpn istance that execute this operation
     * @param number the complex number pushed in the stack if this is a PUSH operation
     * @throws UnsupportedOperationException if the name of operation is not valid
     * @throws NumberFormatException if <code>number</code> is <code>null</code>
     */
    public StackOperation(String name, RPNSolver rpn, Complex number) {
        this.chechOperation(name);
        if (name.equals(PUSH) && number == null) {
            throw new NumberFormatException("Null pointer for complex operand");
        }
        this.rpn = rpn;
        super.setName(name);
    }

    /**
     * Execute the stack operation, using the rpn.
     * @throws UnsupportedOperationException if the operation is not supported
     * @throws NumberFormatException if this is a push operation with <code>null</code> number.
     */
    @Override
    public void execute() {
        switch (this.getName()) {
            case CLEAR -> {
                rpn.clear();
                return;
            }
            case DUP -> {
                rpn.dup();
                return;
            }
            case DROP -> {
                rpn.drop();
                return;
            }
            case SWAP -> {
                rpn.swap();
                return;
            }
            case OVER -> {
                rpn.over();
                return;
            }
            case PUSH -> {
                if(number == null){
                    throw new NumberFormatException("Unable to insert complex - Null pointer");
                }
                rpn.addNum(this.number);
                return;
            }
            default -> {
                throw new UnsupportedOperationException("Stack Operation " + this.getName() + " with invalid name: Not supported");
            }
        }
    }

    private void chechOperation(String name) {
        if (!operationList.contains(name)) {
            throw new UnsupportedOperationException("Operation " + name + " not supported as stack operation");
        }
    }

}
