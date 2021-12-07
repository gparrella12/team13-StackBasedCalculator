package UserDefinedOperation.VariablesOperations;

import UserDefinedOperation.OperationsEnum;
import UserDefinedOperation.SupportedOperation;
import VariablesManager.VariablesStorage;

/**
 *
 * @author Speranza
 */
public class SumVarOperation extends SupportedOperation {

    private final VariablesStorage variableManager;
    private final String variableName;

    public SumVarOperation(VariablesStorage variableManager, String variableName) {
        super(OperationsEnum.SUM_VAR);
        this.variableManager = variableManager;
        this.variableName = variableName;
    }

    /**
     * Sums the top of the stack to a stored variable value.
     *
     */
    @Override
    public void execute() {
        super.push(this.variableManager.getVariableValue(this.variableName));
    }

}
