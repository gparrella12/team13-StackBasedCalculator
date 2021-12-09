package Operations;

import Stack.ObservableStack;
import VariablesManager.VariablesStorage;
import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author gparrella
 */
public abstract class VariablesOperation extends SupportedOperation {

    private final String variableName;
    private final VariablesStorage variableManager;

    public VariablesOperation(String variableName, VariablesStorage variableManager, OperationsEnum name, ObservableStack<Complex> stack) {
        super(name, stack);
        this.variableName = variableName;
        this.variableManager = variableManager;
    }

    public String getVariableName() {
        return variableName;
    }

    public Complex getVariableValue() {
        return this.variableManager.getVariableValue(variableName);
    }

    public void save(Complex number) {
        this.variableManager.save(this.variableName, number);
    }

    public void subFromVariable(Complex number) {
        this.variableManager.subFromVariable(variableName, number);
    }

    public void addToVariable(Complex number) {
        this.variableManager.addToVariable(variableName, number);
    }
}
