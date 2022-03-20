package cv02;

public class Factorial extends AbstractProblem{
    @Override
    protected int solve(int n) {
        int result = 1;
        for(int i = 1; i <= n; i++){
            result *= i;
        }
        return result;
    }

    @Override
    protected int solveRecursively(int n) {
        if(n == 0) return 1;
        else return n * solveRecursively(n - 1);
    }
}
