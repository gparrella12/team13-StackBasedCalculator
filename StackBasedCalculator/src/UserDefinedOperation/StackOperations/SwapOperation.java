/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserDefinedOperation.StackOperations;

import UserDefinedOperation.OperationsEnum;
import UserDefinedOperation.SupportedOperation;

/**
 *
 * @author Speranza
 */
public class SwapOperation extends SupportedOperation {

    public SwapOperation() {
        super(OperationsEnum.SWAP);
    }

    /**
     *  Swaps the last two elements into the stack.
     * 
     */
    @Override
    public void execute() {
        super.swap();
    }

}
