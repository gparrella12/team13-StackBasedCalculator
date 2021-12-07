package UserDefinedOperation.BasicOperations;

import UserDefinedOperation.OperationsEnum;
import UserDefinedOperation.SupportedOperation;
import java.util.NoSuchElementException;
import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author fsonnessa
 */
public class InvertSignOperation extends SupportedOperation{

    public InvertSignOperation() {
        super(OperationsEnum.INVSIGN);
    }

    /**
     * Inverts the sign of on top number (top = top * -1)
     *
     * @throws NoSuchElementException
     */
    @Override
    public void execute() {
        if (super.size() < 1) {
            throw new NoSuchElementException("The super is empty");
        }
        Complex num = super.pop();
        super.push(num.negate());
    }
    
}
