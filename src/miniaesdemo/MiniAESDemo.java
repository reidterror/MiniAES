/**
 * <h1>Class used to test the Mini-AES</h1>
 *
 *  @author Team Caligula
 *  @version 0.1
 *  @since   02.04.2019
 */

package miniaesdemo;

import java.util.Scanner;
import javafx.util.Pair;
import static miniaesdemo.Utilities.printLogo;

// Class for Testing the Mini-AES
public class MiniAESDemo {

        /**
         *
         * @param args the command line arguments
         */
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int cliOption;
            
            do {
                printLogo();
                System.out.print("1. Encrypt a binary string.\n"
                                + "2. Encrypt binary string with random key.\n"
                                + "3. Decrypt a binary string\n"
                                + "4. Encrypt plaintext.\n"
                                + "5. Decrypt plaintext.\n\n"
                                + "0. Exit\n\n"
                                + "Enter your choice: "
                );
                
                cliOption = scanner.nextInt();
                
                switch(cliOption) {
                    case 1: {
                        System.out.println("Enter binary string plaintext:");
                        scanner.nextLine();
                        String plainText = scanner.nextLine();
                        
                        System.out.println("Enter a binary string key:");
                        String encryptionKey = scanner.nextLine();
                        
                        if(plainText.length() == 19 && encryptionKey.length() == 19) {
                            System.out.println("Cipher text: " + MiniAES.Encrypt(plainText, encryptionKey));
                        }
                        else
                        {
                            System.out.println("Invalid plaintext or key.");
                        }
                        
                        break;
                    }
                    
                    case 2: {
                        System.out.println("Enter binary string plaintext:");
                        scanner.nextLine();
                        String plainText = scanner.nextLine();
                        
                        if(plainText.length() == 19) {
                            Pair<String, String> result = MiniAES.Encrypt(plainText, true);
                            System.out.println("Cipher text: " + result.getValue() + "\nRandom key: " + result.getKey());
                        }
                        else
                        {
                            System.out.println("Invalid plaintext.");
                        }
                        
                        break;
                    }
                    
                    case 3: {
                        System.out.println("Enter binary string cipher text:");
                        scanner.nextLine();
                        String cipherText = scanner.nextLine();
                        
                        System.out.println("Enter a binary string key:");
                        String encryptionKey = scanner.nextLine();
                        
                        if(cipherText.length() == 19 && encryptionKey.length() == 19) {
                            System.out.println("Decrypted cipher text: " + MiniAES.Decrypt(cipherText, encryptionKey));
                        }
                        else
                        {
                            System.out.println("Invalid cipher text or key.");
                        }
                        
                        break;
                    }
                    

                    case 4: {
                        
                        break;
                    }
                    
                    case 5: {
                        
                        break;
                    }
                }
            }
            while(cliOption!=0);
        }
        
        public static void normalTest() {
            System.out.println("PlainText: " + "1010 0101 1100 0011");
            System.out.println("Encrypted: " + MiniAES.Encrypt("1010 0101 1100 0011", "1111 0000 1111 0000"));
            System.out.println("Decrypted: " + MiniAES.Decrypt(MiniAES.Encrypt("1010 0101 1100 0011", "1111 0000 1111 0000"), "1111 0000 1111 0000"));
        }
        
        public static void randomKeyTest() {
            Pair<String, String> result = MiniAES.Encrypt("1010 0101 1100 0011", true);
            
            System.out.println("Encrypted: " + result.getValue() + "\nRandom Key: " + result.getKey());
            System.out.println("Decrypted: " + MiniAES.Decrypt(result.getValue(), result.getKey()));
        }
    }
