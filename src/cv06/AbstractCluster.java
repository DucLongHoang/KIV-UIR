package cv06;

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

    /**
     * Constructor for AbstractCluster
     * @param center of AbstractCluster
     */
    public AbstractCluster(Point center) {
        this.center = center;
        this.points = new LinkedList<>();
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