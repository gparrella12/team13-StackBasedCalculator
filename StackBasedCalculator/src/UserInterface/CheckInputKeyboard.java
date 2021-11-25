package UserInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * CheckInputKeyboard class contains the utility methods to check all the
 * keyboard entries.
 *
 * @author Speranza
 */
public class CheckInputKeyboard {

    private String[] stackOperations = {"dup", "over", "clear", "drop", "swap"};
    private String[] mathOperations = {"+", "-", "*", "/", "sqrt", "+-"};

    /**
     * The function checks if the operation inserted in the physical keyboard is
     * supported by the Calculator.
     *
     * @return true is the operation is written in a right way, false otherwise.
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

    public boolean checkIfComplex(String s) {
        Pattern patternA, patternB, patternC, patternD, patternE;
        String numberNoWhiteSpace = null;

        numberNoWhiteSpace = s.replaceAll("\\s", "");
        // Matches complex number with BOTH real AND imaginary parts.  
        // Ex: -3-2.0i
        patternA = Pattern.compile("([-|+]?[0-9]+\\.?[0-9]*)([-|+]?[0-9]+\\.?[0-9]*)[j$]+");

        // Matches ONLY real number.
        // Ex: 3.145
        patternB = Pattern.compile("([-|+|]?[0-9]+\\.?[0-9]*)$");

        // Matches ONLY imaginary number.
        // Ex: -10i
        patternC = Pattern.compile("([-|+]?[0-9]+\\.?[0-9]*)[j$]");

        // Matches complex number when 1j is written as j.
        // Ex: 5+j (5+1j)
        patternD = Pattern.compile("([-|+]?[0-9]+\\.?[0-9]*)([-|+]?[0-9]*\\.*[0-9]*)[j$]+");

        // Matches ONLY imaginary number when 1j is written as j.
        // Ex: +j (+1j)   
        patternE = Pattern.compile("([-|+]?[0-9]*\\.*[0-9]*)[j$]");

        return patternA.matcher(numberNoWhiteSpace).matches()
                || patternB.matcher(numberNoWhiteSpace).matches()
                || patternC.matcher(numberNoWhiteSpace).matches()
                || patternD.matcher(numberNoWhiteSpace).matches()
                || patternE.matcher(numberNoWhiteSpace).matches();
    }

    public List<Double> parsingfComplex(String s) {
        Double real = null, imaginary = null;
        String numberNoWhiteSpace = s.replaceAll("\\s", "");

        // Matches complex number with BOTH real AND imaginary parts.  
        // Ex: -3-2.0j
        Pattern patternA = Pattern.compile("([-|+]?[0-9]+\\.?[0-9]*)([-|+]?[0-9]+\\.?[0-9]*)[j$]+");

        // Matches ONLY real number.
        // Ex: 3.145
        Pattern patternB = Pattern.compile("([-|+]?[0-9]+\\.?[0-9]*)$");

        // Matches ONLY imaginary number.
        // Ex: -10j
        Pattern patternC = Pattern.compile("([-|+]?[0-9]+\\.?[0-9]*)[j$]");

        // Matches complex number when 1j is written as j.
        // Ex: 5+j (5+1j)
        Pattern patternD = Pattern.compile("([-|+]?[0-9]+\\.?[0-9]*)([-|+]?[0-9]*\\.*[0-9]*)[j$]+");

        // Matches ONLY imaginary number when 1j is written as j.
        // Ex: +j (+1j)   
        Pattern patternE = Pattern.compile("([-|+]?[0-9]*\\.*[0-9]*)[j$]");

        Matcher matcherA = patternA.matcher(numberNoWhiteSpace);
        Matcher matcherB = patternB.matcher(numberNoWhiteSpace);
        Matcher matcherC = patternC.matcher(numberNoWhiteSpace);
        Matcher matcherD = patternD.matcher(numberNoWhiteSpace);
        Matcher matcherE = patternE.matcher(numberNoWhiteSpace);

        if (matcherA.find()) {
            real = Double.parseDouble(matcherA.group(1));
            imaginary = Double.parseDouble(matcherA.group(2));
        } else if (matcherB.find()) {
            real = Double.parseDouble(matcherB.group(1));
            imaginary = 0.0;
        } else if (matcherC.find()) {
            real = 0.0;
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
        List<Double> l = new ArrayList<>();
        l.add(0, real);
        l.add(1, imaginary);
        return l;
    }

}
