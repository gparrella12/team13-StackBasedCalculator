package UserInterface.Parser;

/**
 *This class represents the factory, in the Factory Method Pattern, to generate
 * parsers of concrete class based on given information.
 * @author Ernesto
 */
public class ParserFactory {
    
    
    /**
     * Returns a Parser object given the parser type 
     *
     * @param parserType is the type of the parser
     * @return the correct Parser object
     */
    
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
