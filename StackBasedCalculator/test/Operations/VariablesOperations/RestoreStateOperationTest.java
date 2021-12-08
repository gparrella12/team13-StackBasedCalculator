package Operations.VariablesOperations;

import ArchiveModule.Archive;
import ArchiveModule.ArchivableState;
import VariablesManager.VariablesStorage;
import org.apache.commons.math3.complex.Complex;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fsonnessa
 */
public class RestoreStateOperationTest {
    
    private Archive archive;
    private VariablesStorage vs;
    private RestoreStateOperation instance;
    
    public RestoreStateOperationTest() {
    }
    
    @Before
    public void setUp() {
        vs = new VariablesStorage();
        archive = new Archive(vs);
        instance = new RestoreStateOperation(archive, vs);
    }

    /**
     * Test of execute method, of class RestoreStateOperation.
     */
    @Test
    public void testExecute() {
        Complex c = new Complex(3.14, 3.14);
        vs.save("a", c);
        
        ArchivableState initialState = vs.getCurrentState();
        archive.saveState();
        
        vs.save("b", new Complex(1,0));
        
        instance.execute();
        
        assertEquals(vs.getCurrentState().getElement(), initialState.getElement());  
    }
    
}
