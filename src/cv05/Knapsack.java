package cv05;

import java.util.*;
import java.util.stream.Collectors;

public class Knapsack {
    final static int ITEM_COUNT = 6, ITERATIONS = 10;
    final List<Chromosome> CHROMOSOMES;
    final Item[] ITEMS;
    final int CAPACITY;

    public Knapsack(int CAPACITY, Item[] items) {
        this.CAPACITY = CAPACITY;
        this.ITEMS = items;
        this.CHROMOSOMES = new LinkedList<>();
    }

    public void getBestItemPermutation(int initChromosomeCount) {
        initChromosomePool(initChromosomeCount);
        sortList(CHROMOSOMES);

        for(int i = 0; i < ITERATIONS; i++) {
            System.out.println("Crossing 2 permutations");
            Chromosome[] newPool = CHROMOSOMES.get(0).generateByCrossing(CHROMOSOMES.get(1));
            newPool = removeInvalidPermutations(newPool);
            if(newPool.length < 2) {
                System.out.println("Failed crossing");
                continue;
            }
            updateChromosomePool(newPool);
        }

    }

    private Chromosome[] removeInvalidPermutations(Chromosome[] newPool) {
        List<Chromosome> result = new ArrayList<>();
        for(Chromosome c: newPool) {
            if(c.weight <= CAPACITY) {
                result.add(c);
            }
        }
        return result.toArray(new Chromosome[0]);
    }

    private void initChromosomePool(int initChromosomeCount) {
        Chromosome tmpC;
        while(CHROMOSOMES.size() != initChromosomeCount) {
            tmpC = new Chromosome(getRandomPermutation());
            setChromosomeValues(tmpC);
            if(tmpC.weight <= CAPACITY) {
                CHROMOSOMES.add(tmpC);
                tmpC.printPermutation();
            }
        }
    }

    private void updateChromosomePool(Chromosome[] newPool) {
        CHROMOSOMES.clear();
        CHROMOSOMES.addAll(List.of(newPool));
        for(Chromosome c: CHROMOSOMES) {
            setChromosomeValues(c);
            c.printPermutation();
        }
        sortList(CHROMOSOMES);
    }

    private boolean[] getRandomPermutation() {
        boolean[] result = new boolean[ITEM_COUNT];
        Random r = new Random();

        for(int i = 0; i < ITEM_COUNT; i++) {
            result[i] = r.nextBoolean();
        }
        return result;
    }

    private void setChromosomeValues(Chromosome c) {
        for(int i = 0; i < ITEM_COUNT; i++) {
            if(c.permutation[i]) {
                c.price += ITEMS[i].PRICE;
                c.weight += ITEMS[i].WEIGHT;
                c.value += ITEMS[i].RATIO;
            }
        }
    }

    private void sortList(List<Chromosome> listToSort) {
        Collections.sort(listToSort);
        Collections.reverse(listToSort);
    }
}