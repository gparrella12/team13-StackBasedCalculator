package UserDefinedOperation;

import VariablesManager.VariablesStorage;

/**
 *
 * @author gparrella
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
