package Operations.StackOperations;

import Stack.ObservableStack;
import Operations.OperationsEnum;
import Operations.SupportedOperation;
import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author Speranza
 */
public class DupOperation extends SupportedOperation {


    public DupOperation(ObservableStack<Complex> stack) {
        super(OperationsEnum.DUP, stack);
    }

    /**
     * Duplicates the last element into the stack, if there is at least one element.
     * 
     */
    @Override
    public void execute() {
        super.dup();

    }

}
