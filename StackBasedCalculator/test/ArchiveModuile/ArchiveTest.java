/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package ArchiveModuile;

import ArchiveModule.Archive;
import VariablesManager.VariablesStorage;
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

//    /**
//     * Test of setInstance method, of class Archive.
//     */
//    @Test
//    public void testSetInstance() {
//        System.out.println("setInstance");
//        Archivable instance_2 = null;
//        Archive instance = new Archive();
//        instance.setInstance(instance_2);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of saveState method, of class Archive.
//     */
//    @Test
//    public void testSave() {
//        System.out.println("saveState");
//        Archive instance = new Archive();
//        instance.saveState();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of restoreState method, of class Archive.
//     */
//    @Test
//    public void testRestore() {
//        System.out.println("restoreState");
//        Archive instance = new Archive();
//        instance.restoreState();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of checkLastSave method, of class Archive.
//     */
//    @Test
//    public void testCheckLastSave() {
//        System.out.println("checkLastSave");
//        Archive instance = new Archive();
//        ArchiveItem expResult = null;
//        ArchiveItem result = instance.checkLastSave();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

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
}
