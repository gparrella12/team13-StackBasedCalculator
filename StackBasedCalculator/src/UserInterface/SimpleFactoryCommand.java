package UserInterface;

import Operations.*;
import Stack.ObservableStack;
import UserDefinedOperation.BasicOperations.*;
import UserDefinedOperation.StackOperations.*;
import UserDefinedOperation.VariablesOperations.*;
import VariablesManager.VariablesStorage;
import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author gparrella
 */
public class SimpleFactoryCommand {

    private ObservableStack<Complex> stack;
    private VariablesStorage variablesManager;
    private OperationsEnum operation;
    private String variableName;
    private Complex number;

    public SimpleFactoryCommand(ObservableStack<Complex> stack, VariablesStorage variablesManager, OperationsEnum operation) {
        this.stack = stack;
        this.variablesManager = variablesManager;
        this.operation = operation;
    }

    public SimpleFactoryCommand(ObservableStack<Complex> stack, VariablesStorage variablesManager) {
        this.stack = stack;
        this.variablesManager = variablesManager;
    }

    public SimpleFactoryCommand(ObservableStack<Complex> stack, VariablesStorage variablesManager, OperationsEnum operation, String variableName) {
        this.stack = stack;
        this.variablesManager = variablesManager;
        this.operation = operation;
        this.variableName = variableName;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public void setOperation(OperationsEnum operation) {
        this.operation = operation;
    }

    public void setNumber(Complex number) {
        this.number = number;
    }

    public SupportedOperation pickCommand() {
        switch (operation) {
            case SUM -> {
                return new SumOperation(this.stack);
            }
            case SUBTRACTION -> {
                return new SubtractionOperation(this.stack);
            }
            case PRODUCT -> {
                return new ProductOperation(this.stack);
            }
            case DIVISION -> {
                return new DivisionOperation(this.stack);
            }
            case SQRT -> {
                return new SqrtOperation(this.stack);
            }
            case INVSIGN -> {
                return new InvertSignOperation(this.stack);
            }
            case CLEAR -> {
                return new ClearOperation(this.stack);
            }
            case DROP -> {
                return new DropOperation(this.stack);
            }
            case DUP -> {
                return new DupOperation(this.stack);
            }
            case OVER -> {
                return new OverOperation(this.stack);
            }
            case SWAP -> {
                return new SwapOperation(this.stack);
            }
            case LOAD -> {
                return new LoadOperation(this.stack, this.variablesManager, this.variableName);
            }
            case SAVE -> {
                return new SaveOperation(this.stack, this.variablesManager, this.variableName);
            }
            case SUM_VAR -> {
                return new SumVarOperation(this.stack, this.variablesManager, this.variableName);
            }
            case SUB_VAR -> {
                return new SubVarOperation(this.stack, this.variablesManager, this.variableName);
            }
            case PUSH -> {
                return new PushOperation(this.stack, this.number);
            }
        }
        return null;
    }

}
