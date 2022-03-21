package cv05;

public class Item {
    final int WEIGHT, PRICE;
    final double RATIO;

    public Item(int w, int p) {
        this.WEIGHT = w;
        this.PRICE = p;
        this.RATIO = PRICE / (double) WEIGHT;
    }
}