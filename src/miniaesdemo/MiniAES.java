/**
* The Mini-AES class implements a minified version of the AES encryption algorithm.
*
* @author  Team Caligula
* @version 0.1
* @since   29.03.2019
*/
package miniaesdemo;

class MiniAES {
    private static int[] AddRoundKey(int[] plainText, int[] key) {
        int[] result = new int[4];

        for(int iter = 0; iter < 4; iter++) {
            result[iter] = plainText[iter] ^ key[iter];
        }

        return result;
    }

    private static int[] NibbleSub(int[] plainText) {
        int[] result = new int[4];

        for(int iter = 0; iter < 4; iter++) {
            result[iter] = Tables.getNibbleSubValue(plainText[iter]);
        }

        return result;
    }

    private static int[] InverseNibbleSub(int[] plainText) {
        int[] result = new int[4];

        for(int iter = 0; iter < 4; iter++) {
            result[iter] = Tables.getInverseNibbleSubValue(plainText[iter]);
        }

        return result;
    }

    private static int[] ShiftRows(int[] plainText) {
        int value1 = plainText[2];
        int value2 = plainText[3];

        plainText[2] = value2;
        plainText[3] = value1;

        return plainText;
    }
    
    private static int[] MixColumns(int[] plainText) {
        final int[] constMatrix = new int[]{3, 2, 2, 3};
        
        int[] result = {
                        (Tables.getMixColumnValue(constMatrix[0], plainText[0]) ^ (Tables.getMixColumnValue(constMatrix[2], plainText[2]))),
                        (Tables.getMixColumnValue(constMatrix[3], plainText[1]) ^ (Tables.getMixColumnValue(constMatrix[1], plainText[3]))),
                        (Tables.getMixColumnValue(constMatrix[2], plainText[0]) ^ (Tables.getMixColumnValue(constMatrix[0], plainText[2]))),
                        (Tables.getMixColumnValue(constMatrix[1], plainText[1]) ^ (Tables.getMixColumnValue(constMatrix[3], plainText[3])))
        };
        
        return result; 
    }
    
    public static String Encrypt(String plainText, String key) {
        int[] plainTextArray = Utilities.stringToIntArray(plainText);
        int[] keyArray = Utilities.stringToIntArray(key);
        
        EncryptionDetails encryptionDetails = new EncryptionDetails(keyArray);
        
        plainTextArray = AddRoundKey(plainTextArray, encryptionDetails.getKey0());
        plainTextArray = NibbleSub(plainTextArray);
        plainTextArray = ShiftRows(plainTextArray);
        plainTextArray = MixColumns(plainTextArray);
        plainTextArray = AddRoundKey(plainTextArray, encryptionDetails.getKey1());
        plainTextArray = NibbleSub(plainTextArray);
        plainTextArray = ShiftRows(plainTextArray);
        plainTextArray = AddRoundKey(plainTextArray, encryptionDetails.getKey2());
                
        return Utilities.intArrayToString(plainTextArray);
    }
    
    public static String Decrypt(String cipherText, String key) {
        int[] cipherTextArray = Utilities.stringToIntArray(cipherText);
        int[] keyArray = Utilities.stringToIntArray(key);
        
        EncryptionDetails encryptionDetails = new EncryptionDetails(keyArray);
        
        cipherTextArray = AddRoundKey(cipherTextArray, encryptionDetails.getKey2());
        cipherTextArray = InverseNibbleSub(cipherTextArray);
        cipherTextArray = ShiftRows(cipherTextArray);
        cipherTextArray = AddRoundKey(cipherTextArray, encryptionDetails.getKey1());
        cipherTextArray = MixColumns(cipherTextArray);
        cipherTextArray = InverseNibbleSub(cipherTextArray);
        cipherTextArray = ShiftRows(cipherTextArray);
        cipherTextArray = AddRoundKey(cipherTextArray, encryptionDetails.getKey0());
                
        return Utilities.intArrayToString(cipherTextArray);
    }
}