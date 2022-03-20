package cv04;

import java.util.*;

public class Graph {
    char start, target;
    Map<Character, Node> nodeMap;

    public Graph(char start, char target, List<Node> nodes){
        this.start = start;
        this.target = target;
        this.nodeMap = new HashMap<>();
        for(Node node: nodes){
            this.nodeMap.put(node.name, node);
        }
    }

    public void aStarSearch(boolean withAirDistance){
        Node currentNode = this.nodeMap.get(start);
        currentNode.distanceFromStart = 0;

        if(!withAirDistance){
            for(Node node: nodeMap.values()) node.airDistance = 0;
        }

        List<Node> path = new LinkedList<>();
        path.add(currentNode);

        System.out.println("Expansion cost = distance from start + air distance from target");
        while(expandPath(path));
    }

    private boolean expandPath(List<Node> list){
        int currentDistance, newDistance;
        boolean pathExpanded = false;

        if(list.isEmpty()) return pathExpanded;
        sortByDistance(list); // take currently the cheapest Node
        Node currentNode = list.get(0);     // get first node
        list.remove(currentNode);  // pop the Node

        System.out.println("Expanding from Node " + currentNode.name);

        // loop through neighbours of current node
        for(Map.Entry<Character, Integer> entry: currentNode.neighbours.entrySet()){

            Node tmpNeighbour = this.nodeMap.get(entry.getKey());   // temporary neighbour

            currentDistance = tmpNeighbour.distanceFromStart;
            newDistance = currentNode.distanceFromStart + entry.getValue();

            if(currentDistance > newDistance) {     // a shorter distance was found
                tmpNeighbour.distanceFromStart = newDistance;
                if(tmpNeighbour.name == target) {
                    System.out.println("\tPath to target Node " + tmpNeighbour.name
                            + " found! Cost: " + tmpNeighbour.distanceFromStart);
                    System.out.println("\tBut I keep looking for a better path");
                    pathExpanded = true;
                }
                else {
                    list.add(tmpNeighbour);
                    System.out.println("\tNewly found Node " + tmpNeighbour.name
                            + ", cost from parent = " + tmpNeighbour.distanceFromStart);
                    pathExpanded = true;
                }
            }
        }
        return pathExpanded;
    }

    private void sortByDistance(List<Node> nodes){
        nodes.sort(Comparator.comparingInt(n -> n.distanceFromStart + n.airDistance));

        System.out.println("\t" + "-".repeat(30));
        for(Node node: nodes){
            int totalCost = node.distanceFromStart + node.airDistance;
            System.out.print("\tNode " + node.name + " cost = " + totalCost
                    + ", (" + node.distanceFromStart + " + " + node.airDistance + ") \n");
        }
        System.out.println("\t" + "-".repeat(30));
    }

    public static class Node {
        char name;
        int distanceFromStart, airDistance;
        Map<Character, Integer> neighbours;

        public Node(char name, int airDistance){
            this.name = name;
            this.distanceFromStart = Integer.MAX_VALUE / 2;
            this.airDistance = airDistance;
            this.neighbours = new HashMap<>();
        }

        public void addNeighbour(char name, int airDistance){
            this.neighbours.put(name, airDistance);
        }
    }
}