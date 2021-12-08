package Operations.StackOperations;

import Stack.ObservableStack;
import Operations.OperationsEnum;
import Operations.SupportedOperation;
import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author Speranza
 */
public class SwapOperation extends SupportedOperation {

    public SwapOperation(ObservableStack<Complex> stack) {
        super(OperationsEnum.SWAP,stack);
    }

    /**
     *  Swaps the last two elements into the stack.
     * 
     */
    @Override
    public void execute() {
        super.swap();
    }

}
