package main;

import graphs.Graph;
import graphs.Vertex;
import utilities.GraphReader;

public class main {
    public static void main(String[] args) {
        Graph g = new GraphReader().readGraphFromFile("./dataset/pub01.in");
        for (Vertex v : g.getListOfVertices())
            System.out.println(v.getLabel());
    }
}
