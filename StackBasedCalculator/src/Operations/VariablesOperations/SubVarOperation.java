package Operations.VariablesOperations;

import Stack.ObservableStack;
import Operations.OperationsEnum;
import Operations.SupportedOperation;
import VariablesManager.VariablesStorage;
import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author Speranza
 */
public class SubVarOperation extends SupportedOperation {

    private final VariablesStorage variableManager;
    private final String variableName;

    public SubVarOperation(ObservableStack<Complex> stack,VariablesStorage variableManager, String variableName) {
        super(OperationsEnum.SUB_VAR, stack);
        this.variableManager = variableManager;
        this.variableName = variableName;
    }

    /**
     * Subtracts the top of the stack to a stored variable value.
     * 
     */
    @Override
    public void execute() {
        this.variableManager.subFromVariable(this.variableName, super.top());
    }

    @Override
    public String toString() {
        return super.getName().substring(0, 1) + variableName;
    }
    
    

}
