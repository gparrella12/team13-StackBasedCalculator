package UserInterface.Parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is parser for the VariableParser
 *
 * @author Ernesto
 */
public class VariableParser implements Parser {

    /**
     * Return a String object corresponding to the variable
     *
     * @param s is the input string
     * @return the variable
     */
    @Override
    public String check(String s) {

        Pattern patternF = Pattern.compile("[-|+|<|>][a-z]");
        Matcher matcherF = patternF.matcher(s);

        if (patternF.matcher(s).matches() && matcherF.find()) {
            return s;
        }
        return null;
    }
}
