package Operations.StackOperations;

import Stack.ObservableStack;
import Operations.OperationsEnum;
import Operations.SupportedOperation;
import org.apache.commons.math3.complex.Complex;

/**
 * This class implements "Drop" operation for stack.
 * @author Speranza
 */
public class DropOperation extends SupportedOperation {

    /**
     * Create a new DropOperation
     * @param stack is the stack on which you want to remove che last element
     */
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
