/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserInterface;

/**
 * CheckInputKeyboard class contains the utility methos to check all the
 * keyboard entries.
 *
 * @author Speranza
 */
public class CheckInputKeyboard {

    private String[] stackOperations = {"dup", "over", "clear", "drop", "swap", "sqrt"};
    private String[] mathOperations = {"+", "-", "*", "/", "sqrt"};

    /**
     * The function checks if the operation inserted in the physical keyborad is
     * supported by the Calculator.
     *
     * @return true is the operation is written in a right way, false otherwise.
     */
    public static boolean checkOperation(String operation) {
        return true;
    }

    /**
     * The function checks if the operand inserted in the physical/on-screen
     * keyboard is in a right format.
     *
     * @return true is the operand is written in a right way, false otherwise.
     */
    public static boolean checkOperand(String operand) {
        return true;
    }

}
