package UserDefinedOperation.VariablesOperations;

import ArchiveModule.Archive;
import Operations.OperationsEnum;
import Operations.SupportedOperation;
import VariablesManager.VariablesStorage;

/**
 *
 * @author gparrella
 */
public class RestoreStateOperation extends SupportedOperation {

    private final Archive variablesState;
    private final VariablesStorage variableManager;

    public RestoreStateOperation(Archive variablesState, VariablesStorage variableManager) {
        super(OperationsEnum.RESTORE_STATE, null);
        this.variablesState = variablesState;
        this.variableManager = variableManager;
    }

    @Override
    public void execute() {
        variablesState.restoreState();
    }

    @Override
    public String toString() {
        return "restore";
    }
    
    

}
