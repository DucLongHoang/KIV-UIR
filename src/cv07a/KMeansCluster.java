package cv07a;

import java.awt.*;

/**
 * KMeansCluster class - extends AbstractCluster
 * Uses k-means for recentering
 * @author Long
 * @version 1.0
 */
public class KMeansCluster extends AbstractCluster {
    /**
     * Constructor for KMeansCluster
     * @param center of cluster
     */
    public KMeansCluster(Point center) {
        super(center);
    }

    /**
     * Method recenters the center point with k-means method
     * @return true if recentering happened, otherwise false
     */
    @Override
    public boolean recenter() {
        if(this.points.size() == 1)     // Cluster has only the Center
            return false;

        Point oldCenter = this.center.getLocation(); // save old location
        this.center.setLocation(0, 0);  // delete it

        // find new center by averaging all Points in Cluster
        for (Point p : points) {
            this.center.x += p.x;
            this.center.y += p.y;
        }
        this.center.x /= Math.max(points.size(), 1);
        this.center.y /= Math.max(points.size(), 1);

        return oldCenter.distance(center) > 0;    // false if location didn't change
    }
}