package cv06;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;

/**
 * Main class - running app from here
 * @author Long
 * @version 1.0
 */
public class Main {
    /**
     * Main method of app - entry point of app
     * @param args arguments from command line
     */
    public static void main(String[] args) {
        int[] xCoordinates = {2, 4, 10, 12, 3, 20, 30, 11, 25};
        int[] yCoordinates = {2, 4, 15, 18, 5, 50, 30, 34, 65};

        List<Point> points = initPointsList(xCoordinates, yCoordinates);
        List<Point> centers = new LinkedList<>();
        centers.add(points.get(0));
        centers.add(points.get(1));

        Map<Point, KMeansCluster> kMeansClusters = initKMeansClusters(centers);
        Map<Point, KMedoidCluster> kMedoidClusters = initKMedoidClusters(centers);

        boolean change = true;
        while(change) {
            change = cluster(kMeansClusters, points);
        }

        for(AbstractCluster c: kMeansClusters.values()) {
            System.out.println(c);
        }

    }

    /**
     * Method inits list of Points from given x/y coordinates
     * @param xCoord xCoordinates of Points
     * @param yCoord yCoordinates of Points
     * @return list of Points
     */
    public static List<Point> initPointsList(int[] xCoord, int[] yCoord) {
        List<Point> points = new LinkedList<>();

        for(int i = 0; i < xCoord.length; i++) {
            points.add(new Point(xCoord[i], yCoord[i]));
        }

        return points;
    }

    /**
     * Method returns a map of center Point and its KMeansCluster
     * @param centers to make KMeansClusters around
     * @return map of center Points and clusters
     */
    public static Map<Point, KMeansCluster> initKMeansClusters(List<Point> centers) {
        Map<Point, KMeansCluster> clusters = new HashMap<>();
        for(Point center: centers) {
            clusters.put(center, new KMeansCluster(center));
        }
        return clusters;
    }

    /**
     * Method returns a map of center Point and its KMedoidCluster
     * @param centers to make KMedoidClusters around
     * @return map of center Points and clusters
     */
    public static Map<Point, KMedoidCluster> initKMedoidClusters(List<Point> centers) {
        Map<Point, KMedoidCluster> clusters = new HashMap<>();
        for(Point center: centers) {
            clusters.put(center, new KMedoidCluster(center));
        }
        return clusters;
    }

    /**
     * Method clusters Points to the nearest cluster
     * @param clusters
     */
    public static boolean cluster(Map<Point, ? extends AbstractCluster> clusters, List<Point> points) {
        boolean change = false;

        // get centers of all clusters
        List<Point> centers = new LinkedList<>();
        for(AbstractCluster ac: clusters.values()) {
            centers.add(ac.center);
        }

        // find the closest center Point for each Point
        for(Point point: points){
            Point closestCenter = new Point();

            // finding center
            for(Point center: centers){
                if(center.distance(point) < closestCenter.distance(point)){
                    closestCenter = center;
                }
            }

            // originally created closestCenter
            assert (closestCenter.x != 0 && closestCenter.y != 0);
            clusters.get(closestCenter).addToCluster(point);
        }

        // recenter clusters
        for(Map.Entry<Point, ? extends AbstractCluster> entry: clusters.entrySet()){
            change |= entry.getValue().recenter();
        }

        return change;
    }
}
