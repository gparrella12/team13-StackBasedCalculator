package UserDefinedOperation.BasicOperations;

import UserDefinedOperation.OperationsEnum;
import UserDefinedOperation.SupportedOperation;

/**
 *
 * @author Speranza
 */
public class OverOperation extends SupportedOperation {

    public OverOperation() {
        super(OperationsEnum.OVER);
    }

    /**
     * Copies the second last element at the top of the stack.
     * 
     */
    @Override
    public void execute() {
        super.over();
    }

}
