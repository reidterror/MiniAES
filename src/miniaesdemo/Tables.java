package miniaesdemo;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1>Class that stores the different tables in the Mini-Aes</h1>
 *
 *  @author Team Caligula
 *  @version 0.1
 *  @since   02.04.2019
 */
// Class for the storage and use of the tables in the Mini-AES
class Tables {
   /* /**
     * <h1>The following tables are for:</h1>
     * <ol>
     *     <il>NibbleSub Section</il>
     *     <il>MixColumns Section</il>
     * </ol>
     */
    // Creating a final HashMap Value for the NibbleSub Table

    /**
     *
     */
    private static final HashMap<Integer, Integer> nibbleSubTable;
    // Creating a final HashMap Value for the Mixcolumns Table

    /**
     *
     */
    private static final int[][] multiplicationTable;

    static {

        // NibbelSub Table in HashMap
        nibbleSubTable = new HashMap<>();
        nibbleSubTable.put(0, 14);
        nibbleSubTable.put(1, 4);
        nibbleSubTable.put(2, 13);
        nibbleSubTable.put(3, 1);
        nibbleSubTable.put(4, 2);
        nibbleSubTable.put(5, 15);
        nibbleSubTable.put(6, 11);
        nibbleSubTable.put(7, 8);
        nibbleSubTable.put(8, 3);
        nibbleSubTable.put(9, 10);
        nibbleSubTable.put(10, 6);
        nibbleSubTable.put(11, 12);
        nibbleSubTable.put(12, 5);
        nibbleSubTable.put(13, 9);
        nibbleSubTable.put(14, 0);
        nibbleSubTable.put(15, 7);

        // Mixcolumns Hexadecimal Multiplication Table in Integers
        multiplicationTable = new int[][]{  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15},
                {0, 2, 4, 6, 8, 10, 12, 14, 3, 1, 7, 5, 11, 9, 15, 13},
                {0, 3, 6, 5, 12, 15, 10, 9, 11, 8, 13, 14, 7, 4, 1, 2},
                {0, 4, 8, 12, 3, 7, 11, 15, 6, 2, 14, 10, 5, 1, 13, 9},
                {0, 5, 10, 15, 7, 2, 13, 8, 14, 11, 4, 1, 9, 12, 3, 6},
                {0, 6, 12, 10, 11, 13, 7, 1, 5, 3, 9, 15, 14, 8, 2, 4},
                {0, 7, 14, 9, 15, 8, 1, 6, 13, 10, 3, 4, 2, 5, 12, 11},
                {0, 8, 3, 11, 6, 14, 5, 13, 12, 4, 15, 7, 10, 2, 9, 1},
                {0, 9, 1, 8, 2, 11, 3, 10, 4, 13, 5, 12, 6, 15, 7, 14},
                {0, 10, 7, 13, 14, 4, 9, 3, 15, 5, 8, 2, 1, 11, 6, 12},
                {0, 11, 5, 14, 10, 1, 15, 4, 7, 12, 2, 9, 13, 6, 8, 3},
                {0, 12, 11, 7, 5, 9, 14, 2, 10, 6, 1, 13, 15, 3, 4, 8},
                {0, 13, 9, 4, 1, 12, 8, 5, 2, 15, 11, 6, 3, 14, 10, 7},
                {0, 14, 15, 1, 13, 3, 2, 12, 9, 7, 6, 8, 4, 10, 11, 5},
                {0, 15, 13, 2, 9, 6, 4, 8, 1, 14, 12, 3, 8, 7, 5, 10}
        };
    }

    //==================================================================================================
    //Getter Methods for the NibbleSubTable and MixColumnsTable

    /**
     * <h2>Getter Method for the Values in the NibbleSub Table</h2>
     * @param value This is an integer that specifies the exact value.
     * @return Returns the exact value.
     */
    public static int getNibbleSubValue(int value) {

        return nibbleSubTable.get(value);
    }

    /**
     * <h2>Getter Method for the Values in the MixColumns Table</h2>
     * @param row This is an integers that specifies the location of the row in the 2D Array
     * @param column This is an integers that specifies the location of the column 2D Array
     * @return Returns the value depending of the exact position of the row and the column
     */
    public static int getMixColumnValue(int row, int column) {

        return multiplicationTable[row][column];
    }

    //==================================================================================================
    /**
     * <h2>Getter method for the values in the inverse NibbleSub table </h2>
     * <p>This methods acquire its vlaues by using an enchanted for
     *    loop to get the keys using the values in the HashMap</p>
     * @param value
     * @return
     */
    public static int getInverseNibbleSubValue(int value) {
        // Loop to iterate through the HashMap
        // to create an Inverse NibbleSub for the Decryption
        for (Map.Entry<Integer, Integer> entry : nibbleSubTable.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        //End of Loop

        return 0;
    }
}