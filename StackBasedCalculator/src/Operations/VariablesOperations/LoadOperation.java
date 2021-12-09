package Operations.VariablesOperations;

import Stack.ObservableStack;
import Operations.OperationsEnum;
import Operations.VariablesOperation;
import VariablesManager.VariablesStorage;
import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author fsonnessa
 */
public class LoadOperation extends VariablesOperation {

    public LoadOperation(String variableName, VariablesStorage variableManager,ObservableStack<Complex> stack) {
        super(variableName, variableManager, OperationsEnum.LOAD, stack);
    }

    @Override
    public void execute() {
        super.push(super.getVariableValue());
    }

    @Override
    public String toString() {
        return super.getName().substring(0, 1) + super.getVariableName();
    }
    
    

}
