package UserDefinedOperation.BasicOperation;

import UserDefinedOperation.OperationsEnum;
import UserDefinedOperation.SupportedOperation;
import java.util.NoSuchElementException;
import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author fsonnessa
 */
public class ProductOperation extends SupportedOperation{

    public ProductOperation() {
        super(OperationsEnum.PRODUCT);
    }
    
    /**
     * Multiply the first two elements of the stack and save the result on top 
     * (top = top * (top-1))
     *
     * @throws NoSuchElementException
     */
    @Override
    public void execute() {
        if (super.size() < 2) {
            throw new NoSuchElementException("There are less then two elements in the super");
        }

        Complex num1 = super.pop();
        Complex num2 = super.pop();

        super.push(num1.multiply(num2));
    }
    
}
