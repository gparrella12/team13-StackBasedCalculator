package UserDefinedOperation;

import MainMathOperation.RPNSolver;
import java.util.List;

/**
 * The class provide an implementation of Arithmetic Operation supported by the calculator. The arithmetic
 * operation are executed using an instance of RPNSolver class in order to
 * execute the operation in the calculator.
 *
 * @author gparrella
 * @see MainMathOperation.RPNSolver
 */
public class ArithmeticOperation extends SupportedOperation {

    /* The following constant can be used in the constructor to avoid manually insert of operation */
    /**
     * Public constant for sum operation.
     */
    public static final String SUM = "+";

    /**
     * Public constant for product operation.
     */
    public static final String PRODUCT = "*";

    /**
     * Public constant for division operation.
     */
    public static final String DIVISION = "/";

    /**
     * Public constant for sum subtraction.
     */
    public static final String SUBTRACTION = "-";

    /**
     * Public constant for sum invert sign operation.
     */
    public static final String INVSIGN = "+-";

    /**
     * Public constant for Square Root operation.
     */
    public static final String SQRT = "sqrt";
    private static final List<String> operationList = List.of(SUM, PRODUCT, DIVISION, SUBTRACTION, INVSIGN, SQRT);
    // An instance of RPNSolver is used to execute arithmetic operation
    private final RPNSolver rpn;

    /**
     * Create an arithmetic operation given its name and a RPNSolver element to
     * execute it.
     *
     * @param rpn is the rpn used to execute the operation
     * @param name is the operation name.
     * @throws UnsupportedOperationException if the name of operation is not valid
     */
    public ArithmeticOperation(String name, RPNSolver rpn) {
        super(name);
        this.checkOperation(name);
        this.rpn = rpn;
    }

    /**
     * Execute the arithmetic operation using the RPNSolver instance.
     */
    @Override
    public void execute() {
        /* In this case, we only invoke the relative method of rpn solver */
        switch (super.getName()) {
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
                throw new UnsupportedOperationException("Arithmetic Operation " + super.getName() + " with invalid name: Not supported");
            }
        }
    }

    private void checkOperation(String name) {
        if (!operationList.contains(name)) {
            throw new UnsupportedOperationException("Operation " + name + " not supported as arithmetical operation");
        }
    }
}
