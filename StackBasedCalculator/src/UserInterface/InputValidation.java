package UserInterface;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.math3.complex.Complex;

/**
 * InputValidation class contains the utility methods to check all the
 keyboard entries.
 *
 * @authors ermancusi & Speranza
 */
public class InputValidation {

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
    
    
    /**
     * Given a complex number as a string return it as a Complex object
     *
     * @param str
     * @param imaginaryCharacter
     * @return Complex or null if the string passed not rappresent a complex
     * number
     */
    public Complex parser(String str, String imaginaryCharacter) {
        double real = 0, imaginary = 0;
        String numberNoWhiteSpace = str.replaceAll("\\s", "");

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
        Pattern patternD = Pattern.compile("([-|+]?[0-9]+\\.?[0-9]*)([-|+]+[0-9]+\\.*[0-9]*)[j$]+");

        // Matches ONLY imaginary number when 1j is written as j.
        // Ex: +j (+1j)   
        Pattern patternE = Pattern.compile("([-|+]?[0-9]*\\.*[0-9]*)[j$]");

        Matcher matcherA = patternA.matcher(numberNoWhiteSpace);
        Matcher matcherB = patternB.matcher(numberNoWhiteSpace);
        Matcher matcherC = patternC.matcher(numberNoWhiteSpace);
        Matcher matcherD = patternD.matcher(numberNoWhiteSpace);
        Matcher matcherE = patternE.matcher(numberNoWhiteSpace);

        boolean flag = patternA.matcher(numberNoWhiteSpace).matches()
                || patternB.matcher(numberNoWhiteSpace).matches()
                || patternC.matcher(numberNoWhiteSpace).matches()
                || patternD.matcher(numberNoWhiteSpace).matches()
                || patternE.matcher(numberNoWhiteSpace).matches();

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
            } else if (matcherE.find()) {
                real = 0.0;
                if (matcherE.group(1).toString().equals("-")) {
                    imaginary = -1.0;
                } else {
                    imaginary = 1.0;
                }
            }
            return new Complex(real, imaginary);

        }

        return null;

    }

}
