package UserDefinedOperation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**
 *
 * @author gparrella
 */
public class UserDefinedOperations {

    private ObservableList<UserDefinedOperation> list;

    /**
     *
     */
    public UserDefinedOperations() {
        this.list = FXCollections.observableArrayList();
    }

    /**
     *
     * @param operation
     */
    public void insertOperation(UserDefinedOperation operation) {
        if (!list.contains(operation)) {
            list.add(operation);
        }
    }

    /**
     *
     * @param operation
     */
    public void removeOperation(UserDefinedOperation operation) {
        this.list.remove(operation);
    }
    
    /**
     *
     */
    public void updateOperation(){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param listView
     */
    public void setObserbable(ListView<UserDefinedOperation> listView) {
        listView.setItems(list);
    }
}
