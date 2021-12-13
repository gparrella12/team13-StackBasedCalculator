package Operations.StackOperations;

import Stack.ObservableStack;
import Operations.OperationsEnum;
import Operations.SupportedOperation;
import org.apache.commons.math3.complex.Complex;

/**
 * This class implements "Swap" command in the calculator.
 *
 * @author Speranza
 */
public class SwapOperation extends SupportedOperation {

    /**
     * Create a new SwapOperation
     * @param stack is the stack on which you want to swap the two last element
     */
    public SwapOperation(ObservableStack<Complex> stack) {
        super(OperationsEnum.SWAP, stack);
    }

    /**
     * Swaps the last two elements into the stack.
     *
     */
    @Override
    public void execute() {
        super.swap();
    }

}
