package Operations;

import Stack.ObservableStack;
import VariablesManager.VariablesStorage;
import org.apache.commons.math3.complex.Complex;

/**
 * This class is an abstraction for VariablesOperation
 * @author gparrella
 */
public abstract class VariablesOperation extends SupportedOperation {

    private final String variableName;
    private final VariablesStorage variableManager;

    /**
     * Create a new VariablesOperation, must be called by subclasses.
     * @param variableName is the name of the variable
     * @param variableManager is the variable manager
     * @param name is the opertation's name
     * @param stack is the stack used
     */
    public VariablesOperation(String variableName, VariablesStorage variableManager, OperationsEnum name, ObservableStack<Complex> stack) {
        super(name, stack);
        this.variableName = variableName;
        this.variableManager = variableManager;
    }

    /**
     * Return the value of a variable previously saved, starting from its
     * @return a string that is variable name
     */
    public String getVariableName() {
        return variableName;
    }

    /**
     * Get the variable's value.
     * @return a complex number that is variable value
     */
    public Complex getVariableValue() {
        return this.variableManager.getVariableValue(variableName);
    }

    /**
     * Combine a value to variable, starting from the variable and the value
     * @param number is the complex number 
     */
    public void save(Complex number) {
        this.variableManager.save(this.variableName, number);
    }

    /**
     * Subtract passed <code>number</code> to this variable
     * @param number is the complex number to subtract
     */
    public void subFromVariable(Complex number) {
        this.variableManager.subFromVariable(variableName, number);
    }

    /**
     * Sum passed <code>number</code> to this variable
     * @param number is the complex number to sum
     */
    public void addToVariable(Complex number) {
        this.variableManager.addToVariable(variableName, number);
    }
}
