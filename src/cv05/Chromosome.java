package cv05;

import java.util.Random;

/**
 * Chromosome class - represents the building block of a Population
 * It can mutate and cross with other Chromosomes
 * @author Long
 * @version 1.0
 */
public class Chromosome implements Comparable<Chromosome>{
    /** Static Random shared by all the whole class */
    final static Random R = new Random();
    /** Permutation of Chromosome */
    boolean[] permutation;
    /** Weight and price of Chromosome - sum of all Item attributes */
    int weight, price;

    /**
     * Constructor for Chromosome
     * @param permutation that the Chromosome has
     */
    public Chromosome(boolean[] permutation) {
        this.permutation = permutation;
        this.weight = 0;
        this.price = 0;
    }

    /**
     * Method that mutates(=flips) each vector element with given probability
     * @param probability that a vector element gets changed
     */
    public void mutate(double probability) {
        for(int i = 0; i < permutation.length; i++) {
            if(probability > R.nextDouble()){
//                System.out.println("Mutation at index: " + i);
//                System.out.println(getPermutation() + " mutate at index: " + i);
                permutation[i] = !permutation[i];
//                System.out.println(getPermutation());
            }
        }
    }

    /**
     * Method return 2 new Chromosomes as products of this Chromosome and param
     * Cross-point is randomly selected
     * @param other Chromosome to be crossed with
     * @return array of 2 new Chromosomes
     */
    public Chromosome[] generateByCrossing(Chromosome other) {
        boolean[][] newPermutations = new boolean[2][permutation.length];
        int cutOffPoint = new Random().nextInt(permutation.length);
//        System.out.println("Cutoff point: " + cutOffPoint);

        for(int i = 0; i < permutation.length; i++) {
            if(i < cutOffPoint) {
                newPermutations[0][i] = this.permutation[i];
                newPermutations[1][i] = other.permutation[i];
            }
            else {
                newPermutations[1][i] = this.permutation[i];
                newPermutations[0][i] = other.permutation[i];
            }
        }

        return new Chromosome[]{
                new Chromosome(newPermutations[0]),
                new Chromosome(newPermutations[1])
        };
    }

    /**
     * Getter for permutation represented in vector of 0s and 1s
     * @return permutation of Chromosome
     */
    public String getPermutation() {
        StringBuilder sb = new StringBuilder("Permutation: [");
        for(boolean e : permutation) {
            if(e) sb.append(" 1");
            else sb.append(" 0");
        }
        sb.append(" ]");
        return sb.toString();
    }

    /**
     * To String method
     * @return String representations of Chromosome
     */
    @Override
    public String toString() {
        return this.getPermutation() + " weight: " + weight + " price: " + price;
    }

    /**
     * Natural ordering from high to low
     * @param that other Chromosome to be compared with
     * @return -1 if this is bigger, 0 if equal and 1 if this is smaller
     */
    @Override
    public int compareTo(Chromosome that) {
        return (int) -Math.signum(this.price - that.price);
    }
}