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
        if (parserType==ParserEnum.COMPLEXNUMBER) {
            return new ComplexNumberParser();

        } else if (parserType==ParserEnum.OPERATION) {
            return new OperationParser();

        } else if (parserType==ParserEnum.VARIABLE) {
            return new VariableParser();
        }

        return null;
    }

}
