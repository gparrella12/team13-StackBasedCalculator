package MainMathOperation;

import java.util.NoSuchElementException;
import org.apache.commons.math3.complex.Complex;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.ListView;
import org.apache.commons.math3.exception.MathParseException;

/**
 * Implementation of an Reverse Polish Notation Solver for Complex numbers
 *
 * Use a stack to memorize numbers used in operations
 *
 * This class follow Singleton
 *
 * @author fsonnessa
 */
public class RPNSolver {

    private static RPNSolver instance = null;
    private Stack<Complex> stack = null;

    private RPNSolver() {
        this.stack = new Stack<>();
    }

    /**
     * This method return the instance of RPNSolver Singleton class
     *
     * @return RPNSolver instance
     */
    public static RPNSolver getInstance() {
        // Create the object only if not exists
        if (instance == null) {
            instance = new RPNSolver();
        }
        return instance;
    }

    /**
     * Adds the first two elements of the stack and save the reult on top. top =
     * top + (top-1)
     *
     * @throws NoSuchElementException
     */
    public void sum() throws NoSuchElementException {

        if (stack.size() < 2) {
            throw new NoSuchElementException("Need almost two elements in the stack");
        }

        Complex num1 = stack.pop();
        Complex num2 = stack.pop();

        stack.push(num1.add(num2));
    }

    /**
     * Subtracts the first two elements of the stack and save the reult on top.
     * This operation has fixed oreder of operands: the second element is the
     * left operant while the first element (top element) is the right operand
     * top = (top-1) - top
     *
     * @throws NoSuchElementException
     */
    public void subtraction() throws NoSuchElementException {

        if (stack.size() < 2) {
            throw new NoSuchElementException("Need almost two elements in the stack");
        }

        Complex num1 = stack.pop();
        Complex num2 = stack.pop();

        stack.push(num2.subtract(num1));
    }

    /**
     * Multiply the first two elements of the stack and save the reult on top.
     * top = top * (top-1)
     *
     * @throws NoSuchElementException
     */
    public void product() throws NoSuchElementException {

        if (stack.size() < 2) {
            throw new NoSuchElementException("Need almost two elements in the stack");
        }

        Complex num1 = stack.pop();
        Complex num2 = stack.pop();

        stack.push(num1.multiply(num2));
    }

    /**
     * Divides the first two elements of the stack and save the reult on top.
     * This operation has fixed oreder of operands: the second element is the
     * left operant while the first element (top element) is the right operand
     * top = (top-1) / top
     *
     * @throws NoSuchElementException
     */
    public void division() throws NoSuchElementException {

        if (stack.size() < 2) {
            throw new NoSuchElementException("Need almost two elements in the stack");
        }

        Complex num1 = stack.pop();
        Complex num2 = stack.pop();

        stack.push(num2.divide(num1));
    }

    /**
     * Make the root of on top element top = sqrt(top)
     *
     * @throws NoSuchElementException
     */
    public void sqrt() throws NoSuchElementException {
        if (stack.size() < 1) {
            throw new NoSuchElementException("Need almost one elements in the stack");
        }

        Complex num = stack.pop();

        stack.push(num.sqrt());
    }

    /**
     * Invert the sign of on top number top = top * -1
     *
     * @throws NoSuchElementException
     */
    public void invertSign() throws NoSuchElementException {
        if (stack.size() < 1) {
            throw new NoSuchElementException("Need almost one elements in the stack");
        }

        Complex num = stack.pop();

        stack.push(num.negate());
    }

    /**
     * Return last ANSwer
     *
     * @return
     */
    public Complex getAns() {
        return stack.top();
    }

    /**
     * Given a complex number as a string return it as a Complex object
     *
     * @param str
     * @param imaginaryCharacter
     * @return Complex or null if the string passed not rappresent a complex
     * number
     */
    private Complex parser(String str, String imaginaryCharacter) {
        String[] pattern = {"([-+]?[0-9]+\\.?[0-9]*j)",
            "([-+]?j[0-9]+\\.?[0-9]*)",
            "([-+]?[0-9]+\\.?[0-9]*)",
            "([-+]?j)"};
        boolean match = false;
        double real = 0, img = 0;
        str = str.replace(" ", "");
        
        Matcher m0 = Pattern.compile("(?<!\\d)([.][0-9]+)").matcher(str);
        while (m0.find()){
            String tmp = m0.group();
            str = str.replace(tmp, "0"+tmp);
        }

        for (int i = 0; i < pattern.length; i++) {
            String p = pattern[i];
            if (str.equals("")) {
                break;
            }
            Matcher m = Pattern.compile(p).matcher(str);
            if (m.find()) {
                if (p.contains(imaginaryCharacter)) {
                    String tmp = m.group().replace(imaginaryCharacter, "");

                    if (i == pattern.length - 1) {
                        tmp = tmp.replace("+", "").replace("-", "-1");
                    }

                    img = tmp.equals("") ? 1 : Double.parseDouble(tmp);
                } else {
                    real = Double.parseDouble(m.group());
                }
                str = m.replaceAll("");
                match = true;
            }
        }
        return match ? new Complex(real, img) : null;
    }

    /**
     * Push a number in the stack
     *
     * @param num
     */
    public void addNum(Complex num) {
        stack.push(num);
    }

    /**
     * Push a number in the stack
     *
     * @param num
     */
    public void addNum(String num) {
        this.addNum(num, "j");
    }

    /**
     * Push a number in the stack
     *
     * @param num
     * @param imaginaryCharacter
     * @throws MathParseException
     */
    public void addNum(String num, String imaginaryCharacter) throws MathParseException {
        Complex c = this.parser(num, imaginaryCharacter);

        if (c == null) {
            throw new MathParseException("Bad string representation", 0);
        }

        stack.push(c);
    }

    /**
     * Invoke clear() method of the stack
     */
    public void clear() {
        stack.clear();
    }

    /**
     * Invoke drop() method of the stack
     */
    public void drop() {
        stack.drop();
    }

    /**
     * Invoke dup() method of the stack
     */
    public void dup() {
        stack.dup();
    }

    /**
     * Invoke swap() method of the stack
     */
    public void swap() {
        stack.swap();
    }

    /**
     * Invoke over() method of the stack
     */
    public void over() {
        stack.over();
    }

    /**
     * Makes a link between a ListView and the stack
     *
     * @param list
     */
    public void setList(ListView<Complex> list) {
        stack.setObserver(list);
    }
}
