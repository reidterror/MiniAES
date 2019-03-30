package miniaesdemo;

public class MiniAESDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //System.out.println("Decrypted: " + MiniAES.Decrypt(MiniAES.Encrypt("1010 0101 1100 0011", "1010 1111 0000 0101"), "1010 1111 0000 0101"));
        //System.out.println("Encrypted: " + MiniAES.Encrypt("1010 0101 1100 0011", "1010 1111 0000 0101"));
        System.out.println("" + MiniAES.Decrypt("1111 1111 1111 1111", "0000 0000 0000 0000"));
    }
    
    /**
     * TODO:
     * -Think of a decent way to use MiniAES in the real world. (Thanks Rodger.)
     * -Everyone should write their own comments.
     * -Making UML diagrams. (Talk to Rodger about how to write the design doc.)
    */
}
