package cv04;

import java.io.IOException;

public class Main {
    static final String DIR = "./data/";
    static final String PATH_TO_FILE = "cv4_vstup.txt";

    public static void main(String[] args) throws IOException {
        Parser parser = new Parser(DIR + PATH_TO_FILE);
        Graph graph = new Graph(parser.start, parser.finish, parser.nodes);

        System.out.println();
        graph.aStarSearch(false);
    }
}
