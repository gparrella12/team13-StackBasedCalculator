package Operations;

/**
 * This is an enumeration for all supported operation in the calculator.
 * @author gparrella
 */
public enum OperationsEnum {

    /**
     * Sum operation (+).
     */
    SUM("+"),

    /**
     * Subtraction operation (-).
     */
    SUBTRACTION("-"),

    /**
     * Division operation (/).
     */
    DIVISION("/"),

    /**
     * Product operation (*).
     */
    PRODUCT("*"),

    /**
     * Square root operation (sqrt).
     */
    SQRT("sqrt"),

    /**
     * Invert Sign operation (+-).
     */
    INVSIGN("+-"),

    /**
     * Drop operation (drop).
     */
    DROP("drop"),

    /**
     * Clear operation (clear).
     */
    CLEAR("clear"),

    /**
     * Dup operation (dup).
     */
    DUP("dup"),

    /**
     * Swap operation (swap).
     */
    SWAP("swap"),

    /**
     * Over operation (over).
     */
    OVER("over"),

    /**
     * Push operation (push).
     */
    PUSH("push"),

    /**
     * Save operation ("<").
     */
    LOAD("<var"),

    /**
     * Load operation (">").
     */
    SAVE(">var"),

    /**
     * Add variable operation ("+x").
     */
    SUM_VAR("+var"),

    /**
     * Subtract variable operation ("-x").
     */
    SUB_VAR("-var"),

    /**
     * Save variable state operation ("save").
     */
    SAVE_STATE("save"),

    /**
     * Restore variable state operation ("restore").
     */
    RESTORE_STATE("restore");
    
    private final String value;

    private OperationsEnum(String value) {
        this.value = value;
    }

    /**
     * Returns a string that represents this operation.
     * @return a string that represents this operation.
     */
    @Override
    public String toString() {
        return this.value;
    }
    
    /**
     * Returns a OperationsEnum object given the operation name as string.
     * @param operationName is the name of the operation
     * @return the correct OperationsEnum object
     */
    public static OperationsEnum valueOfString(String operationName){
        for(OperationsEnum op : OperationsEnum.values()){
            if(op.toString().equals(operationName))
                return op;
        }
        throw new UnsupportedOperationException("Operation "+operationName + " not supported");
    }
    
    public static OperationsEnum[] userInvokable(){
        OperationsEnum [] arr ={SUM,SUBTRACTION,PRODUCT,DIVISION,INVSIGN,SQRT,DROP,CLEAR,DUP,SWAP,OVER};
        return arr;
    }

}
