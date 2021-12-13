package Operations.StackOperations;

import Operations.OperationsEnum;
import Operations.SupportedOperation;
import Stack.ObservableStack;
import org.apache.commons.math3.complex.Complex;

/**
 * This class implements push command in the calculator.
 *
 * @author gparrella
 */
public class PushOperation extends SupportedOperation {

    private final Complex number;

    /**
     * Create a new PushOperation
     * @param stack the stack in which you want to insert the new number
     * @param number the number to insert in the stack
     */
    public PushOperation(ObservableStack<Complex> stack, Complex number) {
        super(OperationsEnum.PUSH, stack);
        this.number = number;
    }

    /**
     * Insert the number into the stack.
     */
    @Override
    public void execute() {
        super.push(number);
    }

    /**
     * Return a string representation of this operation
     * @return a string representation of this operation
     */
    @Override
    public String toString() {
        return ((number != null) ? "push: " + number.getReal() + ' ' + number.getImaginary() + "j" : "push");
    }

}
