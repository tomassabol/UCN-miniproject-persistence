package view;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class Input {
    
    /**
     * Field for class Input
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Constructor for class input
     */
    public Input() {}

    /**
     * Takes a String input from the user
     * @param input The input from the user
     * @return The input
     */
    public String stringInput(String input) {
        System.out.println(input);
        String newInput = scanner.nextLine();
        return newInput;
    }

    /**
     * Takes in an integer input from the user
     * @param input The input from the user
     * @return The input, or NumberFormatExveption if the input is not an integer
     */
    public int integerInput(String input) {
        //System.out.println(input);
        String var = stringInput(input);
        int newInput = 0;
        try {
            newInput = Integer.parseInt(var);
        } catch(NumberFormatException e) {
            e.printStackTrace();
        }
        
        return newInput;
    }

    /**
     * Takes a BigDecimal input from the user
     * @param input The input from the user
     * @return The input
     */
    public BigDecimal bigDecimalInput(String input) {
        int var = integerInput(input);
        BigDecimal newInput = BigDecimal.valueOf(var);
        return newInput;
    }
}
