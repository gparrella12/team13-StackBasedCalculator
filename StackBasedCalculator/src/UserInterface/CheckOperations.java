package UserInterface;

/**
 * CheckOperations class contains the utility methods to check all the
 keyboard entries.
 *
 * @author Speranza
 */
public class CheckOperations {

    private String[] stackOperations = {"dup", "over", "clear", "drop", "swap"};
    private String[] mathOperations = {"+", "-", "*", "/", "sqrt", "+-"};

    /**
     * The function checks if the operation inserted in the physical keyboard is
     * supported by the Calculator.
     *
     * @return the operation.
     */
    public String checkOperation(String operation) {

        for (String s : stackOperations) {
            if (s.equals(operation)) {
                return s;
            }
        }

        for (String s : mathOperations) {
            if (s.equals(operation)) {
                return s;
            }
        }

        return null;

    }

}
