package UserDefinedOperation;

import MainMathOperation.RPNSolver;

/**
 *
 * @author gparrella
 */
public class BasicOperation implements Operation {

    private final static String operations[] = {"+", "-", "*", "/", "sqrt", "+-", "dup", "over", "clear", "drop", "swap"};
    private String name;
    private RPNSolver rpn;

    /**
     *
     * @param name
     */
    public BasicOperation(String name) {
        for (String op : operations) {
            if (name.equals(op)) {
                this.name = name;
                this.rpn = RPNSolver.getInstance();
                return;
            }
        }
        throw new UnsupportedOperationException("Operation : " + name + " not supported.");
    }

    /**
     *
     * @param name
     * @param rpn
     */
    public BasicOperation(String name, RPNSolver rpn) {
        for (String op : operations) {
            if (name.equals(op)) {
                this.name = name;
                this.rpn = rpn;
                return;
            }
        }
        throw new UnsupportedOperationException("Operation : " + name + " not supported.");
    }
    
    /**
     *
     */
    @Override
    public void execute() {
        switch (this.name) {
            case "+" -> {
                rpn.sum();
                return;
            }
            case "-" -> {
                rpn.subtraction();
                return;
            }
            case "*" -> {
                rpn.product();
                return;
            }
            case "/" -> {
                rpn.division();
                return;
            }
            case "sqrt" -> {
                rpn.sqrt();
                return;
            }
            case "+-" -> {
                rpn.invertSign();
                return;
            }
            case "clear" -> {
                rpn.clear();
                return;
            }
            case "dup" -> {
                rpn.dup();
                return;
            }
            case "drop" -> {
                rpn.drop();
                return;
            }
            case "swap" -> {
                rpn.swap();
                return;
            }
            case "over" -> {
                rpn.over();
                return;
            }
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return this.name;
    }
    
    
}
