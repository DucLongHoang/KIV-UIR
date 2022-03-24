package cv05;

import java.util.Random;

public class Chromosome implements Comparable<Chromosome>{
    final static Random R = new Random();
    boolean[] permutation;
    int weight, price;

    public Chromosome(boolean[] permutation) {
        this.permutation = permutation;
        this.weight = 0;
        this.price = 0;
    }

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

    public String getPermutation() {
        StringBuilder sb = new StringBuilder("Permutation: [");
        for(boolean e : permutation) {
            if(e) sb.append(" 1");
            else sb.append(" 0");
        }
        sb.append(" ]");
        return sb.toString();
    }

    @Override
    public String toString() {
        return this.getPermutation() + " weight: " + weight + " price: " + price;
    }

    @Override
    public int compareTo(Chromosome that) {
        return (int) Math.signum(this.price - that.price);
    }
}