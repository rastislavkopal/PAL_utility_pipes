package main;

import graphs.Graph;
import graphs.Node;
import utils.Djikstra;
import utils.GraphReader;
import utils.Prim;

import java.util.List;
import java.util.Map;

public class main {
    public static void main(String[] args) {
        Graph g = new GraphReader().readGraphFromFile("./dataset/pub01.in");

        // print graph
        for (Node v : g.getNodes()){
            System.out.print(v.getLabel());
            for (Map.Entry<Node, Integer> e : v.getAdjacentNodes().entrySet()) {
                System.out.print(" ->" + e.getKey().getLabel() + "{" + e.getValue() + "} ");
            }
            System.out.println("");
        }

        Node source = g.getNodes().stream().filter(n -> n.getLabel() == 15).findAny().orElse(null);
        g = new Djikstra().calculateShortestPathFromSource(g, source);

        // iterate each node and for hub farms run djikstra
//        for (Node n : g.getNodes()) {
//            if (n.isHubFarm()) {
//                g = new Djikstra(g).calculateShortestPathFromSource(n, n.getLabel());
//                break;
//            }
//        }
        System.out.println("...");

//        List<Node> mst = new Prim(g.getVertices()).run();
//
//        // print minimum spanning tree
//        for (Node n : mst) {
//            for (Edge e : n.getEdges()){
//                if (e.isIncluded()) {
//                    System.out.println(n.getLabel() + " --> ;" + e.getWeight() + "; -->" +e.getDestination().getLabel() );
//                }
//            }
//        }


    }
}
