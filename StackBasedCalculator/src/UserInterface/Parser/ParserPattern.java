package UserInterface.Parser;

/**
 *
 * @author Ernesto
 */
public class ParserPattern {

    public static void main(String[] args) {
        String s = "+";
        String s1 = new ParserFactory().getParser("COMPLEXNUMBER").check(s);
        String s2 = new ParserFactory().getParser("OPERATION").check(s);
        String s3 = new ParserFactory().getParser("VARIABLE").check(s);

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }

}
