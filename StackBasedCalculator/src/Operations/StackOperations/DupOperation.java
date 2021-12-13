package Operations.StackOperations;

import Stack.ObservableStack;
import Operations.OperationsEnum;
import Operations.SupportedOperation;
import org.apache.commons.math3.complex.Complex;

/**
 * This class implements "Dup" operation for stack.
 *
 * @author Speranza
 */
public class DupOperation extends SupportedOperation {

    /**
     * Create a new DupOperation
     *
     * @param stack is the stack on which you want to duplicate che last element
     */
    public DupOperation(ObservableStack<Complex> stack) {
        super(OperationsEnum.DUP, stack);
    }

    /**
     * Duplicates the last element into the stack, if there is at least one
     * element.
     *
     */
    @Override
    public void execute() {
        super.dup();

    }

}
