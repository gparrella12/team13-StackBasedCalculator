package UserDefinedOperation.VariablesOperations;

import UserDefinedOperation.OperationsEnum;
import UserDefinedOperation.SupportedOperation;
import VariablesManager.VariablesStorage;

/**
 *
 * @author Speranza
 */
public class SubVarOperation extends SupportedOperation {

    private final VariablesStorage variableManager;
    private final String variableName;

    public SubVarOperation(VariablesStorage variableManager, String variableName) {
        super(OperationsEnum.SUB_VAR);
        this.variableManager = variableManager;
        this.variableName = variableName;
    }

    /**
     * Subtracts the top of the stack to a stored variable value.
     * 
     */
    @Override
    public void execute() {
        super.push(this.variableManager.getVariableValue(this.variableName));
    }

}
