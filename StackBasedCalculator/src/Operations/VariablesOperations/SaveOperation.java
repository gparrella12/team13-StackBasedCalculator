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
public class SaveOperation extends VariablesOperation {

    public SaveOperation(String variableName, VariablesStorage variableManager, ObservableStack<Complex> stack) {
        super(variableName, variableManager, OperationsEnum.SAVE, stack);
    }

    @Override
    public void execute() {
        super.save(super.top());
        super.drop();
    }

    @Override
    public String toString() {
        return super.getName().substring(0, 1) + super.getVariableName();
    }

}
