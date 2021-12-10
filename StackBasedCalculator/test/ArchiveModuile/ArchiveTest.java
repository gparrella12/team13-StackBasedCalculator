package ArchiveModuile;

import ArchiveModule.Archive;
import VariablesManager.VariablesStorage;
import java.util.EmptyStackException;
import org.apache.commons.math3.complex.Complex;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fsonnessa
 */
public class ArchiveTest {
    
    private Archive arc;
    private VariablesStorage vs;
    
    public ArchiveTest() {
    }
    
    @Before
    public void setUp() {
        vs = new VariablesStorage();
        arc = new Archive(vs);
    }

    /**
     * Test of toSave method, of class VariablesStorage.
     */
    @Test
    public void testSaveState() throws Exception {
        System.out.println("saveState");
        
        vs.save("a", new Complex(1,3));
        
//        System.out.println("#Prima:\n" + vs);
        arc.saveState();
//        System.out.println("\n>>Stato salvato:\n" + arc.checkLastSave().getElement().toString());
        assertEquals(vs.toString(), arc.checkLastSave().getElement().toString());
        
        vs.save("a", new Complex(2.3, 0));
        vs.save("b", new Complex(0,0));
//        System.out.println("\n\n#Dopo:\n" + vs);
//        System.out.println("\n>>Stato salvato:\n" + arc.checkLastSave().getElement().toString());
        assertNotEquals(vs.toString(), arc.checkLastSave().getElement().toString());
    }

    /**
     * Test of toRestore method, of class VariablesStorage.
     */
    @Test
    public void testRestoreState() throws Exception {
        System.out.println("restoreState");
        
        vs.save("a", new Complex(1,3));
        String initialState = vs.toString();
        arc.saveState();
        
        vs.save("a", new Complex(2.3, 0));
        vs.save("b", new Complex(0,0));
        assertNotEquals(vs.toString(), initialState);
        
        arc.restoreState();
        assertEquals(vs.toString(), initialState);       
    }
    
    @Test(expected = EmptyStackException.class)
    public void testRestoreStateException(){
        System.out.println("restoreState without state to restore");
        arc.restoreState();
    }
}
