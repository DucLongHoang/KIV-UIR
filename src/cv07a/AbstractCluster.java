package cv07a;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * AbstractCluster abstract class - to be inherited by k-means and k-medoid clusters
 * @author Long
 * @version 1.0
 */
public abstract class AbstractCluster {
    /** Center of cluster */
    protected Point center;
    /** Points of cluster */
    protected List<Point> points;
    /** Number of closest neighbours */
    protected int closestNeighbours;

    /**
     * Constructor for AbstractCluster
     * @param center of AbstractCluster
     */
    public AbstractCluster(Point center) {
        this.center = center;
        this.points = new LinkedList<>();
        this.closestNeighbours = 0;
    }

    /**
     * Method to check if a Point is in the cluster or not
     * @param p Point to check if it's in the cluster or not
     * @return true if cluster contains the Point, otherwise false
     */
    public boolean contains(Point p) {
        return points.contains(p);
    }

    /**
     * Method recenters the center Point
     * @return true if recentering changed the center of the cluster otherwise false
     */
    public abstract boolean recenter();

    /**
     * Method adds a Point to the cluster
     * @param p Point to be added to the cluster
     */
    public void addToCluster(Point p) {
        this.points.add(p);
    }

    /**
     * Getter for cluster points
     * @return List of Points of a cluster
     */
    public List<Point> getClusterPoints() {
        return points;
    }

    /**
     * Getter for closest neighbours count
     * @return number of closest neighbours
     */
    public int getClosestNeighbours() {
        return closestNeighbours;
    }

    /**
     * Method returns distance of the Cluster-center from a given Point
     * @param p Point to get the distance from the center of
     * @return distance of Point p from center
     */
    public double distanceFrom(Point p){
        return this.center.distance(p);
    }

    /**
     * To String method
     * @return String representation of a cluster
     */
    @Override
    public String toString() {
        return "Center of " + this.getClass().getSimpleName() +
                " is " + String.format("[x=%d, y=%d]", center.x, center.y);
    }
}