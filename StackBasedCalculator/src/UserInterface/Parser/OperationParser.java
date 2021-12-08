package UserInterface.Parser;

import Operations.OperationsEnum;

/**
 * This is parser that checks if an inserted operation is corrected
 *
 * @author Ernesto
 */
public class OperationParser implements Parser {
    /**
     * Return a String object corresponding to the operation
     *
     * @param operation is the name of the operation
     * @return the String associated with the operation
     */
    @Override
    public String check(String operation) {

        String parsedOperation;
        /* try {
            parsedOperation = String.valueOf(OperationsEnum.valueOfString(operation));
        } catch (UnsupportedOperationException ex) {
            parsedOperation = null;
        }
        
        if (parsedOperation!= null && !parsedOperation.equals(String.valueOf(OperationsEnum.PUSH)))
        return parsedOperation;
        
        return null;*/
        for (OperationsEnum op : OperationsEnum.userInvokable()) {
            if (op.toString().equals(operation)) {
                return operation;
            }
        }
        return null;
    }

}
