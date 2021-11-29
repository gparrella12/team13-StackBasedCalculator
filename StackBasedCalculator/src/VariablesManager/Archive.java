/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VariablesManager;

import MainMathOperation.Stack;
import jdk.jshell.spi.ExecutionControl;

/**
 *
 * @author fsonnessa
 */
public class Archive<E> {
    private Stack<E> stack;
    
    public Archive() {
        
    }
    
    public void save(E toSave) throws ExecutionControl.NotImplementedException{
        throw new ExecutionControl.NotImplementedException("TODO");
    }
    
    public E restore() throws ExecutionControl.NotImplementedException{
        throw new ExecutionControl.NotImplementedException("TODO");
    }
    
}
