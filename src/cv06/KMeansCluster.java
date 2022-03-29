package cv06;

import java.awt.*;

/**
 * KMeansCluster class - extends AbstractCluster
 * Uses k-means for recentering
 * @author Long
 * @version 1.0
 */
public class KMeansCluster extends AbstractCluster{

    /**
     * Constructor for KMeansCluster
     * @param center of cluster
     */
    public KMeansCluster(Point center) {
        super(center);
    }

    /**
     * Method recenters the center point with k-means method
     * @return true if recentering happened otherwise false
     */
    @Override
    public boolean recenter() {
        Point tmp = this.center.getLocation(); // save old location
        this.center.setLocation(0, 0);  // delete it
        for(Point p: points){   // find new center by averaging all Points in Cluster
            this.center.x += p.x;
            this.center.y += p.y;
        }
        this.center.x /= Math.max(points.size(), 1);
        this.center.y /= Math.max(points.size(), 1);

        return tmp.distance(center) > 0;    // return false if location didn't change
    }
}
