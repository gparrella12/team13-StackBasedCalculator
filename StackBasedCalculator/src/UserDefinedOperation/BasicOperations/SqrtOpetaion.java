package UserDefinedOperation.BasicOperations;

import UserDefinedOperation.OperationsEnum;
import UserDefinedOperation.SupportedOperation;
import java.util.NoSuchElementException;
import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author fsonnessa
 */
public class SqrtOpetaion extends SupportedOperation {

    public SqrtOpetaion() {
        super(OperationsEnum.SQRT);
    }

    /**
     * Makes the root of on top element (top = sqrt(top))
     *
     * @throws NoSuchElementException
     */
    @Override
    public void execute() {
        if (super.size() < 1) {
            throw new NoSuchElementException("The super is empty");
        }
        Complex num = super.pop();
        super.push(num.sqrt());
    }

}
