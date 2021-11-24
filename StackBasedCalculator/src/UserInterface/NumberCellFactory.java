package UserInterface;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.util.Precision;

/**
 * A list cell factory for Complex Number.
 * 
 * Implement the callback's functional interface in order to override the ListCell method for 
 * display a complex number C(real,image) in the following way:
 * <p>
 * real + image<code>j</code>
 * <p>
 * where <code>j</code> is the imaginary unit.
 * <p>
 * A complex number of <code>Complex</code> class is used.
 * 
 * @see org.apache.commons.math3.complex.Complex 
 * @author gparrella
 */
public class NumberCellFactory implements Callback<ListView<Complex>, ListCell<Complex>> {

    @Override
    public ListCell<Complex> call(ListView<Complex> param) {
        return new ListCell<>() {
            @Override
            public void updateItem(Complex number, boolean empty) {
                super.updateItem(number, empty);
                if (empty || number == null) {
                    // If the cell is empty or number is null, then the visualized text is empy
                    setText(null);
                } else {
                    
                    if (number.getImaginary() < 0) {// If imaginary part is less than 0, then not display the '-' in the text
                        setText(Precision.round(number.getReal(), 3) + Precision.round(number.getImaginary(), 3) + "j");
                    } else { // Otherwise, include '+' in the text
                        setText(Precision.round(number.getReal(), 3) + "+" + Precision.round(number.getImaginary(), 3) + "j");
                    }
                }
            }
        };
    }
}
