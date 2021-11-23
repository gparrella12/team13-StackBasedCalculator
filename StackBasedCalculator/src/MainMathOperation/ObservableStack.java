package MainMathOperation;

import java.util.NoSuchElementException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class provides an implementation of a stack with observable properties.
 * @author gparrella
 * @param <E> is the type of the stack's elements.
 */
public class ObservableStack<E> {

    private final ObservableList<E> list;

    /**
     * Create a new empty ObservableStack.
     */
    public ObservableStack() {
        this.list =FXCollections.observableArrayList();
    }

    /**
     * Returns true if this stack contains no elements.
     * @return <code>true</code> if the stack has no elements, <code>false</code> otherwise.
     */
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    /**
     * Returns the number of elements in this stack.
     * @return the number of elements in this stack.
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Pushes an item onto the top of this stack.
     * @param element the item to be pushed onto this stack.
     */
    public void push(E element) {
        this.list.add(this.size(), element);
    }

    /**
     * Removes the object at the top of this stack and returns that object as the value of this function.
     * @return The object at the top of this stack.
     * @throws NoSuchElementException if this stack is empty.
     */
    public E pop() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("The stack is empty");
        }
        return this.list.remove(this.list.size() - 1);
    }

    /**
     * Looks at the object at the top of this stack without removing it from the stack.
     * @return the object at the top of this stack.
     * @throws NoSuchElementException if this stack is empty.
     */
    public E top() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("The stack is empty");
        }
        return this.list.get(this.list.size() - 1);
    }
}
