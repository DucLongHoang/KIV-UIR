package cv02;

public class Main {
    public static void main(String[] args) {
        int n = 5;
        AbstractProblem[] aProblems = {new HanoiTower(), new Factorial(), new Fibonacci()};

        for(AbstractProblem ap: aProblems){
            System.out.println(ap.getClass().getSimpleName());

            if(ap instanceof HanoiTower) {
                System.out.println("\tSolve for n = " + n + " steps = " + ap.solve(n));
            }
            else {
                System.out.println("\tSolve normally for n = " + n + " result = " + ap.solve(n));
                System.out.println("\tSolve recursively for n = " + n + " result = " + ap.solveRecursively(n));
            }
        }
    }
}
