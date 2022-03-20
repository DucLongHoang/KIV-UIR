package cv03;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class State {
    int numOfParents;
    static int NUM_OF_DISKS;
    List<Disc> left, middle, right;
    List<List<Disc>> allPoles;
    private static final State INVALID_STATE = new State(null, null, null, 0);

    private State(List<Disc> left, List<Disc> middle, List<Disc> right, int numOfParents) {
        this.left = left;
        this.middle = middle;
        this.right = right;
        this.numOfParents = numOfParents;

        this.allPoles = new LinkedList<>();
        this.allPoles.add(this.left);
        this.allPoles.add(this.middle);
        this.allPoles.add(this.right);
    }

    public static State getFirstState(int n){
        NUM_OF_DISKS = n;
        List<Disc> left = new LinkedList<>();
        for(int i = n; i > 0; i--){
            left.add(new Disc(i));
        }
        List<Disc> middle = new LinkedList<>();
        List<Disc> right = new LinkedList<>();

        return new State(left, middle, right, 0);
    }

    public static State getFinishState(int n) {
        List<Disc> left = new LinkedList<>();
        List<Disc> middle = new LinkedList<>();
        List<Disc> right = new LinkedList<>();
        for(int i = n; i > 0; i--){
            right.add(new Disc(i));
        }

        return new State(left, middle, right, 0);
    }

    private boolean isValidState(){
        if(this.equals(INVALID_STATE)) return false;

        for(int i = 1; i < left.size(); i++){
            if(left.get(i).SIZE > left.get(i - 1).SIZE)
                return false;
        }

        for(int i = 1; i < middle.size(); i++){
            if(middle.get(i).SIZE > middle.get(i - 1).SIZE)
                return false;
        }

        for(int i = 1; i < right.size(); i++){
            if(right.get(i).SIZE > right.get(i - 1).SIZE)
                return false;
        }

        return true;
    }

    public List<State> generateNewStates(){
        List<State> result = new LinkedList<>();

        result.add(moveDisc('L', 'M'));
        result.add(moveDisc('L', 'R'));
        result.add(moveDisc('M', 'L'));
        result.add(moveDisc('M', 'R'));
        result.add(moveDisc('R', 'L'));
        result.add(moveDisc('R', 'M'));

        result.removeIf(s -> !s.isValidState());

        return result;
    }

    private State moveDisc(char from, char to){
        State newState = this.cloneState();

        List<Disc> tmpFrom = newState.getSameList(from);
        List<Disc> tmpTo = newState.getSameList(to);

        if(tmpFrom.size() == 0) return INVALID_STATE;

        Disc tmpDisc = tmpFrom.get(tmpFrom.size() - 1);
        tmpFrom.remove(tmpFrom.size() - 1);   // pop last disc
        tmpTo.add(tmpDisc);    // put in last position of destination

        return newState;
    }

    private List<Disc> getSameList(char poleToFind) {
        switch(poleToFind){
            case 'L' -> {
                return this.left;
            }
            case 'M' -> {
                return this.middle;
            }
            case 'R' -> {
                return this.right;
            }
        }
        throw new NoSuchElementException();
    }

    private List<Disc> listDeepCopy(List<Disc> toBeCopied){
        List<Disc> copy = new LinkedList<>();
        for(Disc d: toBeCopied){
            copy.add(d.cloneDisc());
        }
        return copy;
    }

    private State cloneState(){
        return new State(
                this.listDeepCopy(left),
                this.listDeepCopy(middle),
                this.listDeepCopy(right),
                this.numOfParents + 1
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(left, state.left) && Objects.equals(middle, state.middle)
                && Objects.equals(right, state.right) && Objects.equals(allPoles, state.allPoles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, middle, right, allPoles);
    }

    @Override
    public String toString() {
        StringBuilder[] sBuilders = {new StringBuilder(), new StringBuilder(), new StringBuilder()};
        String name = "LMR";

        for(int i = 0; i < sBuilders.length; i++){
            sBuilders[i].append(" ".repeat(numOfParents)).append(name.charAt(i)).append(": ");
            for(Disc d: this.allPoles.get(i)){
                sBuilders[i].append(d.SIZE).append(" ");
            }
            sBuilders[i].append("\n");
        }

        return sBuilders[0].append(sBuilders[1].append(sBuilders[2])).toString();
    }
}