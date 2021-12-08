package Operations.VariablesOperations;

import ArchiveModule.Archive;
import Operations.OperationsEnum;
import Operations.SupportedOperation;
import VariablesManager.VariablesStorage;

/**
 *
 * @author gparrella
 */
public class SaveStateOperation extends SupportedOperation{
    private Archive variablesState;
    private VariablesStorage variableManager;

    public SaveStateOperation(Archive variablesState, VariablesStorage variableManager) {
        super(OperationsEnum.SAVE_STATE, null);
        this.variablesState = variablesState;
        this.variableManager = variableManager;
    }
    
    
    @Override
    public void execute() {
        variablesState.saveState();
    }

    @Override
    public String toString() {
        return "save";
    }
    
    

}
