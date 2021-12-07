package UserDefinedOperation.VariablesOperations;

import UserDefinedOperation.OperationsEnum;
import UserDefinedOperation.SupportedOperation;
import VariablesManager.VariablesStorage;

/**
 *
 * @author fsonnessa
 */
public class SaveOperation extends SupportedOperation {

    private final VariablesStorage variableManager;
    private final String variableName;

    public SaveOperation(VariablesStorage variableManager, String variableName) {
        super(OperationsEnum.SAVE);
        this.variableManager = variableManager;
        this.variableName = variableName;
    }

    @Override
    public void execute() {
        this.variableManager.save(this.variableName, super.top());
        super.drop();
    }
}
