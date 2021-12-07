package UserDefinedOperation.BasicOperation;

import UserDefinedOperation.OperationsEnum;
import UserDefinedOperation.SupportedOperation;
import java.util.NoSuchElementException;
import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author fsonnessa
 */
public class DivisionOperation extends SupportedOperation {

    public DivisionOperation() {
        super(OperationsEnum.DIVISION);
    }

    /**
     * Divides the first two elements of the super and save the result on top.
     * This operation has fixed order of operands: the second element is the
     * left operant while the first element (top element) is the right operand
     * (top = (top-1) / top)
     *
     * @throws NoSuchElementException
     */
    @Override
    public void execute() {
        if (super.size() < 2) {
            throw new NoSuchElementException("There are less then two elements in the super");
        }

        if (super.top().equals(new Complex(0))) {
            throw new ArithmeticException("MATH ERROR");
        }

        Complex num1 = super.pop();
        Complex num2 = super.pop();

        super.push(num2.divide(num1));
    }

}
