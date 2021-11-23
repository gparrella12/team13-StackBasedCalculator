package MainMathOperation.ObservableStack;

import java.util.NoSuchElementException;

/**
 * This class add new features to ObservableStack.
 * @author gparrella
 * @param <E> is the type of the element of the stack.
 */
public class ObservableStackExtended<E> extends ObservableStack<E>{
    
    /**
     * Clear all elements into the stack.
     */
    public void clear() {
        super.list.clear();
    }
    
    /**
     * Remove the last element of the stack, if there are almost one element.
     * If the stack is empty, the operation has no effect.
     */
    public void drop() {
        if (!this.isEmpty()) {
            this.pop();
        }
    }
    
    /**
     * Duplicate the last element into the stack, if there are almost one element.
     * If the stack is empty, the operation has no effect.
     */
    public void dup() {
        if (!this.isEmpty()) {
            this.push(this.top());
        }
    }
    
    /**
     * Swap the last two elements into the stack. 
     * If the stack is empty, the operation has no effect.
     * @throws NoSuchElementException if there are only 1 element into the stack.
     */
    public void swap() {
        if (this.size() == 1) {
            throw new NoSuchElementException("There isn't enough element");
        } else if (this.size() >= 2) {
            E last = super.list.get(super.list.size() - 1);
            E secondLast = super.list.get(super.list.size() - 2);
            super.list.set(super.list.size() - 1, secondLast);
            super.list.set(super.list.size() - 2, last);
        }
    }
    
    /**
     * Copy the second last element at the top of the stack.
     * If the stack is empty, the operation has no effect.
     * @throws NoSuchElementException if the stack has only one element.
     */
    public void copy() {
        if (this.size() == 1) {
            throw new NoSuchElementException("There isn't enough element");
        } else if (this.size() >= 2) {
            E secondLast = super.list.get(super.list.size() - 2);
            this.push(secondLast);
        }
        
    }

}
