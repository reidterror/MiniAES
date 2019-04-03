package miniaesdemo;

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
            System.out.println("" +
                    "   _______                         _____        _  _                _        \n" +
                    "  |__   __|                       / ____|      | |(_)              | |       \n" +
                    "     | |  ___   __ _  _ __ ___   | |      __ _ | | _   __ _  _   _ | |  __ _ \n" +
                    "     | | / _ \\ / _` || '_ ` _ \\  | |     / _` || || | / _` || | | || | / _` |\n" +
                    "     | ||  __/| (_| || | | | | | | |____| (_| || || || (_| || |_| || || (_| |\n" +
                    "     |_| \\___| \\__,_||_| |_| |_|  \\_____|\\__,_||_||_| \\__, | \\__,_||_| \\__,_|\n" +
                    "                                                       __/ |                 \n" +
                    "                                                      |___/                  ");

            System.out.println("Encrypted: " +
                                MiniAES.Encrypt("1010 0101 1100 0011", "1010 1111 0000 0101"));
            System.out.println("Decrypted: " + MiniAES.Decrypt(MiniAES.Encrypt(
                        "1010 0101 1100 0011", "1010 1111 0000 0101"), "1010 1111 0000 0101"));
        }
    }
