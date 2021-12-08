package UserInterface.Parser;

public class Operation implements Parser  {

    private String[] stackOperations = {"dup", "over", "clear", "drop", "swap"};
    private String[] mathOperations = {"+", "-", "*", "/", "sqrt", "+-"};



@Override
    public String check(String operation) {
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
