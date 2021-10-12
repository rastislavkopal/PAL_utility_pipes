package pal;

import java.util.*;

public class Graph {

    public int n_farms;
    public int n_roads;
    public int n_hubs;

    // maps each hub to adjacentNodes
    public Map<Integer, List<Node>> graph;
    public List<Integer> hubs;
    public List<Integer> undecidedNodes = new ArrayList<>();
    public Map<Integer, List<Node>> strippedGraph = new HashMap<>();
    public List<Node> nodesList = new ArrayList<>(); //  represents all existing nodes

    public Graph(int n_farms, int n_roads, int n_hubs, List<Integer> hubs, Map<Integer, List<Node>> graph) {
        this.n_farms = n_farms;
        this.n_roads = n_roads;
        this.n_hubs = n_hubs;
        this.graph = graph;
        this.hubs = hubs;
    }

    // iterate trough each node in nodeList
    // and put into strippedGraph nodes but:
    //              -> skip undecided
    //              -> remove undecided from node's adjacentList
    public void stripGraph() {
        for (Node n : nodesList) {
            if (n.isUndecided) {
                strippedGraph.put(n.label, new ArrayList<Node>());
            } else {
                List<Node> adjacentNodes = new ArrayList<>();

                for (Node neighbour : graph.get(n.label)) {
                    if (nodesList.get(neighbour.label).isUndecided)
                        continue;
                    if (n.hub == nodesList.get(neighbour.label).hub)
                        adjacentNodes.add(neighbour);
                }
                strippedGraph.put(n.label, adjacentNodes);
            }
        }
    }

    public void stripGraph0(){
        Iterator<Map.Entry<Integer, List<Node>>> entryIt = graph.entrySet().iterator();
        while (entryIt.hasNext()) {
            Map.Entry<Integer, List<Node>> entry = entryIt.next();
            if (undecidedNodes.contains(entry.getKey())){
                entryIt.remove();
                continue;
            }

            Iterator<Node> it = entry.getValue().iterator();
            while (it.hasNext()) {
                Node x = it.next();
                if (undecidedNodes.contains(x.label)){
                    it.remove();
                    continue;
                }
            }
        }
    }


}
