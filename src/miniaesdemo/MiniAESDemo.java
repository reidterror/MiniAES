package miniaesdemo;

import javafx.util.Pair;
import static miniaesdemo.Utilities.printLogo;

/**
 * <h1>Class used to test the Mini-AES</h1>
 *
 *  @author Team Caligula
 *  @version 0.1
 *  @since   02.04.2019
 */
// Class for Testing the Mini-AES
public class MiniAESDemo {

        /**
         *
         * @param args the command line arguments
         */
        public static void main(String[] args) {
            printLogo();

            Pair<String, String> result = MiniAES.Encrypt("1010 0101 1100 0011", true);
            
            System.out.println("Encrypted: " + result.getValue() + "\nRandom Key: " + result.getKey());
            System.out.println("Decrypted: " + MiniAES.Decrypt(result.getValue(), result.getKey()));
        }
    }
