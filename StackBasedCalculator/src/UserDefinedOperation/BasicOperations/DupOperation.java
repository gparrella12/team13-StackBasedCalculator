package UserDefinedOperation.BasicOperations;

import UserDefinedOperation.OperationsEnum;
import UserDefinedOperation.SupportedOperation;

/**
 *
 * @author Speranza
 */
public class DupOperation extends SupportedOperation {


    public DupOperation() {
        super(OperationsEnum.DUP);
    }

    /**
     * Duplicates the last element into the stack, if there is at least one element.
     * 
     */
    @Override
    public void execute() {
        super.dup();

    }

}
