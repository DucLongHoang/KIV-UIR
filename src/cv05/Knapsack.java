package cv05;

public class Knapsack {

    final int CAPACITY, ITEM_COUNT;
    final Item[] ITEMS;
    Population population;

    public Knapsack(int capacity, Item[] items, int initPoolSize) {
        this.CAPACITY = capacity;
        this.ITEMS = items;
        this.ITEM_COUNT = items.length;
        this.population = new Population(ITEM_COUNT, initPoolSize);
    }

    public void getBestPermutation() {
        Chromosome best = population.getBestChromosome(this);
        System.out.println();
        System.out.println("Best chromosome has " + best);
    }

    public void setPopulationValues() {
        for(Chromosome c: population.generation) {
            setChromosomeValues(c);
        }
    }

    private void setChromosomeValues(Chromosome c) {
        for(int i = 0; i < ITEM_COUNT; i++) {
            if(c.permutation[i]) {
                c.price += ITEMS[i].PRICE;
                c.weight += ITEMS[i].WEIGHT;
            }
        }
    }
}
