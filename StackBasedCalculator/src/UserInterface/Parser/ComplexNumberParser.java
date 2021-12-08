package UserInterface.Parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexFormat;

/**
 *This is parser for the Complex Number
 * @author Ernesto
 */
public class ComplexNumberParser implements Parser {
    
  /**
     * Return a String object corresponding to the complex number
     *
     * @param s is the input string
     * @return the complex number
     */
    
    @Override
    public String check(String s) {
        double real = 0, imaginary = 0;
        String numberNoWhiteSpace = s.replaceAll("\\s", "");

        // Matches complex number with BOTH real AND imaginary parts.  
        // Ex: -3-2.0i
        Pattern patternA = Pattern.compile("([-|+]?[0-9]+\\.?[0-9]*)([-|+]+[0-9]+\\.?[0-9]*)[j$]+");

        // Matches ONLY real number.
        // Ex: 3.145
        Pattern patternB = Pattern.compile("([-|+]?[0-9]+\\.?[0-9]*)$");

        // Matches ONLY imaginary number.
        // Ex: -10i
        Pattern patternC = Pattern.compile("([-|+]?[0-9]+\\.?[0-9]*)[j$]");

        // Matches complex number when 1j is written as j.
        // Ex: 5+j (5+1j)
        Pattern patternD = Pattern.compile("([-|+]?[0-9]+\\.?[0-9]*)([-|+]+[0-9]*\\.*[0-9]*)[j$]+");

        Matcher matcherA = patternA.matcher(numberNoWhiteSpace);
        Matcher matcherB = patternB.matcher(numberNoWhiteSpace);
        Matcher matcherC = patternC.matcher(numberNoWhiteSpace);
        Matcher matcherD = patternD.matcher(numberNoWhiteSpace);
        // Matcher matcherE = patternE.matcher(numberNoWhiteSpace);

        boolean flag = patternA.matcher(numberNoWhiteSpace).matches()
                || patternB.matcher(numberNoWhiteSpace).matches()
                || patternC.matcher(numberNoWhiteSpace).matches()
                || patternD.matcher(numberNoWhiteSpace).matches();

        if (flag) {
            if (matcherA.find()) {
                real = Double.parseDouble(matcherA.group(1));
                imaginary = Double.parseDouble(matcherA.group(2));
            } else if (matcherB.find()) {
                real = Double.parseDouble(matcherB.group(1));
                imaginary = 0;
            } else if (matcherC.find()) {
                real = 0;
                imaginary = Double.parseDouble(matcherC.group(1));
            } else if (matcherD.find()) {
                real = Double.parseDouble(matcherD.group(1));
                if (matcherD.group(2).toString().equals("-")) {
                    imaginary = -1.0;
                } else {
                    imaginary = 1.0;
                }
            }
            String complexNumber = new ComplexFormat().format(new Complex(real, imaginary)).replace(" ", "");

            int iIndex = complexNumber.indexOf("i");
            if (iIndex != -1 && (complexNumber.charAt(iIndex - 1) == '+' || complexNumber.charAt(iIndex - 1) == '-')) {
                complexNumber = complexNumber.substring(0, iIndex - 1) + complexNumber.charAt(iIndex - 1) + "1" + complexNumber.substring(iIndex, complexNumber.length());
            }

            return complexNumber;

        }

        return null;
    }

}
