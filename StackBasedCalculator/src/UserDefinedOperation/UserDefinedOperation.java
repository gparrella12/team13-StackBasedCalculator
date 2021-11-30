package UserDefinedOperation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author gparrella
 */
public class UserDefinedOperation implements Operation {

    private String name;
    private int requiredOperands;
    private List<Operation> operationList;

    /**
     *
     * @param name
     * @param requiredOperands
     * @param operations
     */
    public UserDefinedOperation(String name, int requiredOperands, String... operations) {
        this.name = name;
        this.requiredOperands = requiredOperands;
        this.operationList = new ArrayList<>();
        for (String operation : operations) {
            Operation op = new BasicOperation(operation);
            this.operationList.add(op);
        }
    }

    /**
     *
     * @param name
     * @param requiredOperands
     * @param operations
     */
    public UserDefinedOperation(String name, int requiredOperands, Operation ... operations) {
        this.name = name;
        this.requiredOperands = requiredOperands;
        this.operationList = new ArrayList<>();
        this.operationList.addAll(Arrays.asList(operations));
    }

    /**
     *
     */
    @Override
    public void execute() {
        this.operationList.forEach(op -> {
            op.execute();
        });
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return  this.name;
    } 

    /**
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
     *
     * @param obj
     * @return
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
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public int getRequiredOperands() {
        return requiredOperands;
    }

    /**
     *
     * @param requiredOperands
     */
    public void setRequiredOperands(int requiredOperands) {
        this.requiredOperands = requiredOperands;
    }

    /**
     *
     * @param operations
     */
    public void updateList(Operation ... operations) {
        this.operationList.clear();
        this.operationList.addAll(Arrays.asList(operations));
    }

    /**
     *
     * @return
     */
    public String getListAsString() {
        String output = "";
        output = this.operationList.stream().map(op -> op.toString()+"\n").reduce(output, String::concat);
        return output;
    }

    /**
     *
     */
    public void exportOperation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
