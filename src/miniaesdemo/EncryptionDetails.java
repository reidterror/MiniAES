package miniaesdemo;

/**
 * <h1>This class generates and stores the round keys that are needed in the Mini-AES</h1>
 *
 *  @author Team Caligula
 *  @version 0.1
 *  @since   02.04.2019
 */

// Class for storage and generation of the Keys
class EncryptionDetails {
    // Creating Integer Arrays
    // to store the various keys

    /**
     *
     */
    private int[] key0;

    /**
     *
     */
    private int[] key1;

    /**
     *
     */
    private int[] key2;

    
    // Constructor Methods

    /**
     * <h2>Constructor Methods for the tree round keys</h2>
     */
    public EncryptionDetails() {
        key0        = new int[4];
        key1        = new int[4];
        key2        = new int[4];
    }

    /**
     *
     * @param initialKey
     */
    public EncryptionDetails(int[] initialKey) {
        key0        = initialKey;
        key1        = generateKey1();
        key2        = generateKey2();
    }
    
    /**
     * <h2>Generation of the first round key</h2>
     * <p>This method creates the new key by using nibble
     *    substitution and the logical exclusive OR.</p>
     * @return Returns an integer array with four elements.
     */
    private int[] generateKey1() {
        int[] key = new int[4];
        //Generating Key1 using the NibbleSub section
        // and the logical exclusive OR
        key[0] = key0[0] ^ Tables.getNibbleSubValue(key0[3]) ^ 1;
        key[1] = key0[1] ^ key[0];
        key[2] = key0[2] ^ key[1];
        key[3] = key0[3] ^ key[2];

        //System.out.println(key);
        return key;
    }

    /**
     * <h2>Generation of the second round key</h2>
     * <p>This method creates the new key by using nibble
     *    substitution and the logical exclusive OR.</p>
     * @return Returns an integer array with four elements.
     */
    private int[] generateKey2() {
        int[] key = new int[4];
        //Generating Key1 using the NibbleSub section
        // and the logical exclusive OR
        key[0] = key1[0] ^ Tables.getNibbleSubValue(key1[3]) ^ 2;
        key[1] = key1[1] ^ key[0];
        key[2] = key1[2] ^ key[1];
        key[3] = key1[3] ^ key[2];

        //System.out.println(key);
        return key;
    }
    
    // Setter Method

    /**
     * <h2>Setter Method for key Zero</h2>
     * @param key An integer array of four elements
     */
    public void setKey0(String key) {

        key0 = Utilities.stringToIntArray(key);
    }

    
    // Getter Methods for Key0,Key1,Key2

    /**
     * <h2>Getter Method for key Zero </h2>
     * @return Returns the value of key Zero
     */
    public int[] getKey0()
    {
        return key0;
    }

    /**
     * <h2>Getter Method for key One </h2>
     * @return Returns the value of key One
     */
    public int[] getKey1()
    {
        return key1;
    }

    /**
     * <h2>Getter Method for key Two </h2>
     * @return Returns the value of key Two
     */
    public int[] getKey2()
    {
        return key2;
    }

    
    // toString Methods using the intArraytoString Method
    // in class Utilities/ ConversionUtilities

    /**
     * <h2>toStirng Method</h2>
     * <p>This method uses the intArraytoString Method in class ConversionUtilities
     *    to convert the array into a string</p>
     * @param arrayToConvert An integer array with four elements.
     * @return Returns a string of binary text.
     */
    public String toString(int[] arrayToConvert) {
        return Utilities.intArrayToString(arrayToConvert);
    }
}
