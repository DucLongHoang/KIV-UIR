package cv07b;

/**
 * Shape class - class, base for other geometrical shapes
 * @author Long
 * @version 1.0
 */
public class Shape {
    /** number of straight line */
    protected final int LINE_COUNT;
    /** number of unique lengths of lines */
    protected final int UNIQUE_LINES;
    /** number of corners */
    protected final int CORNERS;
    /** number of half-circles */
    protected final int SEMI_CIRCLES;

    /**
     * Constructor for Shape
     * @param lineCount total count of lines
     * @param uniqueLines number of lines with unique lengths
     * @param corners number of corners
     * @param semiCircles number of semicircles
     */
    public Shape(int lineCount, int uniqueLines, int corners, int semiCircles) {
        this.LINE_COUNT = lineCount;
        this.UNIQUE_LINES = uniqueLines;
        this.CORNERS = corners;
        this.SEMI_CIRCLES = semiCircles;
    }

    /**
     * toString method
     * @return String representation of Shape
     */
    @Override
    public String toString() {
        return "Shape {" +
                "LINE_COUNT=" + LINE_COUNT +
                ", UNIQUE_LINES=" + UNIQUE_LINES +
                ", CORNERS=" + CORNERS +
                ", SEMI_CIRCLES=" + SEMI_CIRCLES +
                '}';
    }

    /**
     * Method return Euclid distance of other Shape from this Shape object
     * @param other Shape to calculate distance from
     * @return the Euclid distance in double
     */
    public double getEuclidDistance(Shape other) {
        double result = 0;
        result += Math.pow(this.LINE_COUNT - other.LINE_COUNT, 2);
        result += Math.pow(this.UNIQUE_LINES - other.UNIQUE_LINES, 2);
        result += Math.pow(this.CORNERS - other.CORNERS, 2);
        result += Math.pow(this.SEMI_CIRCLES - other.SEMI_CIRCLES, 2);

        return result;
    }
}