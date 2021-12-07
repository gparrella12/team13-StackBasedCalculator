package UserDefinedOperation.VariablesOperations;

import UserDefinedOperation.OperationsEnum;
import UserDefinedOperation.SupportedOperation;
import VariablesManager.VariablesStorage;

/**
 *
 * @author fsonnessa
 */
public class LoadOperation extends SupportedOperation {

    private final VariablesStorage variableManager;
    private final String variableName;

    public LoadOperation(VariablesStorage variableManager, String variableName) {
        super(OperationsEnum.LOAD);
        this.variableManager = variableManager;
        this.variableName = variableName;
    }

    @Override
    public void execute() {
        super.push(this.variableManager.getVariableValue(this.variableName));
    }

}
