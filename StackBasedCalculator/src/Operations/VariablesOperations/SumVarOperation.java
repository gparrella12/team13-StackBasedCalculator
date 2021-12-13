package Operations.VariablesOperations;

import Stack.ObservableStack;
import Operations.OperationsEnum;
import Operations.SupportedOperation;
import Operations.VariablesOperation;
import VariablesManager.VariablesStorage;
import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author Speranza
 */
public class SumVarOperation extends VariablesOperation {

    public SumVarOperation(String variableName, VariablesStorage variableManager, ObservableStack<Complex> stack) {
        super(variableName, variableManager, OperationsEnum.SUM_VAR, stack);
    }

    /**
     * Sums the top of the stack to a stored variable value.
     *
     */
    @Override
    public void execute() {
        super.addToVariable(super.top());
    }

    @Override
    public String toString() {
        return super.getName().substring(0, 1) + super.getVariableName();
    }
}
