package cv05;

import java.io.IOException;

public class Main {
    static final String DIR = "./data/";
    static final String PATH_TO_FILE = "cv6_vstup.txt";
    static final int CAPACITY = 40;
    static final int INIT_POOL_SIZE = 10;

    public static void main(String[] args) throws IOException {
        Parser parser = new Parser(DIR + PATH_TO_FILE);
        Item[] items = new Item[parser.items.size()];
        parser.items.toArray(items);
        Knapsack knapsack = new Knapsack(CAPACITY, items, INIT_POOL_SIZE);
        knapsack.getBestPermutation();
    }
}