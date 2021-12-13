package Operations.StackOperations;

import Stack.ObservableStack;
import Operations.OperationsEnum;
import Operations.SupportedOperation;
import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author fsonnessa
 */
public class ClearOperation extends SupportedOperation{

    public ClearOperation(ObservableStack<Complex> stack) {
        super(OperationsEnum.CLEAR, stack);
    }
    
    /**
     * Clear all the elements of the stack
     */
    @Override
    public void execute() {
        super.clear();
    }
    
}
