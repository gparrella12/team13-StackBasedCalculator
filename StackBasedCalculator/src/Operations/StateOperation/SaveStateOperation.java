package Operations.StateOperation;

import ArchiveModule.Archive;
import Operations.*;

/**
 * This class implements the save state operation.
 *
 * @author gparrella
 */
public class SaveStateOperation implements Operation {

    private final OperationsEnum name;
    private final Archive variablesState;

    /**
     * Creates a new RestoreStateOperation.
     *
     * @param variablesState the archive from which you want to restore the
     * state of the variables
     */
    public SaveStateOperation(Archive variablesState) {
        this.name = OperationsEnum.SAVE_STATE;
        this.variablesState = variablesState;
    }

    /**
     * Execute the operation.
     */
    @Override
    public void execute() {
        variablesState.saveState();
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
