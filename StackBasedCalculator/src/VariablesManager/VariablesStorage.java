/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VariablesManager;

import javafx.collections.ObservableMap;
import javafx.scene.control.TableView;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;

/**
 *
 * @author fsonnessa
 */
public class VariablesStorage<E> {
    private ObservableMap<String, E> variables;
    private Archive<VariablesStorage<E>> stack;
    
    public VariablesStorage(){
        
    }
    
    public E save(String name, E value) throws NotImplementedException{
        throw new NotImplementedException("TODO");
    }
    
    public String getVariableValue(String name) throws NotImplementedException{
        throw new NotImplementedException("TODO");
    }
    
    public E addToVariable(String name, E value) throws NotImplementedException{
        throw new NotImplementedException("TODO");
    }
    
    public E subFromVariable(String name, E value) throws NotImplementedException{
        throw new NotImplementedException("TODO");
    }
    
    public void setObserver(TableView table) throws NotImplementedException{
        throw new NotImplementedException("TODO");
    }
    
    public void saveState()throws NotImplementedException{
        throw new NotImplementedException("TODO");
    }
    
    public void restoreState() throws NotImplementedException{
        throw new NotImplementedException("TODO");
    }
}
