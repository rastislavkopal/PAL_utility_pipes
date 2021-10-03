package main;

import graphs.Edge;
import graphs.Graph;
import graphs.Node;
import utils.GraphReader;
import utils.Prim;

import java.util.List;

public class main {
    public static void main(String[] args) {
        Graph g = new GraphReader().readGraphFromFile("./dataset/pub01.in");

        // print graph
        for (Node v : g.getVertices()){
            System.out.print(v.getLabel());
            for (Edge e :v.getEdges()) {
                System.out.print(" ->" + e.getDestination().getLabel() + "{" + e.getWeight() + "} ");
            }
            System.out.println("");
        }

        List<Node> mst = new Prim(g.getVertices()).run();
        System.out.println("");
    }
}
