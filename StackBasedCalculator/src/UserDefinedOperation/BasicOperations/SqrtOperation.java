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
public class SqrtOperation extends SupportedOperation {

    public SqrtOperation(ObservableStack<Complex> stack) {
        super(OperationsEnum.SQRT, stack);
    }

    /**
     * Makes the root of on top element (top = sqrt(top))
     *
     * @throws NoSuchElementException
     */
    @Override
    public void execute() {
        if (super.size() < 1) {
            throw new NoSuchElementException("The stack is empty");
        }
        Complex num = super.pop();
        super.push(num.sqrt());
    }

}
