package UserDefinedOperation;

/**
 *
 * @author gparrella
 */
public class ClearOperation extends SupportedOperation{

    public ClearOperation() {
        super(OperationsEnum.CLEAR);
    }
    /**
     * Clear all stack's elements.
     */
    @Override
    public void execute() {
        super.clear();
    }

}
