/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VariablesManager.ArchiveModule;

import MainMathOperation.Stack;

/**
 *
 * @author fsonnessa
 */
public class Archive<E> {
    private Stack<ArchivedItem<E>> stack;
    
    public Archive() {
        stack = new Stack<>();
    }
    
    public void save(E toSave) {
        stack.push(new ArchivedItem<>(toSave));
    }
    
    public E restore() {
        return stack.pop().getElement();
    }
}
