package UserInterface.Parser;

/**
 *
 * @author Ernesto
 */
public class ParserFactory {

    public Parser getParser(String parserType) {
        if (parserType == null) {
            return null;
        }
        if (parserType.equalsIgnoreCase("COMPLEXNUMBER")) {
            return new ComplexNumber();

        } else if (parserType.equalsIgnoreCase("OPERATION")) {
            return new Operation();

        } else if (parserType.equalsIgnoreCase("VARIABLE")) {
            return new Variable();
        }

        return null;
    }

}
