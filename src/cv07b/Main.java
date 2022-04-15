package cv07b;

/**
 * Main class - run app from here
 * @author Long
 * @version 1.0
 */
public class Main {

    /**
     * main method, run program from here
     * @param args arguments from command line
     */
    public static void main(String[] args) {
        Shape userShape = new InputHandler().readData();

        Shape[] shapes = new Shape[] {
                Shapes.Circle.getInstance(),
                Shapes.Pill.getInstance(),
                Shapes.Square.getInstance(),
                Shapes.Rectangle.getInstance(),
                Shapes.Triangle.getInstance(),
                Shapes.House.getInstance()
        };

        double tmpDistance, minDistance = Double.MAX_VALUE;
        Shape shape = null;

        for(Shape tmp: shapes) {
            tmpDistance = userShape.getEuclidDistance(tmp);
            if(tmpDistance < minDistance) {
                minDistance = tmpDistance;
                shape = tmp;
            }
        }

        assert shape != null;
        System.out.println("\nThe shape you inputted is probably a : " + shape.getClass().getSimpleName());
    }
}
