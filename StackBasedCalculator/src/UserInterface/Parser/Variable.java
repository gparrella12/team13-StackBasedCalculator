package UserInterface.Parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Variable implements Parser {

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
