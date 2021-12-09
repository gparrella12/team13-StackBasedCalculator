package Operations.StateOperation;

import ArchiveModule.Archive;
import Operations.Operation;
import Operations.OperationsEnum;

/**
 * This class implements the restore state operation.
 *
 * @author gparrella
 */
public class RestoreStateOperation implements Operation {

    private final OperationsEnum name;
    private final Archive variablesState;

    /**
     * Creates a new RestoreStateOperation.
     *
     * @param variablesState the archive in which you want to save the state of
     * the variables
     */
    public RestoreStateOperation(Archive variablesState) {
        this.name = OperationsEnum.RESTORE_STATE;
        this.variablesState = variablesState;
    }

    /**
     * Execute the operation.
     */
    @Override
    public void execute() {
        variablesState.restoreState();
    }

    /**
     * Returns a string that represent this operation
     *
     * @return a string that represent this operation
     */
    @Override
    public String toString() {
        return this.name.toString();
    }

}
