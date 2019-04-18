package miniaesdemo;

/**
 *  <h1>RandomKey class generates a random 16-bit key and stores it.</h1>
 *
 *  @author Team Caligula
 *  @version 0.1
 *  @since   02.04.2019
 */
public class RandomKey {
    private int[] randomKey;

    public RandomKey() {
        randomKey = new int[4];
        generateRandomKey();
    }

    private void generateRandomKey(){
        for(int i = 0; i < 4; i++) {
            randomKey[i] = (int)(Math.random() * 15);
        }
    }

    public int[] getRandomKey() {
        return randomKey;
    }

    public String getRandomKeyString() {
        return Utilities.intArrayToString(randomKey);
    }
}
