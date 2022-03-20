package cv04;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    char start, finish;
    String pathToFile;
    List<Graph.Node> nodes;

    final String START_NODE = "start:";
    final String TARGET_NODE = "cil:";
    final String LIST_DELIMITER = ";";
    final String ADJACENCY_LIST = "seznam sousednosti:";
    final String AIR_DISTANCE_LIST = "vzdusna vzdalenost od cile:";

    public Parser(String pathToFile) throws IOException {
        this.pathToFile = pathToFile;
        this.nodes = new LinkedList<>();
        parseFile();
    }

    public void parseFile() throws IOException {
        Scanner sc = new Scanner(Paths.get(pathToFile));
        while(sc.hasNextLine()){
            String lineFromScanner = sc.nextLine();
            switch(lineFromScanner){

                case START_NODE -> this.start = sc.nextLine().charAt(0);
                case TARGET_NODE -> this.finish = sc.nextLine().charAt(0);

                case ADJACENCY_LIST -> {
                    String line = sc.nextLine();
                    String[] parsedLine;

                    // create Nodes
                    while(!line.equals(AIR_DISTANCE_LIST)){
                        parsedLine = line.split(LIST_DELIMITER);

                        Graph.Node tmp = new Graph.Node(parsedLine[0].charAt(0), 0);

                        for(int i = 1; i < parsedLine.length; i++){
                            String[] parsedNeighbour = parsedLine[i].split("=");
                            tmp.addNeighbour(parsedNeighbour[0].charAt(0), Integer.parseInt(parsedNeighbour[1]));
                        }

                        this.nodes.add(tmp);

                        line = sc.nextLine();
                    }

                    // add neighbours to Nodes
                    while(sc.hasNextLine()){
                        line = sc.nextLine();

                        for(Graph.Node node: nodes){
                            if(line.charAt(0) == node.name){
                                node.airDistance = Integer.parseInt(line.substring(2));
                            }
                        }
                    }
                }
            }
        }
    }
}