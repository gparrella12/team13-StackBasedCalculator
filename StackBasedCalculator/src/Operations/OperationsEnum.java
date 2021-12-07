package Operations;

/**
 *
 * @author gparrella
 */
public enum OperationsEnum {
    SUM("+"), SUBTRACTION("-"), DIVISION("/"), PRODUCT("*"), SQRT("sqrt"), INVSIGN("+-"), DROP("drop"), CLEAR("clear"), DUP("dup"), SWAP("swap"), OVER("over"),PUSH("push"), LOAD("<var"), SAVE(">var"), SUM_VAR("+var"), SUB_VAR("-var");
    
    private String value;

    private OperationsEnum(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }
    
    public static OperationsEnum valueOfString(String str){
        for(OperationsEnum op : OperationsEnum.values()){
            if(op.toString().equals(str))
                return op;
        }
        throw new UnsupportedOperationException("Operation "+str + " not supported");
    }

}
