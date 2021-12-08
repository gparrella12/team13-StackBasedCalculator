package UserInterface.Parser;

/**
 *This class is a factory, in the Factory Method Pattern, to generate
 * parser of concrete class based on given information.
 * @author Ernesto
 */
public class ParserFactory {

    public Parser getParser(ParserEnum parserType) {
        if (parserType == null) {
            return null;
        }
        switch (parserType) {
            case COMPLEXNUMBER -> {
                return new ComplexNumberParser();
            }
            case OPERATION -> {
                return new OperationParser();
            }
            case VARIABLE -> {
                return new VariableParser();
            }
        }

        return null;
    }

}
