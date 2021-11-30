package UserDefinedOperation;

import java.util.Objects;

/**
 * This is an abstract class for the operation that are already supported in the
 * calculator.
 *
 * @author gparrella
 */
public abstract class SupportedOperation implements Operation {

    private String name;

    /**
     * Create a support operation with a name.
     *
     * @param name
     */
    public SupportedOperation(String name) {
        this.name = name;
    }
    /**
     * Get the operation name.
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Return the hashcode of an operation, based on its name.
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.name);
        return hash;
    }

    /**
     * Compare two supported operation.
     *
     * @param obj the supported operation that we want to compare with
     * <code>this</code>
     * @return true if <code> this </code> is equal to obj, false otherwise
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
        final SupportedOperation other = (SupportedOperation) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    /**
     * A string representation of the operation with its name.
     *
     * @return
     */
    @Override
    public String toString() {
        return this.name;
    }
}
