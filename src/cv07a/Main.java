package cv07a;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Main class - running app from here
 * @author Long
 * @version 1.0
 */
public class Main {
    /** K-means and K-medoid flag values */
    final static int K_MEANS = 0, K_MEDOID = 1;

    /**
     * Main method of app - entry point of app
     * @param args arguments from command line
     */
    public static void main(String[] args) {
        // given coordinates
        int[] xCoordinates = {2, 4, 10, 12, 3, 20, 30, 11, 25};
        int[] yCoordinates = {2, 4, 15, 18, 5, 50, 30, 34, 65};

        // init Points and centers of Clusters
        List<Point> points = initPointsList(xCoordinates, yCoordinates);
        List<Point> centers = new LinkedList<>();

        // init centers of Clusters
        centers.add(new Point(2, 2));
        centers.add(new Point(4, 4));

        // init clusters
        Map<Point, AbstractCluster> clusters = null;

        boolean change = true;
        while (change) {    // stop, until centers stop changing
            clusters = initClusters(centers, K_MEANS);
            change = cluster(clusters, points);
            centers = initCenterList(clusters);
        }

        for (Point center : centers) {
            System.out.format("Center of cluster is in: [x=%d, y=%d]\n", center.x, center.y);
        }

        System.out.println();
        Classifier classifier = new Classifier(clusters);
        classifier.classify(new Point(19, 49), -6);
    }

    /**
     * Method inits list of Points from given x/y coordinates
     * @param xCoord xCoordinates of Points
     * @param yCoord yCoordinates of Points
     * @return list of Points
     */
    public static List<Point> initPointsList(int[] xCoord, int[] yCoord) {
        List<Point> points = new LinkedList<>();
        for (int i = 0; i < xCoord.length; i++) {
            points.add(new Point(xCoord[i], yCoord[i]));
        }
        return points;
    }

    /**
     * Method initializes a List of Point which are centers of clusters
     * @param clusters to take centers from
     * @return List of center Points of clusters
     */
    public static List<Point> initCenterList(Map<Point, AbstractCluster> clusters) {
        List<Point> result = new ArrayList<>();
        for(AbstractCluster ac: clusters.values()) {
            result.add(ac.center);
        }
        return result;
    }

    /**
     * Method returns a map of entry <Point, Cluster>
     * @param centers to make Clusters around
     * @param clusterType K-means or K-medoid
     * @return map of center Points and clusters
     */
    public static Map<Point, AbstractCluster> initClusters(List<Point> centers, int clusterType) {
        Map<Point, AbstractCluster> clusters = new HashMap<>();
        switch (clusterType) {
            case K_MEANS -> {
                for (Point center : centers) {
                    clusters.put(center, new KMeansCluster(center));
                }
            }
            case K_MEDOID -> {
                for (Point center : centers) {
                    clusters.put(center, new KMedoidCluster(center));
                }
            }
        }
        return clusters;
    }


    /**
     * Method makes sorts List of Points to the nearest Cluster
     * @param clusters map of clusters
     * @param points   to assign to Clusters
     * @return true if Clusters changed, otherwise false
     */
    public static boolean cluster(Map<Point, AbstractCluster> clusters, List<Point> points) {
        boolean change = false;

        // find the closest center for each Point
        for (Point point : points) {
            Point closestCenter = getClosestClusterCenter(clusters, point);
            clusters.get(closestCenter).addToCluster(point);
        }

        // recenter all Clusters
        for (Map.Entry<Point, AbstractCluster> entry : clusters.entrySet()) {
            change |= entry.getValue().recenter();
        }
        return change;
    }

    /**
     * Method returns the closest center from a List of centers relative to another Point
     * @param clusters to find the closest one
     * @param p find center for this Point
     * @return closest center Point
     */
    public static Point getClosestClusterCenter(Map<Point, AbstractCluster> clusters, Point p) {
        double minDistance = Double.MAX_VALUE;
        Point result = null;

        for (AbstractCluster ac : clusters.values()) {
            if(ac.distanceFrom(p) < minDistance) {
                minDistance = ac.distanceFrom(p);
                result = ac.center;
            }
        }
        return result;
    }
}