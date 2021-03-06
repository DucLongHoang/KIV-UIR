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
     * @return true if recentering happened, otherwise false
     */
    @Override
    public boolean recenter() {
        if(this.points.size() == 1)     // Cluster has only the Center
            return false;

        Point oldCenter = this.center.getLocation(); // save old location
        this.center.setLocation(0, 0);  // delete it

        // find new center by averaging all Points in Cluster
        for(Point p: points){
            this.center.x += p.x;
            this.center.y += p.y;
        }
        this.center.x /= Math.max(points.size(), 1);
        this.center.y /= Math.max(points.size(), 1);

        // find the closest Point to the calculated center
        Point medoid = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
        for(Point p: points) {
            if(p.distance(center) < medoid.distance(center)) {
                medoid = p;
            }
        }
        this.center.setLocation(medoid);

        return oldCenter.distance(center) > 0;    // false if location didn't change
    }
}