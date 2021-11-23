package MainMathOperation;

import java.util.NoSuchElementException;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.apache.commons.math3.complex.Complex;
import MainMathOperation.ObservableStack.ObservableStackExtended;

/**
 * Implementation of an Reverse Polish Notation Solver for Complex numbers
 * 
 * Use a stack to memorize numbers used in operations
 * 
 * This class follow Singleton
 * @author fsonnessa
 */
public class RPNSolver {
    private static RPNSolver instance = null;
    private ObservableStackExtended<Complex> stack = null;
    
    private RPNSolver(){
        this.stack = new ObservableStackExtended<>();
    }
    
    /**
     * This method return the instance of RPNSolver Singleton class 
     * @return
     */
    public static RPNSolver getInstance(){
        // Create the object only if not exists
        if (instance == null){
            instance = new RPNSolver();
        }
        return instance;
    }
    
    /**
     * Adds the first two elements of the stack and save the reult on top.
     * top = top + (top-1)
     * @throws NoSuchElementException
     */
    public void sum() throws NoSuchElementException{
        
        if (stack.size() > 2){
            throw new NoSuchElementException("Need almost two elements in the stack");
        }
        
        Complex num1 = stack.pop();
        Complex num2 = stack.pop();
        
        stack.push(num1.add(num2));
    }
    
    /**
     * Subtracts the first two elements of the stack and save the reult on top.
     * This operation has fixed oreder of operands: the second element is the left operant while the first element (top element) is the right operand
     * top = (top-1) - top
     * @throws NoSuchElementException
     */
    public void subtraction() throws NoSuchElementException{
        
        if (stack.size() > 2){
            throw new NoSuchElementException("Need almost two elements in the stack");
        }
        
        Complex num1 = stack.pop();
        Complex num2 = stack.pop();
        
        stack.push(num2.subtract(num1));
    }
    
    /**
     * Multiply the first two elements of the stack and save the reult on top.
     * top = top * (top-1)
     * @throws NoSuchElementException
     */
    public void product() throws NoSuchElementException{
        
        if (stack.size() > 2){
            throw new NoSuchElementException("Need almost two elements in the stack");
        }
        
        Complex num1 = stack.pop();
        Complex num2 = stack.pop();
        
        stack.push(num1.multiply(num2));
    }
    
    /**
     * Divides the first two elements of the stack and save the reult on top.
     * This operation has fixed oreder of operands: the second element is the left operant while the first element (top element) is the right operand
     * top = (top-1) / top
     * @throws NoSuchElementException
     */
    public void division() throws NoSuchElementException{
        
        if (stack.size() > 2){
            throw new NoSuchElementException("Need almost two elements in the stack");
        }
        
        Complex num1 = stack.pop();
        Complex num2 = stack.pop();
        
        stack.push(num2.divide(num1));         
    }
    
    /**
     * Make the root of on top element
     * top = sqrt(top)
     * @throws NoSuchElementException
     */
    public void sqrt() throws NoSuchElementException{
        if (stack.size() > 1){
            throw new NoSuchElementException("Need almost one elements in the stack");
        }
        
        Complex num = stack.pop();
        
        stack.push(num.sqrt());
    }
    
    /**
     * Invert the sign of on top number
     * top = top * -1
     * @throws NotImplementedException
     */
    public void invertSign() throws NotImplementedException{
        if (stack.size() > 1){
            throw new NoSuchElementException("Need almost one elements in the stack");
        }
        
        Complex num = stack.pop();
        
        stack.push(num.negate());
    }
    
    /**
     * Push a number in the stack
     * @param num
     * @throws NotImplementedException
     */
    public void addNum(Complex num) throws NotImplementedException{
        stack.push(num);
    }
    
    /**
     * Invoke clear() method of the stack
     * @throws NotImplementedException
     */
    public void clear() throws NotImplementedException{
        throw new NotImplementedException("TODO"); 
    }
    
    /**
     * Invoke drop() method of the stack
     * @throws NotImplementedException
     */
    public void drop() throws NotImplementedException{
        throw new NotImplementedException("TODO"); 
    }
    
    /**
     * Invoke dup() method of the stack
     * @throws NotImplementedException
     */
    public void dup() throws NotImplementedException{
        throw new NotImplementedException("TODO"); 
    }
    
    /**
     * Invoke swap() method of the stack
     * @throws NotImplementedException
     */
    public void swap() throws NotImplementedException{
        throw new NotImplementedException("TODO"); 
    }
    
    /**
     * Invoke over() method of the stack
     * @throws NotImplementedException
     */
    public void over() throws NotImplementedException{
        throw new NotImplementedException("TODO"); 
    }
}
