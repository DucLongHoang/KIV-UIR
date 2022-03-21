package cv05;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Knapsack {
    final static int ITEM_COUNT = 6;
    final List<Chromosome> CHROMOSOMES;
    final Item[] items;
    final int CAPACITY;

    public Knapsack(int CAPACITY, Item[] items) {
        this.CAPACITY = CAPACITY;
        this.items = items;
        this.CHROMOSOMES = new LinkedList<>();
    }

    public void getBestItemPermutation(int initChromosomeCount) {
        initChromosomePool(initChromosomeCount);


    }

    private void initChromosomePool(int initChromosomeCount) {
        Chromosome tmpC;
        while(CHROMOSOMES.size() != initChromosomeCount) {
            tmpC = new Chromosome(getRandomPermutation());
            if(isValidPermutation(tmpC)) {
                setChromosomeValue(tmpC);
                CHROMOSOMES.add(tmpC);
            }
        }
    }

    private boolean isValidPermutation(Chromosome c) {
        int actualWeight = 0;
        for(int i = 0; i < ITEM_COUNT; i++) {
            if(c.permutation[i]) {
                actualWeight += items[i].WEIGHT;
            }
        }

        return actualWeight <= CAPACITY;
    }

    private boolean[] getRandomPermutation() {
        boolean[] result = new boolean[ITEM_COUNT];
        Random r = new Random();

        for(boolean e: result) {
            e  = r.nextBoolean();
        }
        return result;
    }

    private void setChromosomeValue(Chromosome c) {
        for(int i = 0; i < ITEM_COUNT; i++) {
            if(c.permutation[i]) {
                c.value += items[i].RATIO;
            }
        }
    }
}