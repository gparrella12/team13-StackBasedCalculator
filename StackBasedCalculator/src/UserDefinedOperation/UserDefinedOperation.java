package UserDefinedOperation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * The class provide an implementation of a user-defined operation. A
 * user-defined operation should contain supported operation and also previus
 * user-defined operation.
 *
 * @author gparrella
 */
public class UserDefinedOperation implements Operation {

    private String name;
    private int requiredOperands;
    private List<Operation> operationList;

    /**
     * Create a user-defined operation given its name, the number of required
     * operands, and the operations that compose it.
     *
     * @param name is the operation's name
     * @param requiredOperands is the number of operands required to this
     * operation
     * @param operations the operation that compose this user defined operation
     */
    public UserDefinedOperation(String name, int requiredOperands, Operation... operations) {
        this.name = name;
        this.requiredOperands = requiredOperands;
        this.operationList = new ArrayList<>();
        this.operationList.addAll(Arrays.asList(operations));
    }

    /**
     * Execute this user-defined operation.
     */
    @Override
    public void execute() {
        this.operationList.forEach(op -> {
            op.execute();
        });
    }

    /**
     * Return a string that contains operation's name.
     *
     * @return a string with operation's name
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * A hashcode for this operation, based on its name.
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.name);
        return hash;
    }

    /**
     * Compare two user-defined operation.
     *
     * @param obj other user-defined operation
     * @return <code> true </code> if the two user-defined operation have the
     * same name, <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserDefinedOperation other = (UserDefinedOperation) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    /**
     * Get the operation's name.
     *
     * @return a string that is the operation's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the operation's name
     *
     * @param name is the name of this operation
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the required operands for this operation
     *
     * @return the required operands of user-defined operation
     */
    public int getRequiredOperands() {
        return requiredOperands;
    }

    /**
     * Set the required operands for this operation
     *
     * @param requiredOperands
     */
    public void setRequiredOperands(int requiredOperands) {
        this.requiredOperands = requiredOperands;
    }

    /**
     * Update the operation list
     * @param operations is the new list of operation of user-defined operation
     */
    public void updateList(Operation... operations) {
        this.operationList.clear();
        this.operationList.addAll(Arrays.asList(operations));
    }
    /**
     *
     */
    public void exportOperation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
