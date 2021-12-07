/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserDefinedOperation.StackOperations;

import Stack.ObservableStack;
import Operations.OperationsEnum;
import Operations.SupportedOperation;
import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author fsonnessa
 */
public class ClearOperation extends SupportedOperation{

    public ClearOperation(ObservableStack<Complex> stack) {
        super(OperationsEnum.CLEAR, stack);
    }
    
    /**
     * Clear all the elements of the stack
     */
    @Override
    public void execute() {
        super.clear();
    }
    
}
