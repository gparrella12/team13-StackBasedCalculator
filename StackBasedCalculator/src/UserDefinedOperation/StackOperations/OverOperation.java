package UserDefinedOperation.StackOperations;

import Stack.ObservableStack;
import Operations.OperationsEnum;
import Operations.SupportedOperation;
import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author Speranza
 */
public class OverOperation extends SupportedOperation {

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
