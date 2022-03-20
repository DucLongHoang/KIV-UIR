package cv03;

import java.util.*;

public class HanoiTowerUninformed {
    Set<State> visitedStates;
    State firstState;
    State finishState;

    public HanoiTowerUninformed(int n){
        visitedStates = new HashSet<>();
        firstState = State.getFirstState(n);
        finishState = State.getFinishState(n);
    }

    public void solveDFS(){
        Stack<State> stateSpace = new Stack<>();
        State tmpState;
        List<State> newStates;

        System.out.println();

        stateSpace.push(firstState);
        while( !( ( tmpState = stateSpace.pop() ).equals(finishState) ) ){
            System.out.println(tmpState);
            if(visitedStates.contains(tmpState)) continue;
            newStates = tmpState.generateNewStates();
            newStates.removeIf(state -> visitedStates.contains(state));
            stateSpace.addAll(newStates);
            visitedStates.add(tmpState);
        }
        System.out.println(tmpState);
    }

    public void solveBFS(){
        Queue<State> stateSpace = new LinkedList<>();
        State tmpState;
        List<State> newStates;

        System.out.println();

        stateSpace.offer(firstState);
        while( !( ( tmpState = stateSpace.poll() ).equals(finishState) ) ){
            System.out.println(tmpState);
            if(visitedStates.contains(tmpState)) continue;
            newStates = tmpState.generateNewStates();
            newStates.removeIf(state -> visitedStates.contains(state));
            stateSpace.addAll(newStates);
            visitedStates.add(tmpState);
        }
        System.out.println(tmpState);
    }
}