package miniaesdemo;

import java.security.InvalidParameterException;

class Utilities {
    
    //
    public static String[] stringToStringArray(String stringToConvert) {
        if(stringToConvert.length() != 19 || stringToConvert.charAt(4) != ' ' || stringToConvert.charAt(9) != ' ' || stringToConvert.charAt(14) != ' ')
        {
            throw new InvalidParameterException("String length mismatch.");
        }
        
        return stringToConvert.split("\\s+");
    }

    public static String stringArrayToString(String[] stringArrayToConvert) {
        if(stringArrayToConvert.length != 4)
        {
            throw new InvalidParameterException("Array length mismatch.");
        }

        String convertedString = "";
        
        for(String element : stringArrayToConvert) {
            convertedString += element + " ";
        }

        return convertedString.substring(0, convertedString.length() - 1);
    }

    public static String[] intArrayToStringArray(int[] intToConvert) {
        if(intToConvert.length != 4) {
            throw new InvalidParameterException("Array length mismatch.");
        }

        String[] stringArray = new String[4];

        for(int iter = 0; iter < 4; iter++) {
            stringArray[iter] = formatBinaryString(Integer.toBinaryString(intToConvert[iter]));
            //System.out.println(stringArray[iter]);
        }

        return stringArray;
    }

    public static int[] stringArrayToIntArray(String[] stringToConvert) {
        if(stringToConvert.length != 4) {
            throw new InvalidParameterException("Array length mismatch.");
        }

        int[] intArray = new int[4];

        for(int iter = 0; iter < 4; iter++) {
            intArray[iter] = Integer.parseInt(stringToConvert[iter], 2);
            //System.out.println(intArray[iter]);
        }
        
        return intArray;
    }

    public static String formatBinaryString(String binaryString) {
        int len = binaryString.length();

        if (binaryString.length() < 4) {
            for (int i = 0; i < (4 - len); i++) {
                binaryString = "0" + binaryString;
            }
        }

        return binaryString;
    }

    public static int[] stringToIntArray(String stringToConvert) {
        return stringArrayToIntArray(stringToStringArray(stringToConvert));
    }

    public static String intArrayToString(int[] intArrayToConvert) {
        return stringArrayToString(intArrayToStringArray(intArrayToConvert));
    }
}