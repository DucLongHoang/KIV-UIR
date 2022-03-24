package cv05;

import java.util.*;

/**
 * Population class - generation of Chromosomes, capable of evolution and getting the best Chromosome
 * @author Long
 * @version 1.0
 */
public class Population {
    /** Static reference to Random shared by the class */
    final static Random R = new Random();
    /** Number of evolutions from the initial generation */
    final static int ITERATIONS = 3;
    /** Probability that a Chromosome mutates */
    final static double MUTATE_PROB = 0.1;
    /** Generation of Chromosomes */
    List<Chromosome> generation;
    /** Permutation length and initial pool size */
    final int PERM_LENGTH, INIT_POOL_SIZE;

    /**
     * Population constructor
     * @param permutationLength length of the permutation of each Chromosome
     * @param initPoolSize pool size of the first generation of Chromosomes
     */
    public Population(int permutationLength, int initPoolSize) {
        this.generation = new LinkedList<>();
        this.PERM_LENGTH = permutationLength;
        this.INIT_POOL_SIZE = initPoolSize;
    }

    /**
     * Method returns the best Chromosome after all evolutions
     * @param knapsack Knapsack the Population belongs to
     * @return best Chromosome with the highest price
     */
    public Chromosome getBestChromosome(Knapsack knapsack) {
        initChromosomePool();
        knapsack.setPopulationValues();
        zeroInvalidChromosomes(knapsack.CAPACITY);
        Collections.sort(generation);
        System.out.println("\n" + this.toString(0));

        for(int i = 0; i < ITERATIONS; i++) {
            generation = getNewGeneration();
            knapsack.setPopulationValues();
            zeroInvalidChromosomes(knapsack.CAPACITY);
            Collections.sort(generation);
            System.out.println("\n" + this.toString(i + 1));
        }

        return generation.get(0);
    }

    /**
     * Method sets price of Chromosome to 0 if it exceeds the Knapsack capacity
     * @param knapsackCapacity capacity of the Knapsack
     */
    private void zeroInvalidChromosomes(int knapsackCapacity) {
        for(Chromosome c: generation) {
            if(c.weight > knapsackCapacity) {
                c.price = 0;
            }
        }
    }

    /**
     * Method return a generation of new Chromosomes by crossing and mutating the current generation
     * @return new generation of Chromosomes
     */
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

    /**
     * Method initializes the first generation of Chromosomes
     */
    private void initChromosomePool() {
        while(generation.size() != INIT_POOL_SIZE) {
            generation.add(getRandomChromosome());
        }
    }

    /**
     * Method returns a new Chromosome with a random permutation
     * @return random Chromosome
     */
    private Chromosome getRandomChromosome() {
        boolean[] permutation = new boolean[PERM_LENGTH];
        for(int i = 0; i < PERM_LENGTH; i++) {
            permutation[i] = R.nextBoolean();
        }
        return new Chromosome(permutation);
    }

    /**
     * To String method
     * @param generation number of generation
     * @return String representation of the generation
     */
    public String toString(int generation) {
        StringBuilder sb = new StringBuilder("Population\n");
        sb.append("- Gen: ").append(generation).append("\n");
        sb.append("- Size: ").append(this.generation.size()).append("\n");
        for(Chromosome c : this.generation) {
            sb.append(c.toString()).append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}