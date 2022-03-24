package cv05;

/**
 * Knapsack class - Object to fill with a permutation of items
 * so that it has the highest price while not exceeding the capacity
 * @author Long
 * @version 1.0
 */
public class Knapsack {
    /** Capacity and Item count of Knapsack */
    final int CAPACITY, ITEM_COUNT;
    /** Array of possible Items */
    final Item[] ITEMS;
    /** Reference to the current Population */
    Population population;

    /**
     * Knapsack constructor
     * @param capacity of Knapsack
     * @param items array of Items from Parser
     * @param initPoolSize initial pool size of the first Population
     */
    public Knapsack(int capacity, Item[] items, int initPoolSize) {
        this.CAPACITY = capacity;
        this.ITEMS = items;
        this.ITEM_COUNT = items.length;
        this.population = new Population(ITEM_COUNT, initPoolSize);
    }

    /**
     * Method prints out the best Chromosome
     */
    public void getBestPermutation() {
        Chromosome best = population.getBestChromosome(this);
        System.out.println();
        System.out.println("Best chromosome has " + best);
    }

    /**
     * Method iterates through the Population and sets values to the entire generation of Chromosomes
     */
    public void setPopulationValues() {
        for(Chromosome c: population.generation) {
            setChromosomeValues(c);
        }
    }

    /**
     * Method sets price and weight of Chromosome depending on its permutation
     * @param c Chromosome to set the values of
     */
    private void setChromosomeValues(Chromosome c) {
        for(int i = 0; i < ITEM_COUNT; i++) {
            if(c.permutation[i]) {
                c.price += ITEMS[i].PRICE;
                c.weight += ITEMS[i].WEIGHT;
            }
        }
    }
}