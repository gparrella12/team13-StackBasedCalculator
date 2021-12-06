package VariablesManager;

import ArchiveModule.Archivable;
import ArchiveModule.ArchiveItem;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.apache.commons.math3.complex.Complex;

/**
 *
 * The class provides an implementation of a Variable Storage for the Calculator.
 * 
 * @author fsonnessa
 */
public class VariablesStorage implements Archivable {
    private ObservableMap<String, Complex> variables;
    
    /**
     * Creates a VariablesStorage object containing all the variables 
     * defined by the user.
     * 
     */
    public VariablesStorage(){
        variables = FXCollections.observableHashMap();
    }
    
    /**
     * Checks the correctness of the given name:
     * the variable name must be only a letter (characther from 'a' to 'z')
     * @param key is the variable name
     * @return the key passed as argument, in lower case
     * @throws IllegalArgumentException 
     */
    private String checkVarName(String key) throws IllegalArgumentException{        
        key = key.toLowerCase();
        if (!Character.isLetter(key.charAt(0)) || key.length() > 1)
            throw new IllegalArgumentException("Character not allowed");
        return key;
    }
    
    /**
     * Combines a value to variable, starting from the variable and the value
     * @param name variable name
     * @param value number to save
     * @throws IllegalArgumentException
     */
    public void save(String name, Complex value) throws IllegalArgumentException{        
        String key = checkVarName(name);        
        variables.put(key, value);
    }
    
    /**
     * Returns the value of a variable previously saved, starting from its name
     * @param name variable name
     * @return value stored
     * @throws NoSuchElementException if variable is not found
     * @throws IllegalArgumentException
     */
    public Complex getVariableValue(String name) throws NoSuchElementException, IllegalArgumentException{
        String key = checkVarName(name);        
        Complex result = variables.get(key);        
        if (result == null){
            throw new NoSuchElementException("Variable not found");
        }
        return result;
    }
    
    /**
     * Removes a stored variable
     * @param name variable name
     * @throws NoSuchElementException
     * @throws IllegalArgumentException 
     */
    public void removeVariable(String name) throws NoSuchElementException, IllegalArgumentException{
        String key = checkVarName(name);             
        if (variables.remove(key) == null){
            throw new NoSuchElementException("Variable not found");
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
     * @param name variable name
     * @param value to sum
     * @throws NoSuchElementException
     */
    public void addToVariable(String name, Complex value){
        String key = checkVarName(name);
        Complex toAdd = getVariableValue(key);
        variables.put(key, toAdd.add(value)); 

    }
    
    /**
     * Subtracts stored variable value to a passed <code>value</code>
     * @param name variable name
     * @param value to subtract
     * @throws NoSuchElementException
     */
    public void subFromVariable(String name, Complex value) throws NoSuchElementException{
        String key = checkVarName(name);
        Complex toSub = getVariableValue(key);
        variables.put(key, toSub.subtract(value));
    }
    
    
    /**
     * Sets an observer to a TableView and its columns to see saved variables
     * @param table is the table in witch show the variable and the value
     * @param varNameColumn is the column associated to the variables
     * @param varValueColumn is the column associated to the value
     * 
     * @see https://stackoverflow.com/questions/37171820/populating-a-tableview-with-a-hashmap-that-will-update-when-hashmap-changes
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
     * Stores current state of saved variables to allow its restore
     */
    @Override
    public ArchiveItem toSave() {        
       HashMap<String, Complex> toSave = new HashMap<>();
       for (Map.Entry<String, Complex> entry : variables.entrySet()){
            toSave.put(entry.getKey(), entry.getValue());
        }        
        return new ArchiveItem(toSave);
    }
    
    /**
     * Restores the last saved state of variables
     */
    @Override
    public void toRestore(ArchiveItem state) {
        HashMap<String, Complex> toRestore = (HashMap<String, Complex>) state.getElement();
        variables.clear();
        for(Map.Entry<String, Complex> entry : toRestore.entrySet()){
            variables.put(entry.getKey(), entry.getValue());
        }
    }
    
    public int getSize(){
    return variables.size();    
    }
    
    @Override
    public String toString(){
        return variables.toString();
    }
}
