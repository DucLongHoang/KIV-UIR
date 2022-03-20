package cv02;

import java.util.Stack;

public class HanoiTower extends AbstractProblem{
    @Override
    protected int solve(int n) {
        return solveRecursively(n);
    }

    @Override
    protected int solveRecursively(int n) {
        solveRecursively(n - 1, 'L', 'M', 'R');
        return (int) (Math.pow(2, n) - 1);
    }

    private void solveRecursively(int n, char L, char M, char R){
        if(n == 0) {
            System.out.println("\t\tMove disc " + n + " from " + L + " to " + R);
        }
        else {
            solveRecursively(n - 1, L, R, M);
            System.out.println("\t\tMove disc " + n + " from " + L + " to " + R);
            solveRecursively(n - 1, M, L, R);
        }
    }
}
