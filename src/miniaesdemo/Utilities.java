package miniaesdemo;

import java.lang.Integer;

/**
 *  <h1>Utilities class used for convection between different values and variables </h1>
 *
 *  @author Team Caligula
 *  @version 0.1
 *  @since   02.04.2019
 */
public class Utilities {

    //==================================================================================================
    // From String to Integer Array ====================================================================
    //==================================================================================================
    /**
     *  <h2>String to String Array</h2>
     *  <p>A method that converts a string to a string array of four elements</p>
     *
     * @param stringToConvert A binary string of 4 nibbles.
     *
     * @return Returns an Array that is split on every space in four elements.
     */
    public static String[] stringToStringArray(String stringToConvert) {
        //Checking if the imputed String is correct
        if (stringToConvert.length() != 19 || stringToConvert.charAt(4) != ' '
                || stringToConvert.charAt(9) != ' ' || stringToConvert.charAt(14) != ' ') {
            System.out.println("String length mismatch.");
        }
        // Returns an Array that is split on every space
        return stringToConvert.split("\\s+");
    }
    //------------------------------------------------------------------------------------------------------

    /**
     *  <h2>String Array to Integer Array</h2>
     *  <p> Methods that converts a string array to integer array
     *     that has the same length. In the methods there is a
     *     if statement to check the correct length and a loop that
     *     goes through the string array and assigns every element
     *     to a new array by also converting the elements to integers</p>
     *
     * @param stringToConvert A string array of four elements.
     *
     * @return Returns an integer array of four elements.
     */
    public static int[] stringArrayToIntArray(String[] stringToConvert) {
        //Checking if it is the correct length
        if(stringToConvert.length != 4) {
            System.out.println("Array length mismatch.");
        }

        int[] intArray = new int[4];

        for(int i = 0; i< 4; i++) {
            intArray[i] = Integer.parseInt(stringToConvert[i], 2);
            //System.out.println(intArray[i]);
        }
        return intArray;
    }
    //------------------------------------------------------------------------------------------------------

    /**
     *  <h2>String to Integer Array </h2>
     *  <p>Method that uses the previous methods to simplify the process of
     *     converting the binary string to an integer array</p>
     *
     * @param stringToConvert A binary string of 4 nibbles.
     *
     * @return  Returns an array of integers using helper
     *          methods that were created previously
     */
    public static int[] stringToIntArray(String stringToConvert) {
        return stringArrayToIntArray(stringToStringArray(stringToConvert));
    }


    //==================================================================================================
    // From Integer Array to String ====================================================================
    //==================================================================================================
    /**
     * <h2>Integer Array to String Array</h2>
     * <p>Methods that converts an integer array to string array
     *    that has the same length. In the methods there is a
     *    if statement to check the correct length and a loop that
     *    goes through the integer array and assigns every element
     *    to a new array by also converting the elements using the <b>formatBinary</b> method.</p>
     *
     * @param intToConvert An integer array of four elements.
     *
     * @return Returns a string array of four elements.
     */
    public static String[] intArrayToStringArray(int[] intToConvert) {

        //Checking if it is the correct length
        if(intToConvert.length != 4) {
            System.out.println("Array length mismatch.");
        }

        String[] stringArray = new String[4];

        for(int i = 0; i < 4; i++) {
            stringArray[i] = formatBinaryString(Integer.toBinaryString(intToConvert[i]));
            //System.out.println(stringArray[i]);
        }

        return stringArray;
    }
    //------------------------------------------------------------------------------------------------------

    /**
     * <h2>Formatting the binary string</h2>
     * <p>The methods is used to correctly assign a binary string with the
     *    addition of zeros depending of the deficiency in every nibble until
     *    they reach a length of four </p>
     *
     * @param binaryString A binary string of length smaller or equal to four
     *
     * @return Returns a newly created string of length four.
     */
    public static String formatBinaryString(String binaryString) {
        int bitLength = binaryString.length();
        //Checking to see if the length is correct
        if (binaryString.length() < 4) {
            //If the length is correct then it goes through
            //the nibble and assigns zeros until its length reaches four
            for (int i = 0; i < (4 - bitLength); i++) {
                binaryString = "0" + binaryString;
            }
        }
        return binaryString;
    }
    //------------------------------------------------------------------------------------------------------

    /**
     *  <h2>String Array to String</h2>
     *  <p>A method that converts a string array of four elements
     *     to string using an enhanced for loop.</p>
     *
     * @param stringArrayToConvert A string array of four elements.
     *
     * @return Returns a binary string of 4 nibbles.
     */
    public static String stringArrayToString(String[] stringArrayToConvert) {
        //Checking if it is the correct length
        if(stringArrayToConvert.length != 4)
        {
            System.out.println("Array length mismatch.");
        }

        String convertedString = "";
        //Using an enhanced for-loop to iterate through the Array
        //and assign every elements into a string variable with added spaces
        for(String element : stringArrayToConvert) {
            convertedString += element + " ";
        }

        return convertedString.substring(0, convertedString.length() - 1);
    }
    //------------------------------------------------------------------------------------------------------

    /**
     *  <h2>Integer Array to String</h2>
     *  <p>Method that uses the previous methods to simplify the process of
     *     converting the integer array to a string</p>
     *
     * @param intArrayToConvert An integer array of four elements.
     *
     * @return Returns a binary string of 4 nibbles.
     */
    public static String intArrayToString(int[] intArrayToConvert) {
        return stringArrayToString(intArrayToStringArray(intArrayToConvert));
    }
}