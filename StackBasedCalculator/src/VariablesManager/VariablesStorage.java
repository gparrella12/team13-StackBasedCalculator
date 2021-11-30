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
    
    public VariablesStorage(){
        variables = FXCollections.emptyObservableMap();
        stack = new Archive<>();
    }
    
    private String checkKey(String key)throws IllegalArgumentException{        
        String newKey = key.toLowerCase();
        if (!Character.isLetter(newKey.charAt(0)) && newKey.length() > 1)
            throw new IllegalArgumentException("Character not allowed");
        return newKey;
    }
    
    public void save(String name, Complex value) throws IllegalArgumentException{        
        String newVar = checkKey(name);        
        variables.put(newVar, value);
    }
    
    public Complex getVariableValue(String name) throws NoSuchElementException, IllegalArgumentException{
        String toGet = checkKey(name);        
        Complex result = variables.get(toGet);        
        if (result == null){
            throw new NoSuchElementException("Variable not foud");
        }
        return result;
    }
    
    public void addToVariable(String name, Complex value) throws NoSuchElementException{
        String key = checkKey(name);
        Complex toAdd = getVariableValue(key);
        save(key, toAdd.add(value));          
    }
    
    public void subFromVariable(String name, Complex value) throws NoSuchElementException{
        String key = checkKey(name);
        Complex toSub = getVariableValue(key);
        save(key, value.subtract(toSub));
    }
    
    // reference: https://stackoverflow.com/questions/37171820/populating-a-tableview-with-a-hashmap-that-will-update-when-hashmap-changes
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
    
    public void saveState()throws NotImplementedException{
        throw new NotImplementedException("TODO");
    }
    
    public void restoreState() throws NotImplementedException{
        throw new NotImplementedException("TODO");
    }
}
