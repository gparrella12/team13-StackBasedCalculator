package Operations.StateOperations;

import Operations.StateOperation.SaveStateOperation;
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
public class SaveStateOperationTest {
    
    private Archive archive;
    private VariablesStorage vs;
    private SaveStateOperation instance;
    
    public SaveStateOperationTest() {
    }
    
    @Before
    public void setUp() {
        vs = new VariablesStorage();
        archive = new Archive(vs);
        instance = new SaveStateOperation(archive);
    }

    /**
     * Test of execute method, of class SaveStateOperation.
     */
    @Test
    public void testExecute() {
        Complex c = new Complex(3.14, 3.14);
        vs.save("a", c);
        
        ArchivableState e = vs.getCurrentState();        
        instance.execute();
        
        assertEquals(e.getElement(), archive.checkLastSave().getElement());        
    }
}
