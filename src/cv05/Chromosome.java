package cv05;

import java.util.Random;

public class Chromosome implements Comparable<Chromosome>{
    boolean[] permutation;
    int weight, price;
    double value;

    public Chromosome(boolean[] permutation) {
        this.permutation = permutation;
        this.weight = 0;
        this.price = 0;
        this.value = 0;
    }

    public Chromosome[] generateByCrossing(Chromosome other) {
        boolean[][] newPermutations = new boolean[2][permutation.length];
        int cutOffPoint = new Random().nextInt(permutation.length);
        System.out.println("Cutoff point: " + cutOffPoint);

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

    public void printPermutation() {
        StringBuilder sb = new StringBuilder("Permutation: [");
        for(boolean e : permutation) {
            if(e) sb.append(" 1");
            else sb.append(" 0");
        }
        sb.append(" ] -").append(" weight: " + weight).append(" price: " + price).append(" value: " + value);
        System.out.println(sb);
    }

    @Override
    public int compareTo(Chromosome that) {
        return (int) Math.signum(this.value - that.value);
    }
}