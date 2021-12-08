package UserInterface.Parser;

/**
 * This is an enumeration for all supported parser in the calculator.
 *
 * @author Ernesto
 */
public enum ParserEnum {

    /**
     * Complex Number Parser
     */
    COMPLEXNUMBER("COMPLEXNUMBER_PARSER"),
    /**
     * Operation Parser
     */
    OPERATION("OPERATION_PARSER"),
    
    /**
     * Variable Parser
     */
    VARIABLE("VARIABLE_PARSER");

    private String value;

    private ParserEnum(String value) {
        this.value = value;
    }

    /**
     * Return a OperationsEnum object given the operation name as string.
     *
     * @param operationName is the name of the operation
     * @return the correct OperationsEnum object
     */
    public static ParserEnum valueOfString(String parserName) {
        for (ParserEnum p : ParserEnum.values()) {
            if (p.toString().equals(parserName)) {
                return p;
            }
        }
        throw new UnsupportedOperationException("Parser " + parserName + " not supported");
    }

    /**
     * Return a string that represent this operation.
     *
     * @return a string that represent this operation.
     */
    @Override
    public String toString() {
        return this.value;
    }

}
