package cv05;

/**
 * Item class - item in a Knapsack, has a weight and price
 * @author Long
 * @version 1.0
 */
public class Item {
    /** Weight and price of Item */
    final int WEIGHT, PRICE;

    /**
     * Constructor for Item
     * @param w weight of Item
     * @param p Price of Item
     */
    public Item(int w, int p) {
        this.WEIGHT = w;
        this.PRICE = p;
    }
}