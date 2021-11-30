package UserDefinedOperation;

import MainMathOperation.RPNSolver;
import java.util.List;
import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author gparrella
 */
public class StackOperation extends SupportedOperation {

    // The following constant can be used in the constructor to avoid manually insert of operation
    public static final String CLEAR = "clear";
    public static final String DROP = "drop";
    public static final String DUP = "dup";
    public static final String SWAP = "swap";
    public static final String OVER = "over";
    public static final String PUSH = "push";
    private static List<String> operationList = List.of(CLEAR, DROP, DUP, SWAP, OVER, PUSH);
// An instance of RPNSolver is used to execute arithmetic operation
    private RPNSolver rpn;
    private Complex number = null;

    public StackOperation(String name, RPNSolver rpn) {
        super(name);
        this.chechOperation(name);
        super.setName(name);
    }

    public StackOperation(String name, RPNSolver rpn, Complex number) {
        super(name);
        this.chechOperation(name);
        if (name.equals(PUSH) && number == null) {
            throw new NumberFormatException("Null pointer for complex operand");
        }
        this.rpn = rpn;
        super.setName(name);
    }

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
