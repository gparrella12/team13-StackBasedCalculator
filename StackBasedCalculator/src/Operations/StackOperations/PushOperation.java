package Operations.StackOperations;

import Operations.OperationsEnum;
import Operations.SupportedOperation;
import Stack.ObservableStack;
import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author gparrella
 */
public class PushOperation extends SupportedOperation{
    private Complex number;

    public PushOperation(ObservableStack<Complex> stack,Complex number) {
        super(OperationsEnum.PUSH, stack);
        this.number = number;
    }

    @Override
    public void execute() {
        super.push(number);
    }

    @Override
    public String toString() {
        return "push: "+ number.getReal() +  ' ' + number.getImaginary()+"j";
    }
    
    

}