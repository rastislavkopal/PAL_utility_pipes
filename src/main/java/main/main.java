package main;

import graphs.Graph;
import graphs.Node;
import utils.Djikstra;
import utils.GraphReader;

import java.util.*;

public class main {
    public static void main(String[] args) {
        Graph g = new GraphReader().readGraphFromFile("./dataset/pub01.in");


        g = new Djikstra(g).calculateShortestPathFromSource(new Node(15));

        // run Djikstra from each hubFarm
        for (Node n : g.getNodes()) {
            if (n.isHubFarm()) {
                g = new Djikstra(g).calculateShortestPathFromSource(n);
                for (Node clr : g.getNodes())
                    clr.setDistance(Integer.MAX_VALUE);
            }
        }


        // remove nodes that are of same shortest-distance
        Iterator<Node> it = g.getNodes().iterator();
        List<Integer> toDel = new LinkedList<>();
        while (it.hasNext()) {
            Node n = it.next();
            if (n.isHubFarm())
                continue;

            Integer minVal = Collections.min(n.getHubDistances().values());
            int occur = 0;
            for (Map.Entry<Integer, Integer> entry : n.getHubDistances().entrySet())
                if (entry.getValue() == minVal) {
                    occur++;
                    if (occur > 1) { // remove node from g
                        toDel.add(n.getLabel());
                        it.remove();
                        break;
                    }
                }
        }

        // from each node of graph remove adjacentNode if was deleted earlier
        for (Node n : g.getNodes()) {

            // remove adjacent that is closer to another hub
            for (Iterator<Map.Entry<Node, Integer>> entryIt = n.getAdjacentNodes().entrySet().iterator(); entryIt.hasNext(); ) {
                Map.Entry<Node, Integer> entry = entryIt.next();

                // if neighbour's closestHub is different than the curClosestHub
                if (!n.getClosestHub().equals(entry.getKey().getClosestHub()))
                    entryIt.remove();
            }

            if (n.isHubFarm())
                continue;

            // remove adjacent that is undecided
            Iterator<Map.Entry<Node, Integer>> entryIt = n.getAdjacentNodes().entrySet().iterator();
            while (entryIt.hasNext()) {
                Map.Entry<Node, Integer> entry = entryIt.next();
                if (toDel.contains(entry.getKey().getLabel()))
                    entryIt.remove();
            }
        }

        int sum = 0;
        for (Node n : g.getNodes()) {
            sum += n.getAllAdjacentLen();
        }

        sum = sum / 2;


        System.out.println(sum + " " + (g.getN_farms() - g.getNodes().size()));
    }
}
