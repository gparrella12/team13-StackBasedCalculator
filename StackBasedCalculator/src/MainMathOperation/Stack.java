package MainMathOperation;

import java.util.NoSuchElementException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**
 * This class provides an implementation of a stack.
 *
 * @author gparrella
 * @param <E> is the type of the stack's elements.
 */
public class Stack<E> {

    /**
     * This is the list that contains the stack's elements.
     */
    private final ObservableList<E> list;

    /**
     * Create a new empty ObservableStack.
     */
    public Stack() {
        this.list = FXCollections.observableArrayList();
    }

    /**
     * Returns true if this stack contains no elements.
     *
     * @return <code>true</code> if the stack has no elements,
     * <code>false</code> otherwise.
     */
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    /**
     * Returns the number of elements in this stack.
     *
     * @return the number of elements in this stack.
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Pushes an item onto the top of this stack.
     *
     * @param element the item to be pushed onto this stack.
     */
    public void push(E element) {
        this.list.add(this.size(), element);
    }

    /**
     * Removes the object at the top of this stack and returns that object as
     * the value of this function.
     *
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
     * Looks at the object at the top of this stack without removing it from the
     * stack.
     *
     * @return the object at the top of this stack.
     * @throws NoSuchElementException if this stack is empty.
     */
    public E top() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("The stack is empty");
        }
        return this.list.get(this.list.size() - 1);
    }

    /**
     * Clear all elements into the stack.
     */
    public void clear() {
        list.clear();
    }

    /**
     * Remove the last element of the stack, if there is at least one element. If
     * the stack is empty, the operation has no effect.
     */
    public void drop() {
        if (!this.isEmpty()) {
            this.pop();
        }
    }

    /**
     * Duplicate the last element into the stack, if there is at least one element.
     * If the stack is empty, the operation has no effect.
     */
    public void dup() {
        if (!this.isEmpty()) {
            this.push(this.top());
        }
    }

    /**
     * Swap the last two elements into the stack. If the stack is empty, the
     * operation has no effect.
     *
     * @throws NoSuchElementException if there are only 1 element into the
     * stack.
     */
    public void swap() {
        if (this.size() == 1) {
            throw new NoSuchElementException("There are less then two elements in the stack");
        } else if (this.size() >= 2) {
            E last = list.get(list.size() - 1);
            E secondLast = list.get(list.size() - 2);
            list.set(list.size() - 1, secondLast);
            list.set(list.size() - 2, last);
        }
    }

    /**
     * Copy the second last element at the top of the stack. If the stack is
     * empty, the operation has no effect.
     *
     * @throws NoSuchElementException if the stack has only one element.
     */
    public void over() {
        if (this.size() == 1) {
            throw new NoSuchElementException("There are less then two elements in the stack");
        } else if (this.size() >= 2) {
            E secondLast = list.get(list.size() - 2);
            this.push(secondLast);
        }

    }

    /**
     * Set a list as observer for the stack's contents.
     * 
     * @param list the list in which you want to see the stack's contents.
     */
    public void setObserver(ListView<E> list) {
        list.setItems(this.list);
    }
}
