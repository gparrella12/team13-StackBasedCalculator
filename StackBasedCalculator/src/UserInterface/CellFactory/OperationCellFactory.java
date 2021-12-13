package UserInterface.CellFactory;

import Operations.Operation;
import Operations.VariablesOperation;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * This class provides an implementation of a CellFactory for
 * SupportedOperations.
 *
 * In this case, if the list cell is not empty, the name of the operation is
 * shown.
 *
 * @author gparrella
 * @see Operations.SupportedOperation
 */
public class OperationCellFactory implements Callback<ListView<Operation>, ListCell<Operation>> {

    @Override
    public ListCell<Operation> call(ListView<Operation> param) {
        return new ListCell<Operation>() {
            @Override
            public void updateItem(Operation operation, boolean empty) {
                super.updateItem(operation, empty);
                if (!empty) {
                    setText(operation.toString());
                    if (operation instanceof VariablesOperation) {
                        setText(getText().replace("null", "var") + "Name");
                    }
                }
            }
        };
    }
;
}
