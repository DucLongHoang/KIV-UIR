package cv06;

import java.awt.*;

/**
 * KMedoidCluster class - extends AbstractCluster
 * Uses k-medoid for recentering
 * @author Long
 * @version 1.0
 */
public class KMedoidCluster extends AbstractCluster{

    /**
     * Constructor for KMedoidCluster
     * @param center of cluster
     */
    public KMedoidCluster(Point center) {
        super(center);
    }

    /**
     * Method recenters the center point with k-medoid method
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

        // find the closest Point to the calculated center
        Point closest = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
        for(Point p: points) {
            if(p.distance(center) < closest.distance(center)) {
                closest = p;
            }
        }

        return tmp.distance(closest) > 0;    // return false if location didn't change
    }
}
