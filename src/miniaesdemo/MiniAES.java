/**
* The Mini-AES class implements a minified version of the AES encryption algorithm.
*
* @author  Team Caligula
* @version 0.1
* @since   29.03.2019
*/

package miniaesdemo;

import javafx.util.Pair;

/**
 * <h1>The Mini-AES class implements a minified version of the AES encryption algorithm.</h1>
 *
 * @author  Team Caligula
 * @version 0.1
 * @since   02.04.2019
 */

class MiniAES {

    /**
     * <h1>AddRound Key Section</h1>
     * <h2>Method that adds the Round Key to the plain text</h2>
     * <p>The methods uses a loop the Loop that goes through every part of the plainText Array,
     *    using XOR the RoundKey is added to every part of the Array</p>
     *
     * @param plainText An Integer Array of four elements that is converted using the <b>ConversionUtilities</b> class
     *
     * @param key An Integer Array of four elements that stores the various keys.
     *
     * @return Returns a new integer array.
     */
    private static int[] AddRoundKey(int[] plainText, int[] key) {
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
     * <h1>NibbleSub Section</h1>
     * <h2>Method that substitutes every nibble</h2>
     * <p>This methods uses a loop that goes through the NibbleSub
     *    Table and substitutes every part of the plainText Array </p>
     *
     * @param plainText An Integer Array of four elements.
     *
     * @return Returns a new integer array of four elements.
     */
    private static int[] NibbleSub(int[] plainText) {
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
     * <h1>Inverse NibbleSub Section</h1>
     * <h2>Method that substitutes every nibble in inverse order</h2>
     * <p>This methods uses a loop that goes through the Inverse NibbleSub
     *    Table and substitutes every part of the plainText Array </p>
     *
     * @param plainText An Integer Array of four elements.
     *
     * @return Returns a new integer array of four elements.
     */
    private static int[] InverseNibbleSub(int[] plainText) {
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
    
    /**
     * <h1>ShiftRows Section</h1>
     * <h2>Methods that shifts the last two elements in the array</h2>
     * <p>The methods stores the last two elements form the pain
     *    text array into two variables and slabs their passion,
     *    reassigning them back to the array</p>
     *
     * @param plainText An Integer Array of four elements.
     *
     * @return Returns the same array but with sifted elements.
     */
    private static int[] ShiftRows(int[] plainText) {
        int value1 = plainText[2];
        int value2 = plainText[3];

        // Shifts the last two Nibbles
        plainText[2] = value2;
        plainText[3] = value1;

        return plainText;
    }
    
    /**
     * <H1>MixColumns Section</H1>
     * <h2>Method that changes the elements of the plainText</h2>
     * <p>Using the Hexadecimal Multiplication table and the constant
     *    matrix in the MixColumns Section every nibble of the plain
     *    Text Array is assign with new value by also using XOR. </p>
     *
     * @param plainText An Integer Array of four elements.
     *
     * @return Returns a new integer array of four elements.
     */
    private static int[] MixColumns(int[] plainText) {
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
    
    /**
     * <h1>The Encryption Process</h1>
     * <h2>This method encapsulates the operations done
     *     in the encryption process of the Mini-AES</h2>
     * <p>This methods uses helper methods to produce an encrypted copy of a binary string.
     *    The followitng is a list of the operations: </p>
     * <ol>
     *     <li>Adding round key Zero to the plain text</li>
     *     <li>Substitution of every nibble or element occurs</li>
     *     <li>The last two nibbles are shifted</li>
     *     <li>Going through the mixing of the columns using
     *     the Multiplication Table and the constant matrix</li>
     *     <li>Adding round key One</li>
     *     <li>Going through the nibble substitution again</li>
     *     <li>The last two nibbles are shifted again</li>
     *     <li>Adding the final Round Key Two</li>
     * </ol>
     *
     * @param plainText An Integer Array of four elements.
     *
     * @param key Also an Integer Array of four elements.
     *
     * @return Returns a new integer array of four elements.
     */
    public static String Encrypt(String plainText, String key) {
        //Converting the plain Text and the key to Integer Arrays
        int[] plainTextArray = Utilities.stringToIntArray(plainText);
        int[] keyArray = Utilities.stringToIntArray(key);

        EncryptionDetails encryptionDetails = new EncryptionDetails(keyArray);
        
        //Adding the Round Key Zero
        plainTextArray = AddRoundKey(plainTextArray, encryptionDetails.getKey0());

        //Goes through the nibble substitution
        plainTextArray = NibbleSub(plainTextArray);

        //The last two nibbles are shifted
        plainTextArray = ShiftRows(plainTextArray);

        //Goes through the mixing of the columns using the Multiplication Table
        plainTextArray = MixColumns(plainTextArray);

        //Adding Round Key One
        plainTextArray = AddRoundKey(plainTextArray, encryptionDetails.getKey1());

        //Goes through the nibble substitution again
        plainTextArray = NibbleSub(plainTextArray);

        //The last two nibbles are shifted again
        plainTextArray = ShiftRows(plainTextArray);

        //Adding the final Round Key Two
        plainTextArray = AddRoundKey(plainTextArray, encryptionDetails.getKey2());

        return Utilities.intArrayToString(plainTextArray);
    }
    
    public static Pair Encrypt(String plainText, boolean generateRandomKey) {
        RandomKey randomKey = new RandomKey();
        String key = randomKey.getRandomKeyString();
        //Converting the plain Text and the key to Integer Arrays
        int[] plainTextArray = Utilities.stringToIntArray(plainText);
        int[] keyArray = Utilities.stringToIntArray(key);

        EncryptionDetails encryptionDetails = new EncryptionDetails(keyArray);
        
        //Adding the Round Key Zero
        plainTextArray = AddRoundKey(plainTextArray, encryptionDetails.getKey0());

        //Goes through the nibble substitution
        plainTextArray = NibbleSub(plainTextArray);

        //The last two nibbles are shifted
        plainTextArray = ShiftRows(plainTextArray);

        //Goes through the mixing of the columns using the Multiplication Table
        plainTextArray = MixColumns(plainTextArray);

        //Adding Round Key One
        plainTextArray = AddRoundKey(plainTextArray, encryptionDetails.getKey1());

        //Goes through the nibble substitution again
        plainTextArray = NibbleSub(plainTextArray);

        //The last two nibbles are shifted again
        plainTextArray = ShiftRows(plainTextArray);

        //Adding the final Round Key Two
        plainTextArray = AddRoundKey(plainTextArray, encryptionDetails.getKey2());
        
        Pair<String, String> result = new Pair<>(key, Utilities.intArrayToString(plainTextArray));
        
        return result;
    }
    
    /**
     * <h1>The Decryption Process</h1>
     * <h2>This method encapsulates the operations done
     *     in the decryption process of the Mini-AES</h2>
     * <p>This methods uses helper methods to produce an decrypted copy
     *    of an encrypted binary string. The following is a list of
     *    the operations: </p>
     * <ol>
     *     <li>Adding round key Two to the plain text</li>
     *     <li>Substitution of every nibble or element occurs in inverse order</li>
     *     <li>The last two nibbles are shifted</li>
     *     <li>Going through the mixing of the columns using
     *     the Multiplication Table and the constant matrix</li>
     *     <li>Adding round key One</li>
     *     <li>Going through the inverse nibble substitution again</li>
     *     <li>The last two nibbles are shifted again</li>
     *     <li>Adding the Round Key Zero</li>
     * </ol>
     *
     * @param cipherText An Integer Array of four elements.
     *
     * @param key Also an Integer Array of four elements.
     *
     * @return Returns a new integer array of four elements.
     *
     */
    public static String Decrypt(String cipherText, String key) {
        //Converting the cipher Text and the key to Integer Arrays
        int[] cipherTextArray = Utilities.stringToIntArray(cipherText);
        int[] keyArray = Utilities.stringToIntArray(key);

        EncryptionDetails encryptionDetails = new EncryptionDetails(keyArray);

        //Adding the final Round Key - Key Two
        cipherTextArray = AddRoundKey(cipherTextArray, encryptionDetails.getKey2());

        //Goes through the nibble substitution but using the inverse table
        cipherTextArray = InverseNibbleSub(cipherTextArray);

        //The last two nibbles are shifted
        cipherTextArray = ShiftRows(cipherTextArray);

        //Adding the Round Key One
        cipherTextArray = AddRoundKey(cipherTextArray, encryptionDetails.getKey1());

        //Goes through the mixing of the columns using the Multiplication Table
        cipherTextArray = MixColumns(cipherTextArray);

        //Goes through the nibble substitution but using the inverse table
        cipherTextArray = InverseNibbleSub(cipherTextArray);

        //The last two nibbles are shifted
        cipherTextArray = ShiftRows(cipherTextArray);

        //Adding the Round Key Zero
        cipherTextArray = AddRoundKey(cipherTextArray, encryptionDetails.getKey0());

        return Utilities.intArrayToString(cipherTextArray);
    }
}