package UserInterface;

import ArchiveModule.Archive;
import Operations.*;
import Stack.ObservableStack;
import UserDefinedOperation.BasicOperations.*;
import UserDefinedOperation.StackOperations.*;
import UserDefinedOperation.VariablesOperations.*;
import VariablesManager.VariablesStorage;
import org.apache.commons.math3.complex.Complex;

/**
 * This class implements a simple factory for commands of the calculator.
 *
 * @author gparrella
 */
public class SimpleFactoryCommand {

    private final ObservableStack<Complex> stack;
    private final VariablesStorage variablesManager;
    private OperationsEnum operation;
    private String variableName;
    private Complex number;
    private Archive archive;

    /**
     * Create a new SimpleFactoryCommand
     *
     * @param stack the stack on which the commands operate
     * @param variablesManager the variable manager on which the commands
     * operate
     * @param operation the desired operation
     */
    public SimpleFactoryCommand(ObservableStack<Complex> stack, VariablesStorage variablesManager, OperationsEnum operation) {
        this.stack = stack;
        this.variablesManager = variablesManager;
        this.operation = operation;
    }

    /**
     * Create a new SimpleFactoryCommand
     *
     * @param stack the stack on which the commands operate
     * @param variablesManager the variable manager on which the commands
     * operate
     */
    public SimpleFactoryCommand(ObservableStack<Complex> stack, VariablesStorage variablesManager) {
        this.stack = stack;
        this.variablesManager = variablesManager;
    }

    /**
     * Get the variable's name stored actually in the factory.
     *
     * @return a string that represents the variableName stored in the factory
     */
    public String getVariableName() {
        return variableName;
    }

    /**
     * Set the variable's name stored actually in the factory.
     *
     * @param variableName the new variableName
     */
    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    /**
     * Set the operation stored actually in the factory.
     *
     * @param operation the new desired operation
     */
    public void setOperation(OperationsEnum operation) {
        this.operation = operation;
    }

    /**
     * Set the complex number stored actually in the factory.
     *
     * @param number the complex number to be saved in the factory
     */
    public void setNumber(Complex number) {
        this.number = number;
    }

    /**
     * Set the archive in the factory for command to save variables's state.
     * @param archive the archive used
     */
    public void setArchive(Archive archive) {
        this.archive = archive;
    }

    /**
     * Pick the desired command. the command that will be returned will depend
     * on the value of the operation present in the factory, the complex number,
     * and the name of the variable previously set.
     *
     * @return the desired command
     */
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
            case SAVE_STATE -> {
                return new SaveStateOperation(archive, variablesManager);
            }
            case RESTORE_STATE -> {
                return new RestoreStateOperation(archive, variablesManager);
            }
        }
        return null;
    }

}
