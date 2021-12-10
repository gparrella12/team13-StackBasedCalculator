package Operations;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The class provides an implementation of a user-defined operation.
 * <p>
 * A user-defined operation should contains supported operations and also
 * previous user-defined operations.
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
     * Create a user-defined operation given its name, the number of required
     * operands, and the operations that compose it.
     *
     * @param name is the operation's name
     * @param requiredOperands is the number of operands required to this
     * operation
     * @param operationList the operation that compose this user defined
     * operation
     */
    public UserDefinedOperation(String name, int requiredOperands, List<Operation> operationList) {
        this.name = name;
        this.requiredOperands = requiredOperands;
        this.operationList = operationList;
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
     * Compare two user-defined operations.
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
     *
     * @param operations is the new list of operation of user-defined operation
     */
    public void updateList(Operation... operations) {
        this.operationList.clear();
        this.operationList.addAll(Arrays.asList(operations));
    }

    /**
     * Check if an operation is included in this User-Defined Operation. If
     * there are more nested user-defined operation in this operation, then the
     * function return true if the operation passed as parameter is included in
     * this or in at least one nested user-defined operation in this.
     *
     * @param operation the operation to check
     * @return <code>true</code> if the operation passed as parameter is
     * included in this User-Defined operations, <code>false</code> otherwise.
     */
    public boolean contains(Operation operation) {
        // Check if operation op is included in this operation
        for (Operation op : this.operationList) {
            // If op is in this operationList, then return true
            if (operation.equals(op)) {
                return true;
            } else if (op instanceof UserDefinedOperation) {
                /* If op is an UserDefinedOperation, check if operation is included in its operationList:
                -If operation is contained in op's operationList, then return true
                -Otherwise, the search continue
                 */
                UserDefinedOperation nested = ((UserDefinedOperation) op);
                boolean goOut = nested.contains(operation);
                if (goOut) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Export this operation in a file.
     * @param filename is the filename
     * @throws java.io.IOException if there are some problems in writing the file
     */
    public void exportOperation(String filename) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(this.name + "\n");
        writer.write(String.valueOf(this.requiredOperands) + "\n");
        for (Operation op : this.operationList) {
            writer.write(op.toString() + "\n");
        }
        writer.close();

    }
}
