/**
 * <h1>The Mini-AES class implements a minified version of the AES encryption algorithm.</h1>
 *
 * @author  Team Caligula
 * @version 1.0
 * @since   02.04.2019
 */

package miniaesdemo;

import javafx.util.Pair;

class MiniAES {

    /**
     * <h2>AddRoundKey</h2>
     * 
     * <p>This method applies a given round key to the provided plaintext and then returns it.</p>
     *
     * @param plainText An Integer Array of four elements that stores the plaintext.
     *
     * @param key An Integer Array of four elements that stores a single key.
     *
     * @return Returns a resulting ciphertext integer array with the key applied to it.
     */
    private static int[] addRoundKey(int[] plainText, int[] key) {
        int[] result = new int[4];
        //Loop that goes through every part of the plainText Array
        //Using XOR the RoundKey is added to every part of the Array
        for(int iter = 0; iter < 4; iter++) {
            result[iter] = plainText[iter] ^ key[iter];
        }
        // End of Loop

        return result;
    }
    
    /**
     * <h2>NibbleSub</h2>
     * 
     * <p>This method substitutes all of the four elements of the provided plaintext integer array
     * with corresponding values in the nibble substitution table.</p>
     *
     * @param plainText An Integer Array of four elements that stores the plaintext.
     *
     * @return Returns a resulting ciphertext integer array with the values substituted.
     */
    private static int[] nibbleSub(int[] plainText) {
        int[] result = new int[4];
        //The Loop goes through the NibbleSub Table
        // and substitutes every part of the plainText Array
        for(int iter = 0; iter < 4; iter++) {
            result[iter] = Tables.getNibbleSubValue(plainText[iter]);
        }
        // End of Loop

        return result;
    }
    
    /**
     * <h2>InverseNibbleSub</h2>
     * 
     * <p>This method substitutes all of the four elements of the provided plaintext integer array
     * with inverted values in the nibble substitution table.</p>
     *
     * @param plainText An Integer Array of four elements that stores the plaintext.
     *
     * @return Returns a resulting ciphertext integer array with the values substituted.
     */
    private static int[] inverseNibbleSub(int[] plainText) {
        int[] result = new int[4];
        //Iterates through the Inverse NibbleSub table
        // to get the values that are replaced in the plain Text
        // and returns the variable "result"
        for(int iter = 0; iter < 4; iter++) {
            result[iter] = Tables.getInverseNibbleSubValue(plainText[iter]);
        }
        //End of Loop

        return result;
    }
    
    private static int[] subBytes(int[] plainText) {
        int[] result = new int[4];
        //The Loop goes through the NibbleSub Table
        // and substitutes every part of the plainText Array
        for(int iter = 0; iter < 4; iter++) {
            result[iter] = Tables.getSBoxValue(plainText[iter]);
        }
        // End of Loop

        return result;
    }
    
    private static int[] inverseSubBytes(int[] plainText) {
        int[] result = new int[4];
        //The Loop goes through the NibbleSub Table
        // and substitutes every part of the plainText Array
        for(int iter = 0; iter < 4; iter++) {
            result[iter] = Tables.getInverseSBoxValue(plainText[iter]);
        }
        // End of Loop

        return result;
    }
    
    /**
     * <h2>ShiftRows</h2>
     * 
     * <p>This method swaps the last two elements of the plaintext.</p>
     *
     * @param plainText An Integer Array of four elements that stores the plaintext.
     *
     * @return Returns a resulting ciphertext integer array with the last two elements swapped.
     */
    private static int[] shiftRows(int[] plainText) {
        int value1 = plainText[2];
        int value2 = plainText[3];

        // Shifts the last two Nibbles
        plainText[2] = value2;
        plainText[3] = value1;

        return plainText;
    }
    
    /**
     * <h2>MixColumns</h2>
     * 
     * <p>This method multiplies each element by an element of a constant array, the result of
     * this multiplication is retrieved from the mixcolumn multiplication table in the <b>Tables</b> class.</p>
     *
     * @param plainText An Integer Array of four elements that stores the plaintext.
     *
     * @return Returns a resulting ciphertext integer array with constant array applied to it.
     */
    private static int[] mixColumns(int[] plainText) {
        final int[] constMatrix = new int[]{3, 2, 2, 3};

        int[] result = {

                /*
                * Using Multiplication table and the constant matrix in the MixColumns Section
                * every nibble of the plain Text Array is assign with new value(XOR).
                */
                (Tables.getMixColumnValue(constMatrix[0], plainText[0]) ^
                                         (Tables.getMixColumnValue(constMatrix[2], plainText[2]))),

                (Tables.getMixColumnValue(constMatrix[3], plainText[1]) ^
                                         (Tables.getMixColumnValue(constMatrix[1], plainText[3]))),

                (Tables.getMixColumnValue(constMatrix[2], plainText[0]) ^
                                         (Tables.getMixColumnValue(constMatrix[0], plainText[2]))),

                (Tables.getMixColumnValue(constMatrix[1], plainText[1]) ^
                                         (Tables.getMixColumnValue(constMatrix[3], plainText[3])))
        };

        return result;
    }
    
    private static int[] mixColumnsAES(int[] plainText) {
        int[] result = {
                (Tables.getMUL2Value(plainText[0]) ^ Tables.getMUL3Value(plainText[1]) ^ plainText[2] ^ plainText[3]),
                (plainText[0] ^ Tables.getMUL2Value(plainText[1]) ^ Tables.getMUL3Value(plainText[2]) ^ plainText[3]),
                (plainText[0] ^ plainText[1] ^ Tables.getMUL2Value(plainText[2]) ^ Tables.getMUL3Value(plainText[3])),
                (Tables.getMUL3Value(plainText[0]) ^ plainText[1] ^ plainText[2] ^ Tables.getMUL2Value(plainText[3]))
        };

        return result;
    }
    
    private static int[] inverseMixColumnsAES(int[] cipherText) {
        int[] result = {
                (Tables.getMUL14Value(cipherText[0]) ^ Tables.getMUL11Value(cipherText[1]) ^ Tables.getMUL13Value(cipherText[2]) ^ Tables.getMUL9Value(cipherText[3])),
                (Tables.getMUL9Value(cipherText[0]) ^ Tables.getMUL14Value(cipherText[1]) ^ Tables.getMUL11Value(cipherText[2]) ^ Tables.getMUL13Value(cipherText[3])),
                (Tables.getMUL13Value(cipherText[0]) ^ Tables.getMUL9Value(cipherText[1]) ^ Tables.getMUL14Value(cipherText[2]) ^ Tables.getMUL11Value(cipherText[3])),
                (Tables.getMUL11Value(cipherText[0]) ^ Tables.getMUL13Value(cipherText[1]) ^ Tables.getMUL9Value(cipherText[2]) ^ Tables.getMUL14Value(cipherText[3]))
        };

        return result;
    }
    
    /**
     * <h2>Encrypt</h2>
     * 
     * <p>This method encapsulates the operations done
     * in the encryption process of the Mini-AES. The process is broken down into a few steps:</p>
     * 
     * <ol>
     *     <li><b>AddRoundKey</b>: Adding the initial key to the plain text.</li>
     * 
     *     <li><b>NibbleSub</b>: Substituting the values in the result of the
     *     (<b>AddRoundKey</b>) step with values from the NibbleSub table found in the <b>Tables</b> class.</li>
     *     
     *     <li><b>ShiftRows</b>: Shifting the last two elements of the result of
     *     the (<b>NibbleSub</b>) step.</li>
     *     
     *     <li><b>MixColumn</b>: Multiplying the result the (<b>ShiftRows</b>) step
     *     by a constant array.</li>
     *     
     *     <li><b>AddRoundKey1</b>: Adding the first round key to the result of the (<b>MixColumn</b>) step.</li>
     *     
     *     <li><b>NibbleSub</b>: Substituting the values in the result of the
     *     (<b>AddRoundKey1</b>) step with values from the NibbleSub table found in the <b>Tables</b> class.</li>
     *     
     *     <li><b>ShiftRows</b>: Shifting the last two elements of the result of
     *     the (<b>NibbleSub</b>) step.</li>
     *
     *     <li><b>AddRoundKey2</b>: Adding the second round key to the result of the (<b>ShiftRows</b>) step.</li>
     * </ol>
     *
     * @param plainText An Integer Array of four elements, representing the plaintext to be encrypted.
     *
     * @param key An Integer Array of four elements, representing the key with which the plaintext will be encrypted.
     *
     * @return Returns a new integer array of four elements, representing the encrypted plaintext.
     */
    public static String Encrypt(String plainText, String key) {
        //Converting the plain Text and the key to Integer Arrays
        int[] plainTextArray = Utilities.stringToIntArray(plainText);
        int[] keyArray = Utilities.stringToIntArray(key);

        EncryptionDetails encryptionDetails = new EncryptionDetails(keyArray);
        
        //Adding the Round Key Zero
        plainTextArray = addRoundKey(plainTextArray, encryptionDetails.getKey0());

        //Goes through the nibble substitution
        plainTextArray = nibbleSub(plainTextArray);

        //The last two nibbles are shifted
        plainTextArray = shiftRows(plainTextArray);

        //Goes through the mixing of the columns using the Multiplication Table
        plainTextArray = mixColumns(plainTextArray);

        //Adding Round Key One
        plainTextArray = addRoundKey(plainTextArray, encryptionDetails.getKey1());

        //Goes through the nibble substitution again
        plainTextArray = nibbleSub(plainTextArray);

        //The last two nibbles are shifted again
        plainTextArray = shiftRows(plainTextArray);

        //Adding the final Round Key Two
        plainTextArray = addRoundKey(plainTextArray, encryptionDetails.getKey2());

        return Utilities.intArrayToString(plainTextArray);
    }
    
    /**
     * <h2>Encrypt</h2>
     * 
     * <p>This method encapsulates the operations done
     * in the encryption process of the Mini-AES. The process is broken down into a few steps:</p>
     * 
     * <ol>
     *     <li><b>AddRoundKey</b>: Adding the initial key to the plain text.</li>
     * 
     *     <li><b>NibbleSub</b>: Substituting the values in the result of the
     *     (<b>AddRoundKey</b>) step with values from the NibbleSub table found in the <b>Tables</b> class.</li>
     *     
     *     <li><b>ShiftRows</b>: Shifting the last two elements of the result of
     *     the (<b>NibbleSub</b>) step.</li>
     *     
     *     <li><b>MixColumn</b>: Multiplying the result the (<b>ShiftRows</b>) step
     *     by a constant array.</li>
     *     
     *     <li><b>AddRoundKey1</b>: Adding the first round key to the result of the (<b>MixColumn</b>) step.</li>
     *     
     *     <li><b>NibbleSub</b>: Substituting the values in the result of the
     *     (<b>AddRoundKey1</b>) step with values from the NibbleSub table found in the <b>Tables</b> class.</li>
     *     
     *     <li><b>ShiftRows</b>: Shifting the last two elements of the result of
     *     the (<b>NibbleSub</b>) step.</li>
     *
     *     <li><b>AddRoundKey2</b>: Adding the second round key to the result of the (<b>ShiftRows</b>) step.</li>
     * </ol>
     *
     * @param plainText An Integer Array of four elements, representing the plaintext to be encrypted.
     *
     * @param generateRandomKey A boolean value that triggers the generation of a random key.
     *
     * @return Returns a new integer array of four elements, representing the encrypted plaintext.
     */
    public static Pair Encrypt(String plainText, boolean generateRandomKey) throws Exception {
        
        if(!generateRandomKey) {
            throw new Exception("GenerateRandomKey variable set to false. Please provide a 16-bit binary key in it's place and re-start.");
        }
        
        RandomKey randomKey = new RandomKey();
        String key = randomKey.getRandomKeyString();
        //Converting the plain Text and the key to Integer Arrays
        int[] plainTextArray = Utilities.stringToIntArray(plainText);
        int[] keyArray = Utilities.stringToIntArray(key);

        EncryptionDetails encryptionDetails = new EncryptionDetails(keyArray);
        
        //Adding the Round Key Zero
        plainTextArray = addRoundKey(plainTextArray, encryptionDetails.getKey0());

        //Goes through the nibble substitution
        plainTextArray = nibbleSub(plainTextArray);

        //The last two nibbles are shifted
        plainTextArray = shiftRows(plainTextArray);

        //Goes through the mixing of the columns using the Multiplication Table
        plainTextArray = mixColumns(plainTextArray);

        //Adding Round Key One
        plainTextArray = addRoundKey(plainTextArray, encryptionDetails.getKey1());

        //Goes through the nibble substitution again
        plainTextArray = nibbleSub(plainTextArray);

        //The last two nibbles are shifted again
        plainTextArray = shiftRows(plainTextArray);

        //Adding the final Round Key Two
        plainTextArray = addRoundKey(plainTextArray, encryptionDetails.getKey2());
        
        Pair<String, String> result = new Pair<>(key, Utilities.intArrayToString(plainTextArray));
        
        return result;
    }
    
    /**
     * <h2>Decrypt</h2>
     * 
     * <p>This method encapsulates the operations done
     * in the decryption process of the Mini-AES. The process is broken down into a few steps:</p>
     * 
     * <ol>
     *     <li><b>AddRoundKey2</b>: Adding the second round key to the result of the (<b>ShiftRows</b>) step.</li>
     * 
     *     <li><b>InverseNibbleSub</b>: Substituting the values in the result of the
     *     (<b>AddRoundKey2</b>) step with values from the inverted NibbleSub table found in the <b>Tables</b> class.</li>
     *     
     *     <li><b>ShiftRows</b>: Shifting the last two elements of the result of
     *     the (<b>InverseNibbleSub</b>) step.</li>
     * 
     *     <li><b>AddRoundKey1</b>: Adding the first round key to the result of the (<b>ShiftRows</b>) step.</li>
     *     
     *     <li><b>MixColumn</b>: Multiplying the result the (<b>AddRoundKey1</b>) step
     *     by a constant array.</li>
     *     
     *     <li><b>InverseNibbleSub</b>: Substituting the values in the result of the
     *     (<b>AddRoundKey1</b>) step with values from the inverted NibbleSub table found in the <b>Tables</b> class.</li>
     *     
     *     <li><b>ShiftRows</b>: Shifting the last two elements of the result of
     *     the (<b>InverseNibbleSub</b>) step.</li>
     *
     *     <li><b>AddRoundKey</b>: Adding the initial key to the result of the <b>ShiftRows</b> step.</li>
     * </ol>
     *
     * @param cipher An Integer Array of four elements, representing the cipher text to be decrypted.
     *
     * @param key An Integer Array of four elements, representing the key with which the cipher text will be decrypted.
     *
     * @return Returns a new integer array of four elements, representing the decrypted cipher text.
     */
    public static String Decrypt(String cipherText, String key) {
        //Converting the cipher Text and the key to Integer Arrays
        int[] cipherTextArray = Utilities.stringToIntArray(cipherText);
        int[] keyArray = Utilities.stringToIntArray(key);

        EncryptionDetails encryptionDetails = new EncryptionDetails(keyArray);

        //Adding the final Round Key - Key Two
        cipherTextArray = addRoundKey(cipherTextArray, encryptionDetails.getKey2());

        //Goes through the nibble substitution but using the inverse table
        cipherTextArray = inverseNibbleSub(cipherTextArray);

        //The last two nibbles are shifted
        cipherTextArray = shiftRows(cipherTextArray);

        //Adding the Round Key One
        cipherTextArray = addRoundKey(cipherTextArray, encryptionDetails.getKey1());

        //Goes through the mixing of the columns using the Multiplication Table
        cipherTextArray = mixColumns(cipherTextArray);

        //Goes through the nibble substitution but using the inverse table
        cipherTextArray = inverseNibbleSub(cipherTextArray);

        //The last two nibbles are shifted
        cipherTextArray = shiftRows(cipherTextArray);

        //Adding the Round Key Zero
        cipherTextArray = addRoundKey(cipherTextArray, encryptionDetails.getKey0());

        return Utilities.intArrayToString(cipherTextArray);
    }

    
    public static String EncryptText(String plainText, String key) {
        //Converting the plain Text and the key to Integer Arrays
        int[][] plainTextMatrix = Utilities.segmentInputData(plainText);
        int[] keyArray = Utilities.stringToIntArray(key);
        
        String encryptedText = "";
        final int plainTextMatrixLength = plainTextMatrix.length;

        EncryptionDetails encryptionDetails = new EncryptionDetails(keyArray);
        
        
        for(int iter = 0; iter < plainTextMatrixLength; iter++) {
            //Adding the Round Key Zero
            plainTextMatrix[iter] = addRoundKey(plainTextMatrix[iter], encryptionDetails.getKey0());

            //Goes through the nibble substitution
            plainTextMatrix[iter] = subBytes(plainTextMatrix[iter]);

            //The last two nibbles are shifted
            plainTextMatrix[iter] = shiftRows(plainTextMatrix[iter]);

            //Goes through the mixing of the columns using the Multiplication Table
            plainTextMatrix[iter] = mixColumnsAES(plainTextMatrix[iter]);

            //Adding Round Key One
            plainTextMatrix[iter] = addRoundKey(plainTextMatrix[iter], encryptionDetails.getKey1());

            //Goes through the nibble substitution again
            plainTextMatrix[iter] = subBytes(plainTextMatrix[iter]);

            //The last two nibbles are shifted again
            plainTextMatrix[iter] = shiftRows(plainTextMatrix[iter]);

            //Adding the final Round Key Two
            plainTextMatrix[iter] = addRoundKey(plainTextMatrix[iter], encryptionDetails.getKey2());
        }
        
        for(int iter = 0; iter < plainTextMatrixLength; iter++) {
            encryptedText += Utilities.intArrayToHexString(plainTextMatrix[iter]);
        }

        return encryptedText;
    }
    
        
    public static String DecryptText(String plainText, String key) {
        int[][] plainTextMatrix = Utilities.segmentInputData(plainText);
        int[] keyArray = Utilities.stringToIntArray(key);
        
        String encryptedText = "";
        final int plainTextMatrixLength = plainTextMatrix.length;

        EncryptionDetails encryptionDetails = new EncryptionDetails(keyArray);
        
        
        for(int iter = 0; iter < plainTextMatrixLength; iter++) {
            plainTextMatrix[iter] = addRoundKey(plainTextMatrix[iter], encryptionDetails.getKey2());

            plainTextMatrix[iter] = inverseSubBytes(plainTextMatrix[iter]);

            plainTextMatrix[iter] = shiftRows(plainTextMatrix[iter]);

            plainTextMatrix[iter] = addRoundKey(plainTextMatrix[iter], encryptionDetails.getKey1());
            
            plainTextMatrix[iter] = inverseMixColumnsAES(plainTextMatrix[iter]);

            plainTextMatrix[iter] = inverseSubBytes(plainTextMatrix[iter]);

            plainTextMatrix[iter] = shiftRows(plainTextMatrix[iter]);

            plainTextMatrix[iter] = addRoundKey(plainTextMatrix[iter], encryptionDetails.getKey0());
        }
        
        for(int iter = 0; iter < plainTextMatrixLength; iter++) {
            encryptedText += Utilities.intArrayToHexString(plainTextMatrix[iter]);
        }

        return encryptedText;
    }

}