package cv07b;

/**
 * Shapes class - wrapper class, used to get instances of every shape from one class
 */
public class Shapes {
    /**
     * Triangle class - extends Shape
     */
    static class Triangle extends Shape {
        /**
         * Constructor for Shape
         *
         * @param lineCount   total count of lines
         * @param uniqueLines number of lines with unique lengths
         * @param corners     number of corners
         * @param semiCircles number of semicircles
         */
        public Triangle(int lineCount, int uniqueLines, int corners, int semiCircles) {
            super(lineCount, uniqueLines, corners, semiCircles);
        }

        /**
         * Method creates instance of Triangle
         * @return instance of triangle
         */
        public static Shape getInstance() {
            return new Triangle(3, 2, 3, 0);
        }
    }

    /**
     * Square class - extends Shape
     */
    static class Square extends Shape {
        /**
         * Constructor for Shape
         *
         * @param lineCount   total count of lines
         * @param uniqueLines number of lines with unique lengths
         * @param corners     number of corners
         * @param semiCircles number of semicircles
         */
        public Square(int lineCount, int uniqueLines, int corners, int semiCircles) {
            super(lineCount, uniqueLines, corners, semiCircles);
        }

        /**
         * Method return an instance of Square
         *
         * @return instance of Square
         */
        public static Square getInstance() {
            return new Square(4, 1, 4, 0);
        }
    }

    /**
     * Circle class - extends Shape
     */
    static class Circle extends Shape {
        /**
         * Constructor for Shape
         *
         * @param lineCount   total count of lines
         * @param uniqueLines number of lines with unique lengths
         * @param corners     number of corners
         * @param semiCircles number of semicircles
         */
        public Circle(int lineCount, int uniqueLines, int corners, int semiCircles) {
            super(lineCount, uniqueLines, corners, semiCircles);
        }

        /**
         * Method return an instance of Square
         *
         * @return instance of Square
         */
        public static Circle getInstance() {
            return new Circle(0, 0, 0, 2);
        }
    }

    /**
     * Pill class - extends Shape
      */
    static class Pill extends Shape {
        /**
         * Constructor for Shape
         *
         * @param lineCount   total count of lines
         * @param uniqueLines number of lines with unique lengths
         * @param corners     number of corners
         * @param semiCircles number of semicircles
         */
        public Pill(int lineCount, int uniqueLines, int corners, int semiCircles) {
            super(lineCount, uniqueLines, corners, semiCircles);
        }

        /**
         * Method return an instance of Square
         *
         * @return instance of Square
         */
        public static Pill getInstance() {
            return new Pill(2, 1, 0, 2);
        }
    }

    /**
     * Rectangle class - extends Shape
     */
    static class Rectangle extends Shape {
        /**
         * Constructor for Shape
         *
         * @param lineCount   total count of lines
         * @param uniqueLines number of lines with unique lengths
         * @param corners     number of corners
         * @param semiCircles number of semicircles
         */
        public Rectangle(int lineCount, int uniqueLines, int corners, int semiCircles) {
            super(lineCount, uniqueLines, corners, semiCircles);
        }

        /**
         * Method return an instance of Square
         *
         * @return instance of Square
         */
        public static Rectangle getInstance() {
            return new Rectangle(4, 2, 4, 0);
        }
    }

    /**
     * House class - extends Shape
     */
    static class House extends Shape {
        /**
         * Constructor for Shape
         *
         * @param lineCount   total count of lines
         * @param uniqueLines number of lines with unique lengths
         * @param corners     number of corners
         * @param semiCircles number of semicircles
         */
        public House(int lineCount, int uniqueLines, int corners, int semiCircles) {
            super(lineCount, uniqueLines, corners, semiCircles);
        }

        /**
         * Method return an instance of Square
         *
         * @return instance of Square
         */
        public static House getInstance() {
            return new House(5, 2, 5, 0);
        }
    }
}