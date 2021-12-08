package UserDefinedOperation.BasicOperations;

import Stack.ObservableStack;
import Operations.OperationsEnum;
import Operations.SupportedOperation;
import java.util.NoSuchElementException;
import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author fsonnessa
 */
public class InvertSignOperation extends SupportedOperation{

    public InvertSignOperation(ObservableStack<Complex> stack) {
        super(OperationsEnum.INVSIGN, stack);
    }

    /**
     * Inverts the sign of on top number (top = top * -1)
     *
     * @throws NoSuchElementException
     */
    @Override
    public void execute() {
        if (super.size() < 1) {
            throw new NoSuchElementException("The stack is empty");
        }
        Complex num = super.pop();
        super.push(num.negate());
    }
    
}
