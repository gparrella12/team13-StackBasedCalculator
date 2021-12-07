package Operations;

/**
 * This interface qualifies as Operation the objects of each class that implements it.
 * In particular, this classes must implement the execute() method, that specify how the 
 * operation is executed in the calculator.
 * 
 * @author gparrella
 */
public interface Operation {

    /**
     * Execute the operation in the calculator
     */
    public void execute();
}
