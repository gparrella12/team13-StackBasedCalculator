package Operations.StackOperations;

import Stack.ObservableStack;
import Operations.OperationsEnum;
import Operations.SupportedOperation;
import org.apache.commons.math3.complex.Complex;

/**
 * This class implements "Over" operation for stack.
 *
 * @author Speranza
 */
public class OverOperation extends SupportedOperation {

    /**
     * Create a new OverOperation
     *
     * @param stack is the stack on which you want copy the second last element
     */
    public OverOperation(ObservableStack<Complex> stack) {
        super(OperationsEnum.OVER, stack);
    }

    /**
     * Copies the second last element at the top of the stack.
     *
     */
    @Override
    public void execute() {
        super.over();
    }

}
