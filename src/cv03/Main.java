package cv03;

public class Main {

    public static void main(String[] args) {
        HanoiTowerUninformed htu = new HanoiTowerUninformed(3);
        htu.solveDFS();
    }
}