package UserDefinedOperation;

import MainMathOperation.RPNSolver;
import java.util.List;

/**
 *
 * @author gparrella
 */
public class ArithmeticOperation extends SupportedOperation {

    // The following constant can be used in the constructor to avoid manually insert of operation
    public static final String SUM = "+";
    public static final String PRODUCT = "*";
    public static final String DIVISION = "/";
    public static final String SUBTRACTION = "-";
    public static final String INVSIGN = "+-";
    public static final String SQRT = "sqrt";
    private static List<String> operationList = List.of(SUM, PRODUCT, DIVISION, SUBTRACTION, INVSIGN, SQRT);
    // An instance of RPNSolver is used to execute arithmetic operation
    private RPNSolver rpn;

    /**
     *
     * @param name
     */
    public ArithmeticOperation(String name) {
        if (!operationList.contains(name)) {
            throw new UnsupportedOperationException("Operation " + name + " not supported as arithmetical operation");
        }
        super.setName(name);
        this.rpn = RPNSolver.getInstance();
    }

    public ArithmeticOperation(RPNSolver rpn, String name) {
        this(name);
        this.rpn = rpn;
    }

    /**
     *
     */
    @Override
    public void execute() {
        switch (this.getName()) {
            case SUM -> {
                rpn.sum();
                return;
            }
            case SUBTRACTION -> {
                rpn.subtraction();
                return;
            }
            case "*" -> {
                rpn.product();
                return;
            }
            case DIVISION -> {
                rpn.division();
                return;
            }
            case SQRT -> {
                rpn.sqrt();
                return;
            }
            case INVSIGN -> {
                rpn.invertSign();
                return;
            }
            default -> {
                throw new UnsupportedOperationException("Arithmetic Operation " + this.getName() + " with invalid name: Not supported");
            }
        }
    }
}
