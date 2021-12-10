package UserInterface.Parser;

import java.util.Locale;
import java.util.Scanner;

/**
 * This class implement a parser for a push operation during reading from file.
 * @author gparrella
 */
public class PushFileParser implements Parser {

    @Override
    public String check(String s) {
        try (Scanner sc = new Scanner(s)) {
            sc.useDelimiter("\\s");
            sc.useLocale(Locale.US);
            if (!sc.next().equals("push:")) {
                return null;
            }
            String complexNumber = sc.nextLine();
            return new ComplexNumberParser().check(complexNumber);
        } catch (Exception ex) {
        }
        return null;
    }

}
