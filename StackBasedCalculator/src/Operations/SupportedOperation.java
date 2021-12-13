package Operations;

import Stack.ObservableStack;
import java.util.NoSuchElementException;
import org.apache.commons.math3.complex.Complex;

/**
 * This is an abstract class for the operations that are already supported in
 * the calculator.
 *
 * @author gparrella
 */
public abstract class SupportedOperation implements Operation {

    private final OperationsEnum name;
    private final ObservableStack<Complex> stack;

    /**
     * Create a support operation with a name.
     *
     * @param name
     */
    public SupportedOperation(OperationsEnum name, ObservableStack<Complex> stack) {
        this.name = name;
        this.stack = stack;
    }

    /**
     * Get the operation's name.
     *
     * @return
     */
    public String getName() {
        return name.toString();
    }

    /**
     * A string representation of the operation with its name.
     *
     * @return
     */
    @Override
    public String toString() {
        return this.name.toString();
    }

    public int size() {
        return stack.size();
    }

    /**
     * Pushes an item onto the top of this stack.
     *
     * @param element the item to be pushed onto this stack.
     */
    public void push(Complex element) {
        stack.push(element);
    }

    /**
     * Removes the object at the top of this stack and returns that object as
     * the value of this function.
     *
     * @return The object at the top of this stack.
     * @throws NoSuchElementException if this stack is empty.
     */
    public Complex pop() {
        return stack.pop();
    }

    /**
     * Looks at the object at the top of this stack without removing it from the
     * stack.
     *
     * @return the object at the top of this stack.
     * @throws NoSuchElementException if this stack is empty.
     */
    public Complex top() {
        return stack.top();
    }

    /**
     * Clear all elements into the stack.
     */
    public void clear() {
        stack.clear();
    }

    /**
     * Remove the last element of the stack, if there is at least one element.
     * If the stack is empty, the operation has no effect.
     */
    public void drop() {
        stack.drop();
    }

    /**
     * Duplicate the last element into the stack, if there is at least one
     * element. If the stack is empty, the operation has no effect.
     */
    public void dup() {
        stack.dup();
    }

    /**
     * Swap the last two elements into the stack. If the stack is empty, the
     * operation has no effect.
     *
     * @throws NoSuchElementException if there are only 1 element into the
     * stack.
     */
    public void swap() {
        stack.swap();
    }

    /**
     * Copy the second last element at the top of the stack. If the stack is
     * empty, the operation has no effect.
     *
     * @throws NoSuchElementException if the stack has only one element.
     */
    public void over() {
        stack.over();
    }
    
}
