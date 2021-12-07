package UserDefinedOperation;

import MainMathOperation.RPNSolver;
import VariablesManager.VariablesStorage;
import java.util.List;

/**
 * This class implements a Variable Operation supported by the calculator. The
 * stack's operations are executed using an instance of RPNSolver class in order
 * to execute the operations in the calculator, while the variable operations are
 * executed using an instance of variable manager in the calculator.
 * @author gparrella
 * @see VariablesManager.VariablesStorage
 */
public class VariableOperation extends SupportedOperation {

    private final VariablesStorage variableManager;
    private String variableName;
    private final RPNSolver rpn;
    /* The following constants can be used in the constructor to avoid manually insert of operation */
    /**
     * Constant for saving operator, ">".
     */
    public static final String SAVE = ">";

    /**
     * Constant for loading operator, "<".
     */
    public static final String LOAD = "<";

    /**
     * Constant for add operator, "+".
     */
    public static final String ADD = "+";

    /**
     * Constant for subtract operator, "-".
     */
    public static final String SUBTRACT = "-";
    private static final List<String> operationList = List.of(SAVE, LOAD, ADD, SUBTRACT);

    /**
     * Create a VariableOperation
     *
     * @param variableManager is the manager of variable in the calculator
     * @param variableName is the name of the variable involved in this
     * operation
     * @param rpn is the rpn solver that manages the stack in the calculator
     * @param operationName is the name of the operation, it can be *
     * "-","+","<",">"
     */
    public VariableOperation(VariablesStorage variableManager, String variableName, RPNSolver rpn, OperationsEnum name) {
        super(name);
        this.variableManager = variableManager;
        this.variableName = variableName;
        this.rpn = rpn;
    }

    /**
     * Execute this variable operation.
     */
    @Override
    public void execute() {
        switch (super.getName()) {
            case SAVE -> {
                this.variableManager.save(this.variableName, rpn.getAns());
                rpn.drop();
                return;
            }
            case SUBTRACT -> {
                this.variableManager.subFromVariable(this.variableName, rpn.getAns());
                return;
            }
            case ADD -> {
                this.variableManager.addToVariable(this.variableName, rpn.getAns());
                return;
            }
            case LOAD -> {
                rpn.addNum(this.variableManager.getVariableValue(this.variableName));
                return;
            }
            default -> {
                throw new UnsupportedOperationException("Variable Operation " + super.getName() + " with invalid name: Not supported");
            }
        }
    }

    private void checkOperation(String name) {
        if (!operationList.contains(name)) {
            throw new UnsupportedOperationException("Operation " + name + " not supported as variable operation");
        }
    }

    /**
     * Set the name of the variable
     *
     * @param variableName the variable's name
     */
    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }
    

    /**
     * Return a string that represent this operation.
     *
     * @return a string that represent this operation
     */
    @Override
    public String toString() {
        return super.toString() + ((this.variableName == null) ? "varName" : this.variableName);
    }

}
