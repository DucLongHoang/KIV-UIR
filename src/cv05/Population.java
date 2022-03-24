package cv05;

import java.util.*;

public class Population {
    final static Random R = new Random();
    final static int ITERATIONS = 3;

    List<Chromosome> generation;
    final int PERM_LENGTH, INIT_POOL_SIZE;
    final double MUTATE_PROB = 0.1;

    public Population(int permutationLength, int initPoolSize) {
        this.generation = new LinkedList<>();
        this.PERM_LENGTH = permutationLength;
        this.INIT_POOL_SIZE = initPoolSize;
    }

    public Chromosome getBestChromosome(Knapsack knapsack) {
        initChromosomePool();
        knapsack.setPopulationValues();
        zeroInvalidChromosomes(knapsack.CAPACITY);
        sortList(generation);
        System.out.println("\n" + this.toString(0));

        for(int i = 0; i < ITERATIONS; i++) {
            generation = getNewGeneration();
            knapsack.setPopulationValues();
            zeroInvalidChromosomes(knapsack.CAPACITY);
            sortList(generation);
            System.out.println("\n" + this.toString(i + 1));
        }

        return generation.get(0);
    }

    private void zeroInvalidChromosomes(int knapsackCapacity) {
        for(Chromosome c: generation) {
            if(c.weight > knapsackCapacity) {
                c.price = 0;
            }
        }
    }

    private List<Chromosome> getNewGeneration() {
        List<Chromosome> newGen = new LinkedList<>();
        this.generation.removeIf(e -> e.price == 0);

        for(int i = 0; i < generation.size(); i++) {
            int index = R.nextInt(generation.size());
            while(index == i) {
                index = R.nextInt(generation.size());
            }
            Chromosome[] crossbreeds = generation.get(i).
                    generateByCrossing(generation.get(index));

            newGen.addAll(Arrays.asList(crossbreeds));
        }

        for(Chromosome c: newGen) {
            c.mutate(MUTATE_PROB);
        }

        return newGen;
    }

    private void initChromosomePool() {
        while(generation.size() != INIT_POOL_SIZE) {
            generation.add(getRandomChromosome());
        }
    }

    private Chromosome getRandomChromosome() {
        boolean[] permutation = new boolean[PERM_LENGTH];

        for(int i = 0; i < PERM_LENGTH; i++) {
            permutation[i] = R.nextBoolean();
        }

        return new Chromosome(permutation);
    }

    private void sortList(List<Chromosome> listToSort) {
        Collections.sort(listToSort);
        Collections.reverse(listToSort);
    }

    public String toString(int iteration) {
        StringBuilder sb = new StringBuilder("Population\n");
        sb.append("- Gen: ").append(iteration).append("\n");
        sb.append("- Size: ").append(generation.size()).append("\n");
        for(Chromosome c : generation) {
            sb.append(c.toString()).append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}