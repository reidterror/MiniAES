/**
 *  <h1>This class contains handy helper functions.</h1>
 *
 *  @author Team Caligula
 *  @version 1.0
 *  @since   02.04.2019
 */

package miniaesdemo;

import java.lang.Integer;

public class Utilities {

    //==================================================================================================
    // From String to Integer Array ====================================================================
    //==================================================================================================
    
    /**
     *  <h2>Conversion from String to String Array</h2>
     *  <p>This method converts a String object to a String Array object of four elements</p>
     *
     * @param stringToConvert A binary string.
     *
     * @return Returns a String Array of four elements.
     */
    public static String[] stringToStringArray(String stringToConvert) {
        //Checking if the imputed String is correct
        if (stringToConvert.length() != 19 || 
            stringToConvert.charAt(4) != ' ' || 
            stringToConvert.charAt(9) != ' ' ||
            stringToConvert.charAt(14) != ' ') {
            System.out.println("String length mismatch.");
        }
        // Returns an Array that is split on every space
        return stringToConvert.split("\\s+");
    }
    
    /**
     *  <h2>Conversion from String Array to Integer Array</h2>
     * 
     *  <p> This method converts a String Array to Integer Array
     *     that has the same length.</p>
     *
     * @param stringToConvert A String Array of four elements.
     *
     * @return Returns an Integer Array of four elements.
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
    
    /**
     *  <h2>Conversion from String to Integer Array</h2>
     * 
     *  <p> This method converts a String to Integer Array.</p>
     *
     * @param stringToConvert A binary String.
     *
     * @return  Returns a Integer array of four elements.
     */
    public static int[] stringToIntArray(String stringToConvert) {
        return stringArrayToIntArray(stringToStringArray(stringToConvert));
    }

    //==================================================================================================
    // From Integer Array to String ====================================================================
    //==================================================================================================
    
    /**
     * <h2>Conversion from Integer Array to String Array</h2>
     * 
     * <p> This method converts a Integer Array to String Array.</p>
     *
     * @param intToConvert An Integer Array of four elements.
     *
     * @return Returns a String Array of four elements.
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
    
    /**
     * <h2>Formatting a binary string</h2>
     * 
     * <p> This method formats a 4-bit binary string to add padding such that the string is always of length four.</p>
     *
     * @param binaryString A 4-bit binary String.
     *
     * @return Returns a padded 4-bit binary String.
     */
    public static String formatBinaryString(String binaryString) {
        return (String.format("%4s", binaryString).replace(' ', '0'));
    }
    
    /**
     *  <h2>Conversion from String Array to String</h2>
     *  <p>This method converts a String Array to String.</p>
     *
     * @param stringArrayToConvert A String Array of four elements.
     *
     * @return Returns a 16-bit binary string.
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
    
    /**
     *  <h2>Conversion from Integer Array to String</h2>
     *  
     *  <p> This method converts an Integer Array to a 16-bit binary String.</p>
     *
     * @param intArrayToConvert An Integer Array of four elements.
     *
     * @return Returns a 16-bit binary String.
     */
    public static String intArrayToString(int[] intArrayToConvert) {
        return stringArrayToString(intArrayToStringArray(intArrayToConvert));
    }
    
    /**
     *  <h2>Team Caligula Logo</h2>
     *  
     *  <p> This method displays Team Caligula's ascii logo. Crafted by Petar Baltov.</p>
     */
    public static void printLogo() {
        System.out.println("" +
                    "   _______                         _____        _  _                _        \n" +
                    "  |__   __|                       / ____|      | |(_)              | |       \n" +
                    "     | |  ___   __ _  _ __ ___   | |      __ _ | | _   __ _  _   _ | |  __ _ \n" +
                    "     | | / _ \\ / _` || '_ ` _ \\  | |     / _` || || | / _` || | | || | / _` |\n" +
                    "     | ||  __/| (_| || | | | | | | |____| (_| || || || (_| || |_| || || (_| |\n" +
                    "     |_| \\___| \\__,_||_| |_| |_|  \\_____|\\__,_||_||_| \\__, | \\__,_||_| \\__,_|\n" +
                    "                                                       __/ |                 \n" +
                    "                                                      |___/                  ");
    }
}