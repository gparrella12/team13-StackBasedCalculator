package UserInterface.Parser;

/**
 *This class is a factory, in the Factory Method Pattern, to generate
 * parser of concrete class based on given information.
 * @author Ernesto
 */
public class ParserFactory {
    
    
    /**
     * Return a Parser object given the parser type 
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
