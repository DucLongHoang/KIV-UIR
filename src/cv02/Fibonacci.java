package cv02;

public class Fibonacci extends AbstractProblem{

    @Override
    protected int solve(int n) {
        int a = 1;
        int b = 0;
        for(int i = 1; i <= n; i++){
            b = a + b;
            a = b - a;
        }
        return b;
    }

    @Override
    protected int solveRecursively(int n) {
        if(n < 2) return n;
        else return solveRecursively(n - 1) + solveRecursively(n - 2);
    }
}
