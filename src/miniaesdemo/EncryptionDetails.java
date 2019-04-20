/**
 * <h1>EncryptionDetails generates and stores encryption keys.</h1>
 *
 *  @author Team Caligula
 *  @version 1.0
 *  @since   02.04.2019
 */

package miniaesdemo;

// Class for storage and generation of the Keys
class EncryptionDetails {
    // Creating Integer Arrays
    // to store the various keys

    private int[] key0;
    private int[] key1;
    private int[] key2;
   
    // Constructor Methods
    
    /**
     * <h2>Constructor Method with no parameters</h2>
     * 
     * <p>Constructor method that initializes the local variables with empty objects.</p>
     */
    public EncryptionDetails() {
        key0        = new int[4];
        key1        = new int[4];
        key2        = new int[4];
    }

    /**
     * <h2>Constructor Method with an initial key parameter</h2>
     * 
     * <p>Constructor method that takes in an initial key as a parameter and
     * automatically generates the rest of the keys.</p>
     * 
     * @param initialKey A 16-bit binary String Array.
     */
    public EncryptionDetails(int[] initialKey) {
        key0        = initialKey;
        key1        = generateKey1();
        key2        = generateKey2();
    }
    
    /**
     * <h2>Generate all of the three round keys.</h2>
     * 
     * <p>This method takes in an initial key and generates the rest of the keys.</p>
     */
    public void generateKeys(int[] key) {
        key0        = key;
        key1        = generateKey1();
        key2        = generateKey2();
    }
    
    /**
     * <h2>Generate the first round key.</h2>
     * 
     * <p>This method generates the first round key.</p>
     * 
     * @return Returns an Integer Array.
     */
    private int[] generateKey1() {
        int[] key = new int[4];
        //Generating Key1 using the NibbleSub section
        // and the logical exclusive OR.
        key[0] = key0[0] ^ Tables.getNibbleSubValue(key0[3]) ^ 1;
        key[1] = key0[1] ^ key[0];
        key[2] = key0[2] ^ key[1];
        key[3] = key0[3] ^ key[2];

        //System.out.println(key);
        return key;
    }

    /**
     * <h2>Generate the second round key.</h2>
     * 
     * <p>This method generates the second round key.</p>
     * 
     * @return Returns an Integer Array.
     */
    private int[] generateKey2() {
        int[] key = new int[4];
        //Generating Key2 using the NibbleSub section
        // and the logical exclusive OR.
        key[0] = key1[0] ^ Tables.getNibbleSubValue(key1[3]) ^ 2;
        key[1] = key1[1] ^ key[0];
        key[2] = key1[2] ^ key[1];
        key[3] = key1[3] ^ key[2];

        //System.out.println(key);
        return key;
    }
    
    // Setter Method

    /**
     * <h2>Setter method for the initial key</h2>
     * 
     * @param key An Integer Array.
     */
    public void setKey0(String key) {
        key0 = Utilities.stringToIntArray(key);
    }

    
    // Getter Methods for Key0,Key1,Key2

    /**
     * <h2>Getter method for the initial key</h2>
     * 
     * @return Returns the initial key.
     */
    public int[] getKey0()
    {
        return key0;
    }

    /**
     * <h2>Getter method for the first round key.</h2>
     *
     * @return Returns the first round key.
     */
    public int[] getKey1()
    {
        return key1;
    }

    /**
     * <h2>Getter method for the second round key</h2>
     * 
     * @return Returns the second round key.
     */
    public int[] getKey2()
    {
        return key2;
    }

    
    // toString Methods using the intArraytoString Method in class Utilities.

    /**
     * <h2>toStirng Method</h2>
     * 
     * <p>This method uses the intArraytoString Method in class ConversionUtilities
     *    to convert the array into a string</p>
     * 
     * @param arrayToConvert An Integer Array.
     * 
     * @return Returns a 16-bit binary String.
     */
    public String toString(int[] arrayToConvert) {
        return Utilities.intArrayToString(arrayToConvert);
    }
}
