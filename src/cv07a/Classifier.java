package cv07a;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Classifier class - classifies Points to the nearest cluster
 * @author Long
 * @version 1.0
 */
public class Classifier {
    /** Clusters to classify to */
    final Map<Point, AbstractCluster> CLUSTERS;
    /** List of all points from all the clusters */
    final List<Point> ALL_POINTS;

    /**
     * Constructor for Classifier
     * @param clusters to classify incoming objects to
     */
    public Classifier(Map<Point, AbstractCluster> clusters) {
        this.CLUSTERS = clusters;
        this.ALL_POINTS = new LinkedList<>();
        for(AbstractCluster ac : CLUSTERS.values()) {
            ALL_POINTS.addAll(ac.points);
        }
    }

    /**
     * Method classifies Point to the nearest cluster
     * @param point to be classified
     * @param neighbourCount if 0, classify by cluster center,
     *                       if odd classify to cluster by neighbours
     *                       else return error message
     */
    public void classify(Point point, int neighbourCount) {
        // sanity checks
        assert neighbourCount >= 0 : "Neighbour count is less then 0";
        assert neighbourCount <= ALL_POINTS.size() : "Neighbour count is more than the number of all points";

        if(neighbourCount == 0) {
            classifyByCenter(point);
        }
        else {
            classifyByNeighbours(point, neighbourCount);
        }
    }

    /**
     * Method classifies a Point according to its nearest neighbours
     * @param point to be classified
     * @param neighbourCount number of nearest neighbours to take into consideration
     */
    private void classifyByNeighbours(Point point, int neighbourCount) {
        // sanity check
        assert neighbourCount % 2 == 1 : "Cannot classify with an even number of closest neighbours";

        // init variables
        Point[] closestNeighbours = new Point[neighbourCount];
        Map<AbstractCluster, Integer> frequencies = new HashMap<>();

        // sort all points by distance from the reference point
        ALL_POINTS.sort(Comparator.comparingDouble(n -> n.distance(point)));

        // get closest neighbours
        for(int i = 0; i < neighbourCount; i++) {
            closestNeighbours[i] = ALL_POINTS.get(i);
        }

        // count clusters frequencies of closest neighbours
        AbstractCluster tmpCluster;
        for(int i = 0; i < neighbourCount; i++) {
            tmpCluster = getClusterByPoint(closestNeighbours[i]);
            if(!frequencies.containsKey(tmpCluster)) {
                frequencies.put(tmpCluster, 1);
            }
            else {
                int freq = frequencies.get(tmpCluster);
                freq++;
                frequencies.put(tmpCluster, freq);
            }
        }

        // find Cluster with most occurrences
        int maxFreq = 0;
        tmpCluster = null;
        for(Map.Entry<AbstractCluster, Integer> entry: frequencies.entrySet()) {
            if(maxFreq < entry.getValue()) {
                maxFreq = entry.getValue();
                tmpCluster = entry.getKey();
            }
        }

        assert tmpCluster != null : "This should not happen...";
        System.out.format("Point [x=%d, y=%d] is closest to cluster with a center in [x=%d, y=%d]",
                point.x, point.y, tmpCluster.center.x, tmpCluster.center.y);
    }

    /**
     * Method return a cluster to which the Point belongs to
     * @param p Point to find the cluster to
     * @return cluster the Point belongs to,
     * otherwise throw NoSuchElementException
     */
    private AbstractCluster getClusterByPoint(Point p) {
        for(AbstractCluster ac: CLUSTERS.values()) {
            if(ac.contains(p)) {
                return ac;
            }
        }
        throw new NoSuchElementException(
                String.format("No cluster found for Point [x=%d, y=%d]", p.x, p.y)
        );
    }

    /**
     * Method classifies Point to the cluster with the nearest center
     * @param point to be classified
     */
    private void classifyByCenter(Point point) {
        // init max value variables
        double minDistance = Double.MAX_VALUE;
        Point center = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
        // find the closest center
        for (AbstractCluster ac : CLUSTERS.values()) {
            if(ac.distanceFrom(point) < minDistance) {
                minDistance = ac.distanceFrom(point);
                center = ac.center;
            }
        }
        System.out.format("Point [x=%d, y=%d] is closest to cluster with a center in [x=%d, y=%d]",
                point.x, point.y, center.x, center.y);
    }
}