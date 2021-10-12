package pal;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
//        long startTime = System.nanoTime();
        Graph g = new Reader().readGraphFaster();
//        long endTime = System.nanoTime();
//        System.out.println("reader time: " + (endTime - startTime));

//        startTime = System.nanoTime();
        new Dijkstra(g).run();
//        endTime = System.nanoTime();
//        System.out.println("dijkstra time: " + (endTime - startTime));


//        printGraph(g);
//        startTime = System.nanoTime();
        g.stripGraph();
//        endTime = System.nanoTime();
//        System.out.println("stripTime time: " + (endTime - startTime));
        Prim p = new Prim(g);

//        startTime = System.nanoTime();
        int sum = 0;
        for (Integer i : g.hubs) {
            sum += p.run(i);
        }
//        endTime = System.nanoTime();
//        System.out.println("prim time: " + (endTime - startTime));
        System.out.println(sum + " " + g.undecidedNodes.size());
    }

    public static void printGraph(Graph g ) {
        for (Map.Entry<Integer, List<Node>> entry : g.graph.entrySet()){
            System.out.print(entry.getKey());
            for (Node n : entry.getValue()){
                System.out.print(" ---> " + n.label + " {" + n.distance + "} ");
            }
            System.out.println("");
        }

        for (Integer i : g.hubs)
            System.out.print(" " + i + " ,");
    }
}
