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
     * Returns a ParserEnum object given the parserName name as string.
     *
     * @param parserName is the name of the operation
     * @return the correct ParserEnum object
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
     * Returns a string that represents this operation.
     *
     * @return a string that represents this operation.
     */
    @Override
    public String toString() {
        return this.value;
    }

}
