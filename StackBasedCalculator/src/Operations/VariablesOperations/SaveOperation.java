package Operations.VariablesOperations;

import Stack.ObservableStack;
import Operations.OperationsEnum;
import Operations.SupportedOperation;
import VariablesManager.VariablesStorage;
import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author fsonnessa
 */
public class SaveOperation extends SupportedOperation {

    private final VariablesStorage variableManager;
    private final String variableName;

    public SaveOperation(ObservableStack<Complex> stack, VariablesStorage variableManager, String variableName) {
        super(OperationsEnum.SAVE, stack);
        this.variableManager = variableManager;
        this.variableName = variableName;
    }

    @Override
    public void execute() {
        this.variableManager.save(this.variableName, super.top());
        super.drop();
    }

    @Override
    public String toString() {
        return super.getName().substring(0, 1) + variableName;
    }
    
    
}
