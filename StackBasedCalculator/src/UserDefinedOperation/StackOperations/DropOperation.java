package UserDefinedOperation.StackOperations;

import UserDefinedOperation.OperationsEnum;
import UserDefinedOperation.SupportedOperation;

/**
 *
 * @author Speranza
 */
public class DropOperation extends SupportedOperation {

    
    public DropOperation() {
        super(OperationsEnum.DROP);
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
