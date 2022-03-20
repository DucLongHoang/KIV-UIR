package cv03;

import java.util.Objects;

public class Disc {
    final int SIZE;

    public Disc(int size) {
        this.SIZE = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disc disc = (Disc) o;
        return SIZE == disc.SIZE;
    }

    @Override
    public int hashCode() {
        return Objects.hash(SIZE);
    }

    public Disc cloneDisc() {
        return new Disc(SIZE);
    }
}