package MainMathOperation;

import java.util.NoSuchElementException;
import org.apache.commons.math3.complex.Complex;
import javafx.scene.control.ListView;

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
     * Adds the first two elements of the stack and save the result on top. top =
     * top + (top-1)
     *
     * @throws NoSuchElementException
     */
    public void sum() throws NoSuchElementException {

        if (stack.size() < 2) {
            throw new NoSuchElementException("There are less then two elements in the stack");
        }

        Complex num1 = stack.pop();
        Complex num2 = stack.pop();

        stack.push(num1.add(num2));
    }

    /**
     * Subtracts the first two elements of the stack and save the result on top.
     * This operation has fixed order of operands: the second element is the
     * left operant while the first element (top element) is the right operand
     * top = (top-1) - top
     *
     * @throws NoSuchElementException
     */
    public void subtraction() throws NoSuchElementException {

        if (stack.size() < 2) {
            throw new NoSuchElementException("There are less then two elements in the stack");
        }

        Complex num1 = stack.pop();
        Complex num2 = stack.pop();

        stack.push(num2.subtract(num1));
    }

    /**
     * Multiply the first two elements of the stack and save the result on top.
     * top = top * (top-1)
     *
     * @throws NoSuchElementException
     */
    public void product() throws NoSuchElementException {

        if (stack.size() < 2) {
            throw new NoSuchElementException("There are less then two elements in the stack");
        }

        Complex num1 = stack.pop();
        Complex num2 = stack.pop();

        stack.push(num1.multiply(num2));
    }

    /**
     * Divides the first two elements of the stack and save the result on top.
     * This operation has fixed order of operands: the second element is the
     * left operant while the first element (top element) is the right operand
     * top = (top-1) / top
     *
     * @throws NoSuchElementException
     */
    public void division() throws NoSuchElementException {

        if (stack.size() < 2) {
            throw new NoSuchElementException("There are less then two elements in the stack");
        }

        if (stack.top().equals(new Complex(0))) {
            throw new ArithmeticException("MATH ERROR");
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
            throw new NoSuchElementException("The stack is empty");
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
            throw new NoSuchElementException("The stack is empty");
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
     * Push a number in the stack
     *
     * @param num
     */
    public void addNum(Complex num) {   
        if (num == null) {
            throw new NumberFormatException("Null element");
        }

        stack.push(num);
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
    
    /**
     * Get the stack's size.
     * @return the size of the stack
     */
    public int getStackSize(){
        return stack.size();
    }
}
