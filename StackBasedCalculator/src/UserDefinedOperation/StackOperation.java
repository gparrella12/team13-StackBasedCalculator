package UserDefinedOperation;

import MainMathOperation.RPNSolver;
import java.util.List;

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
    // An instance of RPNSolver is used to execute arithmetic operation
    private static List<String> operationList = List.of(CLEAR, DROP, DUP, SWAP, OVER);

    private RPNSolver rpn;

    public StackOperation(String name) {
        if (!operationList.contains(name)) {
            throw new UnsupportedOperationException("Operation " + name + " not supported as stack operation");
        }
        super.setName(name);
        this.rpn = RPNSolver.getInstance();
    }

    public StackOperation(String name, RPNSolver rpn) {
        this(name);
        this.rpn = rpn;
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
            default -> {
                throw new UnsupportedOperationException("Stack Operation " + this.getName() + " with invalid name: Not supported");
            }
        }
    }

}
