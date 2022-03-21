package cv05;

public class Chromosome implements Comparable{
    boolean[] permutation;
    double value;

    public Chromosome(boolean[] permutation) {
        this.permutation = permutation;
        this.value = 0;
    }

    @Override
    public int compareTo(Object o) {
        if(o != null) {
            Chromosome that = (Chromosome) o;
            return (int) Math.signum(this.value - that.value);
        }
        return 0;
    }
}