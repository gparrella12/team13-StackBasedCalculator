package UserDefinedOperation.StackOperations;

import Stack.ObservableStack;
import Operations.OperationsEnum;
import Operations.SupportedOperation;
import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author Speranza
 */
public class DropOperation extends SupportedOperation {

    
    public DropOperation(ObservableStack<Complex> stack) {
        super(OperationsEnum.DROP, stack);
    }

    /**
     * Removes the last element of the stack, if there is at least one element.
     * 
     */
    @Override
    public void execute() {
        super.drop();
    }

}
