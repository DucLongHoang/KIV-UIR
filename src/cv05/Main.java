package cv05;

import java.io.IOException;

/**
 * Main class - running program from here
 */
public class Main {
    /** Directory of dataset */
    static final String DIR = "./data/";
    /** Name of file with data */
    static final String PATH_TO_FILE = "cv6_vstup.txt";
    /** Capacity of Knapsack */
    static final int CAPACITY = 40;
    /** Pool size of the first generation of Chromosomes */
    static final int INIT_POOL_SIZE = 10;

    /**
     * Main method to run program
     * @param args arguments from command line
     * @throws IOException exception if file not found
     */
    public static void main(String[] args) throws IOException {
        Parser parser = new Parser(DIR + PATH_TO_FILE);
        Item[] items = new Item[parser.items.size()];
        parser.items.toArray(items);
        Knapsack knapsack = new Knapsack(CAPACITY, items, INIT_POOL_SIZE);
        knapsack.getBestPermutation();
    }
}