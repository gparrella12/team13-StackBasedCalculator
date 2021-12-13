package ArchiveModule;

import java.util.Stack;

/**
 *
 * @author fsonnessa
 */
public class Archive {

    private Stack<ArchivableState> stack;
    private Archivable instance;

    public Archive() {
        this.stack = new Stack<>();
        this.instance = null;
    }

    public Archive(Archivable instance) {
        this.stack = new Stack<>();
        this.instance = instance;
    }

    public void setInstance(Archivable instance) {
        this.instance = instance;
    }

    public void saveState() {
        try {
            stack.push(this.instance.getCurrentState());
        } catch (NullPointerException ex) {
            throw new NullPointerException(ex.getMessage() + "\nTip: Set the instance of class that you want save before");
        }
    }

    public void restoreState() {
        try {
            this.instance.setCurrentState(stack.pop());
        } catch (NullPointerException ex) {
            throw new NullPointerException(ex.getMessage() + "\nTip: Set the instance of class that you want restore before");
        }
    }

    public ArchivableState checkLastSave() {
        return stack.peek();
    }
}
