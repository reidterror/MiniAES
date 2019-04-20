/**
 *  <h1>RandomKey class generates a random 16-bit key and stores it.</h1>
 *
 *  @author Team Caligula
 *  @version 1.0
 *  @since   02.04.2019
 */

package miniaesdemo;

public class RandomKey {
    private int[] randomKey;

    /**
     * <h2>Random Key Constructor Method</h2>
     * 
     * <p>This method sets the initial value of the local variables.</p>
     */
    public RandomKey() {
        randomKey = new int[4];
        generateRandomKey();
    }
    
    /**
     * <h2>Generate Random Encryption Key Method</h2>
     * 
     * <p>This method generates a random 16-bit binary key.</p>
     */
    private void generateRandomKey(){
        for(int i = 0; i < 4; i++) {
            randomKey[i] = (int)(Math.random() * 15);
        }
    }

    /**
     * <h2>Getter method for the local variable <b>randomKey</b></h2>
     * 
     * <p>This method returns the private local variable <b>randomKey</b>.</p>
     * 
     * @return Returns a String Array.
     */
    public int[] getRandomKey() {
        return randomKey;
    }

    /**
     * <h2>Getter method for the local variable <b>randomKey</b></h2>
     * 
     * <p>This method returns the private local variable <b>randomKey</b> converted to a String.</p>
     * 
     * @return Returns a 16-bit binary String
     */
    public String getRandomKeyString() {
        return Utilities.intArrayToString(randomKey);
    }
}
