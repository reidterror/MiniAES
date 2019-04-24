package miniaesdemo;

/**
 * <p>This class contains helper methods used in the MiniAES calculations.</p>
 *
 * @author Team Caligula
 * @version 1.0
 * @since 0.1
 */
public class Utilities {
    /**
     * <p>This method converts a String object to a String Array object of four
     * elements</p>
     *
     * @param stringToConvert A binary string.
     *
     * @return Returns a String Array of four elements.
     */
    public static String[] stringToStringArray(String stringToConvert) {
        //Checking if the imputed String is correct
        if (stringToConvert.length() != 19
                || stringToConvert.charAt(4) != ' '
                || stringToConvert.charAt(9) != ' '
                || stringToConvert.charAt(14) != ' ') {
            System.out.println("String length mismatch.");
        }
        // Returns an Array that is split on every space
        return stringToConvert.split("\\s+");
    }

    /**
     * <p>This method converts a String Array to Integer Array that has the same
     * length.</p>
     *
     * @param stringToConvert A String Array of four elements.
     *
     * @return Returns an Integer Array of four elements.
     */
    public static int[] stringArrayToIntArray(String[] stringToConvert) {
        //Checking if it is the correct length
        if (stringToConvert.length != 4) {
            System.out.println("Array length mismatch.");
        }

        int[] intArray = new int[4];

        for (int i = 0; i < 4; i++) {
            intArray[i] = Integer.parseInt(stringToConvert[i], 2);
            //System.out.println(intArray[i]);
        }
        return intArray;
    }

    /**
     * <p>This method converts a String to Integer Array.</p>
     *
     * @param stringToConvert A binary String.
     *
     * @return Returns a Integer array of four elements.
     */
    public static int[] stringToIntArray(String stringToConvert) {
        return stringArrayToIntArray(stringToStringArray(stringToConvert));
    }

    /**
     * <p>This method converts a Integer Array to String Array.</p>
     *
     * @param intToConvert An Integer Array of four elements.
     *
     * @return Returns a String Array of four elements.
     */
    public static String[] intArrayToStringArray(int[] intToConvert) {

        //Checking if it is the correct length
        if (intToConvert.length != 4) {
            System.out.println("Array length mismatch.");
        }

        String[] stringArray = new String[4];

        for (int i = 0; i < 4; i++) {
            stringArray[i] = formatBinaryString(Integer.toBinaryString(intToConvert[i]));
            //System.out.println(stringArray[i]);
        }

        return stringArray;
    }

    /**
     * <p>This method formats a 4-bit binary string to add padding such that the
     * string is always of length four.</p>
     *
     * @param binaryString A 4-bit binary String.
     *
     * @return Returns a padded 4-bit binary String.
     */
    public static String formatBinaryString(String binaryString) {
        return (String.format("%4s", binaryString).replace(' ', '0'));
    }

    /**
     * <p>This method converts a String Array to String.</p>
     *
     * @param stringArrayToConvert A String Array of four elements.
     *
     * @return Returns a 16-bit binary string.
     */
    public static String stringArrayToString(String[] stringArrayToConvert) {
        //Checking if it is the correct length
        if (stringArrayToConvert.length != 4) {
            System.out.println("Array length mismatch.");
        }

        String convertedString = "";
        //Using an enhanced for-loop to iterate through the Array
        //and assign every elements into a string variable with added spaces
        for (String element : stringArrayToConvert) {
            convertedString += element + " ";
        }

        return convertedString.substring(0, convertedString.length() - 1);
    }

    /**
     * <p>This method converts an Integer Array to a 16-bit binary String.</p>
     *
     * @param intArrayToConvert An Integer Array of four elements.
     *
     * @return Returns a 16-bit binary String.
     */
    public static String intArrayToString(int[] intArrayToConvert) {
        return stringArrayToString(intArrayToStringArray(intArrayToConvert));
    }

    /**
     * <p>This method converts a Integer Array to a Hexadecimal String.</p>
     *
     * @param intArray An Integer Array.
     *
     * @return Returns a Hexadecimal String.
     */
    public static String intArrayToHexString(int[] intArray) {
        String output = "";

        for (int iter = 0; iter < intArray.length; iter++) {
            output += String.format("%02x", intArray[iter]);
        }

        return output;
    }
    
    /**
     * <p>This method converts a Integer Array to a Hexadecimal String.</p>
     *
     * @param hexString A Hexadecimal String .
     *
     * @return Returns An Integer Array.
     */
    public static int[] hexStringToIntArray(String hexString) {
        String[] hexStringArray = hexString.split("(?<=\\G..)");
        int HEX_STRING_LENGTH = hexStringArray.length;
        int[] output = new int[HEX_STRING_LENGTH];

        for (int iter = 0; iter < HEX_STRING_LENGTH; iter++) {
            output[iter] = Integer.parseInt(hexStringArray[iter], 16);
        }

        return output;
    }
    
    /**
     * <p>This method segments input data and pads it before its sent for encryption it. See <b><u>{@link MiniAES#EncryptText(java.lang.String, java.lang.String)}</u></b> method.</p>
     *
     * @param inputData A string containing the input data provided by the user.
     *
     * @return Returns an Integer Matrix of the input data split into rows of 4 characters, the last one may contain padding.
     */
    public static int[][] segmentAndPadData(String inputData) {
        final int inputDataLength = inputData.length();
        final int numOfSegments = (int) Math.ceil(inputDataLength / 4.0);
        final int paddingSize = (4 - (inputDataLength % 4));

        int byteDataIndicator = 0;
        int[] byteData = new int[inputDataLength];

        for (int i = 0; i < inputDataLength; i++) {
            byteData[i] = (int) inputData.charAt(i);
        }

        int[][] segmentedData = new int[numOfSegments][4];

        for (int row = 0; row < numOfSegments; row++) {
            for (int col = 0; col < 4; col++) {
                if (byteDataIndicator < inputDataLength) {
                    segmentedData[row][col] = byteData[byteDataIndicator];
                } else {
                    segmentedData[row][col] = paddingSize;
                }

                byteDataIndicator++;
            }
        }

        return segmentedData;
    }

    /**
     * <p>This method segments input data before its sent for decryption it. See <b><u>{@link MiniAES#EncryptText(java.lang.String, java.lang.String)}</u></b> method.</p>
     *
     * @param inputData An integer array containing the input data provided by the user.
     *
     * @return Returns an Integer Matrix of the input data split into rows of 4 characters.
     */
    public static int[][] segmentData(int[] inputData) {
        final int inputDataLength = inputData.length;
        final int numOfSegments = inputDataLength / 4;

        int DataIndicator = 0;
        int[][] segmentedData = new int[numOfSegments][4];

        for (int row = 0; row < numOfSegments; row++) {
            for (int col = 0; col < 4; col++) {
                segmentedData[row][col] = inputData[DataIndicator];
                DataIndicator++;
            }
        }

        return segmentedData;
    }

    /**
     * <p>This method removes the padding from the last segment of the input data before its. See <b><u>{@link MiniAES#DecryptText(java.lang.String, java.lang.String)}</u></b> method.</p>
     *
     * @param paddedInput An integer array containing the last segment of the input which might contain padding.
     *
     * @return Returns an Integer matrix of the input data with the padding removed from it.
     */
    public static int[] removePadding(int[] paddedInput) {
        int checkPadding = paddedInput[paddedInput.length - 1];

        if (checkPadding == 1 || checkPadding == 2 || checkPadding == 3) {
            int[] cleanInput = new int[4 - checkPadding];

            for (int iter = 0; iter < cleanInput.length; iter++) {
                cleanInput[iter] = paddedInput[iter];
            }

            return cleanInput;
        } else {
            return paddedInput;
        }
    }

    /**
     * <p>This method displays Team Caligula's ascii logo. Crafted by Petar Baltov.</p>
     */
    public static void printLogo() {
        System.out.println(""
                + "   _______                         _____        _  _                _        \n"
                + "  |__   __|                       / ____|      | |(_)              | |       \n"
                + "     | |  ___   __ _  _ __ ___   | |      __ _ | | _   __ _  _   _ | |  __ _ \n"
                + "     | | / _ \\ / _` || '_ ` _ \\  | |     / _` || || | / _` || | | || | / _` |\n"
                + "     | ||  __/| (_| || | | | | | | |____| (_| || || || (_| || |_| || || (_| |\n"
                + "     |_| \\___| \\__,_||_| |_| |_|  \\_____|\\__,_||_||_| \\__, | \\__,_||_| \\__,_|\n"
                + "                                                       __/ |                 \n"
                + "                                                      |___/                  ");
    }
}
