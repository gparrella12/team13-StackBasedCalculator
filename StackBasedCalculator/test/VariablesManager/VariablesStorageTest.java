package VariablesManager;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
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
        System.out.println("\nsave");

        Scanner sc = new Scanner(new InputStreamReader(VariablesStorageTest.class.getResourceAsStream("inputCSV.csv")));
        sc.nextLine();
        sc.useDelimiter(";");

        ComplexFormat cf = new ComplexFormat();
        HashMap<String, Complex> tmpHash = new HashMap<>();
        boolean exceptionFlag = false;
        String name, value, result;

        while (sc.hasNext()) {
            name = sc.next().replace("\n", "").replace("\r", "").toLowerCase();
            value = sc.next().replace("\n", "").replace("\r", "").replace("j", "i");
            result = sc.next().replace("\n", "").replace("\r", "");

            System.out.print("name: " + name + " \tvalue: " + value + " \tresult: " + result);

            if (result.equals("ok")) {
                vs.save(name, cf.parse(value));
                tmpHash.put(name, cf.parse(value));
                System.out.println(" >> ok");
            } else if (result.equals("error")) {
                try {
                    vs.save(name, cf.parse(value));
                } catch (IllegalArgumentException argEx) {
                    System.out.println(" >> arg error");
                    exceptionFlag = true;
                }
                if (!exceptionFlag) {
                    throw new RuntimeException(">> Attention! this input < " + name + " = " + value + " > not fail!");
                }
                exceptionFlag = false;
            } else if (result.equals("update")) {
                vs.save(name, cf.parse(value));
                Complex tmp = vs.getVariableValue(name);
                Complex stored = tmpHash.get(name);

                if (stored == null) {
                    System.out.println(" >> update fail");
                    throw new RuntimeException("input < " + name + " = " + value + " >> update fail - support structure error");
                }

                if (stored.equals(tmp)) {
                    System.out.println(" >> update fail");
                    throw new RuntimeException("input < " + name + " = " + value + " >> update fail");
                }
                System.out.println(" >> update ok");
            }
        }
        vs.clear();
    }

    /**
     * Test of getVariableValue method, of class VariablesStorage.
     */
    @Test(expected = NoSuchElementException.class)
    public void testGetVariableValue() {
        System.out.println("\ngetVariableValue");
        
        vs.save("a", new Complex(1,2));
        vs.removeVariable("a");
        vs.getVariableValue("a");
        vs.clear();
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testRemoveVariable() {
        System.out.println("\nremoveVariable");        
        vs.removeVariable("a");
    }

    /**
     * Test of addToVariable method, of class VariablesStorage.
     */
    @Test
    public void testAddToVariable() {
        System.out.println("\naddToVariable");
        
        Complex a = new Complex(1,2);
        Complex b = new Complex(1,2).negate();
        Complex result;
        
        vs.save("a", a);
        vs.addToVariable("a", a);
        result = a.add(a);
        assertEquals(result, vs.getVariableValue("a"));
        
        vs.addToVariable("a", b);
        result = result.add(b);
        assertEquals(result, vs.getVariableValue("a"));
        
        vs.addToVariable("a", b);
        result = result.add(b);
        assertEquals(result, vs.getVariableValue("a"));
        
        vs.clear();
    }

    /**
     * Test of subFromVariable method, of class VariablesStorage.
     */
    @Test
    public void testSubFromVariable() {
        System.out.println("\naddToVariable");
        
        Complex a = new Complex(1,2);
        Complex b = new Complex(1,2).negate();
        Complex result;
        
        vs.save("a", a);
        vs.subFromVariable("a", a);
        result = a.subtract(a);
        assertEquals(result, vs.getVariableValue("a"));
        
        vs.subFromVariable("a", b);
        result = b.subtract(result);
        assertEquals(result, vs.getVariableValue("a"));
        
        vs.subFromVariable("a", b);
        result = b.subtract(result);
        assertEquals(result, vs.getVariableValue("a"));
    }

    /**
     * Test of saveState method, of class VariablesStorage.
     */
    @Test
    public void testSaveState() throws Exception {
        System.out.println("saveState");
        
        vs.save("a", new Complex(1,3));
        
        vs.saveState();
        assertEquals(vs.toString(), vs.chekLastSavedState().getElement().toString());
        
        vs.save("a", new Complex(2.3, 0));
        vs.save("b", new Complex(0,0));
        assertNotEquals(vs.toString(), vs.chekLastSavedState().getElement().toString());
    }

    /**
     * Test of restoreState method, of class VariablesStorage.
     */
    @Test
    public void testRestoreState() throws Exception {
        System.out.println("restoreState");
        
        vs.save("a", new Complex(1,3));
        String initialState = vs.toString();
        vs.saveState();
        
        vs.save("a", new Complex(2.3, 0));
        vs.save("b", new Complex(0,0));
        assertNotEquals(vs.toString(), initialState);
        
        vs.restoreState();
        assertEquals(vs.toString(), initialState);       
    }
}
