/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ArchiveModule;

import java.util.Stack;

/**
 *
 * @author fsonnessa
 */
public class Archive {

    private Stack<ArchiveItem> stack;
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
        stack.push(this.instance.toSave());
    }

    public void restoreState() {
        this.instance.toRestore(stack.pop());
    }

    public ArchiveItem checkLastSave() {
        return stack.peek();
    }
}
