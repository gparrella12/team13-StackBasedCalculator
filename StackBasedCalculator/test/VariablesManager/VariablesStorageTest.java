package VariablesManager;

import UserInterface.InputValidation;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexFormat;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fsonnessa
 */
public class VariablesStorageTest {
    
    private VariablesStorage vs = new VariablesStorage();
//    private HashMap<String, Complex> tmpHash = new HashMap<>();
    
    /**
     * Test of save method, of class VariablesStorage.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        
        Scanner sc = new Scanner(new InputStreamReader(InputValidation.class.getResourceAsStream("saveInput.csv")));
        System.out.println("WEEEEEEEEE");
        sc.nextLine();
        sc.useDelimiter(";");
        
        ComplexFormat cf = new ComplexFormat();
        HashMap<String, Complex> tmpHash = new HashMap<>();
        boolean exceptionFlag = false;
        String name, value, result;
        
        while (sc.hasNext()){
            name = sc.next().replace("\n", "").replace("\r", "").toLowerCase();
            value = sc.next().replace("\n", "").replace("\r", "");
            result = sc.next().replace("\n", "").replace("\r", "");
            
            System.out.print("name: " + name + "\tvalue: " + value + "\tresult: " + result);
            
            if (result.equals("ok")){
                vs.save(name, cf.parse(value));
                tmpHash.put(name, cf.parse(value));
                System.out.println(" >> ok");
            }
            
            if (result.equals("error")){
                try {
                    vs.save(name, cf.parse(value));
                } catch (IllegalArgumentException argEx){
                    System.out.println(" >> arg error");
                    exceptionFlag = true;
                }
                if (!exceptionFlag) {
                    throw new RuntimeException(">> Attention! this input < " + name + " = " + value + " > not fail!");
                }
                exceptionFlag = false;
            }
            
            if (result.equals("update")){
                vs.save(name, cf.parse(value));
                Complex tmp = vs.getVariableValue(name);                
                if (tmpHash.get(name).equals(tmp)){
                    System.out.println(" >> update fail");
                    throw new RuntimeException("input < " + name + " = " + value + " > update fail");
                }                
                System.out.println(" >> update ok");
            }
        }
    }

//    /**
//     * Test of getVariableValue method, of class VariablesStorage.
//     */
//    @Test
//    public void testGetVariableValue() {
//        System.out.println("getVariableValue");
//        String name = "";
//        VariablesStorage instance = new VariablesStorage();
//        Complex expResult = null;
//        Complex result = instance.getVariableValue(name);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of removeVariable method, of class VariablesStorage.
//     */
//    @Test
//    public void testRemoveVariable() {
//        System.out.println("removeVariable");
//        String name = "";
//        VariablesStorage instance = new VariablesStorage();
//        instance.removeVariable(name);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addToVariable method, of class VariablesStorage.
//     */
//    @Test
//    public void testAddToVariable() {
//        System.out.println("addToVariable");
//        String name = "";
//        Complex value = null;
//        VariablesStorage instance = new VariablesStorage();
//        instance.addToVariable(name, value);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of subFromVariable method, of class VariablesStorage.
//     */
//    @Test
//    public void testSubFromVariable() {
//        System.out.println("subFromVariable");
//        String name = "";
//        Complex value = null;
//        VariablesStorage instance = new VariablesStorage();
//        instance.subFromVariable(name, value);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

//    /**
//     * Test of saveState method, of class VariablesStorage.
//     */
//    @Test
//    public void testSaveState() throws Exception {
//        System.out.println("saveState");
//        VariablesStorage instance = new VariablesStorage();
//        instance.saveState();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of restoreState method, of class VariablesStorage.
//     */
//    @Test
//    public void testRestoreState() throws Exception {
//        System.out.println("restoreState");
//        VariablesStorage instance = new VariablesStorage();
//        instance.restoreState();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
