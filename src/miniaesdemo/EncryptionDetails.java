package miniaesdemo;

class EncryptionDetails {
    private int[] key0;
    private int[] key1;
    private int[] key2;

    public EncryptionDetails() {
        key0        = new int[4];
        key1        = new int[4];
        key2        = new int[4];
    }

    public EncryptionDetails(int[] initialKey) {
        key0        = initialKey;
        key1        = generateKey1();
        key2        = generateKey2();
    }
    
    
    private int[] generateKey1() {
        int[] key = new int[4];

        key[0] = key0[0] ^ Tables.getNibbleSubValue(key0[3]) ^ 1;
        key[1] = key0[1] ^ key[0];
        key[2] = key0[2] ^ key[1];
        key[3] = key0[3] ^ key[2];

        System.out.println();
        return key;
    }

    private int[] generateKey2() {
        int[] key = new int[4];

        key[0] = key1[0] ^ Tables.getNibbleSubValue(key1[3]) ^ 2;
        key[1] = key1[1] ^ key[0];
        key[2] = key1[2] ^ key[1];
        key[3] = key1[3] ^ key[2];

        return key;
    }

    public void setKey0(String key) {
        key0 = Utilities.stringToIntArray(key);
    }

    public int[] getKey0()
    {
        return key0;
    }

    public int[] getKey1()
    {
        return key1;
    }

    public int[] getKey2()
    {
        return key2;
    }

    public String toString(int[] arrayToConvert) {
        return Utilities.intArrayToString(arrayToConvert);
    }
}