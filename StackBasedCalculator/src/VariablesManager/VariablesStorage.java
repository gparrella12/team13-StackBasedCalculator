package VariablesManager;

import java.util.NoSuchElementException;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author fsonnessa
 */
public class VariablesStorage {
    private ObservableMap<String, Complex> variables;
    private Archive<VariablesStorage> stack;
    
    /**
     *
     */
    public VariablesStorage(){
        variables = FXCollections.observableHashMap();
        stack = new Archive<>();
    }
    
    /**
     * Check the correctness of the given name:
     * the variable name must be only a letter (characther from 'a' to 'z')
     * @param key
     * @return passed key, at post in lower case
     * @throws IllegalArgumentException 
     */
    private String checkVarName(String key) throws IllegalArgumentException{        
        key = key.toLowerCase();
        if (!Character.isLetter(key.charAt(0)) && key.length() > 1)
            throw new IllegalArgumentException("Character not allowed");
        return key;
    }
    
    /**
     * Save a number in a variable assigning a name to it
     * @param name - variable name
     * @param value - number to save
     * @throws IllegalArgumentException
     */
    public void save(String name, Complex value) throws IllegalArgumentException{        
        String key = checkVarName(name);        
        variables.put(key, value);
    }
    
    /**
     * Return the value of a variable previously saved thanks its name
     * @param name - variable name
     * @return value stored
     * @throws NoSuchElementException if variable is not found
     * @throws IllegalArgumentException
     */
    public Complex getVariableValue(String name) throws NoSuchElementException, IllegalArgumentException{
        String key = checkVarName(name);        
        Complex result = variables.get(key);        
        if (result == null){
            throw new NoSuchElementException("Variable not foud");
        }
        return result;
    }
    
    /**
     * Remove a stored variable
     * @param name - variable name
     * @throws NoSuchElementException
     * @throws IllegalArgumentException 
     */
    public void removeVariable(String name) throws NoSuchElementException, IllegalArgumentException{
        String key = checkVarName(name);             
        if (variables.remove(key) == null){
            throw new NoSuchElementException("Variable not foud");
        }
    }
    
    /**
     * Clear all stored variables
     */
    public void clear(){
        variables.clear();
    }
    
    /**
     * Sum passed <code>value</code> to a stored variable value
     * @param name - variable name
     * @param value to sum
     * @throws NoSuchElementException
     */
    public void addToVariable(String name, Complex value) throws NoSuchElementException{
        String key = checkVarName(name);
        Complex toAdd = getVariableValue(key);
        save(key, toAdd.add(value));          
    }
    
    /**
     * Subtracts stored variable value to a passed <code>value</code>
     * @param name - variable name
     * @param value to subtract
     * @throws NoSuchElementException
     */
    public void subFromVariable(String name, Complex value) throws NoSuchElementException{
        String key = checkVarName(name);
        Complex toSub = getVariableValue(key);
        save(key, value.subtract(toSub));
    }
    
    // reference: https://stackoverflow.com/questions/37171820/populating-a-tableview-with-a-hashmap-that-will-update-when-hashmap-changes
    /**
     * Sets an observer to a TableView and its columns to see saved variables
     * @param table
     * @param varNameColumn
     * @param varValueColumn
     */
    public void setObserver(TableView<String> table, TableColumn<String, String> varNameColumn, TableColumn<String, Complex> varValueColumn) {
        ObservableList<String> keys = FXCollections.observableArrayList();
        
        variables.addListener((MapChangeListener.Change<? extends String, ? extends Complex> change) -> {
            boolean removed = change.wasRemoved();
            if (removed != change.wasAdded()) {
                // no put for existing key
                if (removed) {
                    keys.remove(change.getKey());
                } else {
                    keys.add(change.getKey());
                }
            }
        });
        
        table.setItems(keys);
        varNameColumn.setCellValueFactory(cd -> Bindings.createStringBinding(() -> cd.getValue()));
        varValueColumn.setCellValueFactory(cd -> Bindings.valueAt(variables, cd.getValue()));
        table.getColumns().setAll(varNameColumn, varValueColumn);
    }
    
    /**
     * Store current state of saved variables to permet its restore
     * @throws NotImplementedException
     */
    public void saveState()throws NotImplementedException{
        throw new NotImplementedException("TODO");
    }
    
    /**
     * Restore last saved state of variables
     * @throws NotImplementedException
     */
    public void restoreState() throws NotImplementedException{
        throw new NotImplementedException("TODO");
    }
}