package pal;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {

    private Graph graph;


    public Dijkstra(Graph graph) {
        this.graph = graph;
    }

    // set distance from each hub to 0
    // add each hub to priorityQueue before start
    // set parent and hub to each hub as reference to itself
    public void run() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i =0; i < graph.n_farms; i++)
            graph.nodesList.add(i, new Node(i, Integer.MAX_VALUE));

        for (Integer h : graph.hubs) {
            Node currentHub = graph.nodesList.get(h);
            currentHub.distance = 0;
            currentHub.hub = currentHub;
            currentHub.parent = currentHub;
            pq.offer(currentHub);
        }
        
        while (!pq.isEmpty()) {
            Node u = pq.poll();
            for (Node v : graph.graph.get(u.label)) { // for each neighbour of u
                int newDistance = u.distance + v.distance;
                if (newDistance < graph.nodesList.get(v.label).distance) { // new distance would is smaller => update
                    graph.nodesList.get(v.label).distance = newDistance;
                    graph.nodesList.get(v.label).parent = u;
                    graph.nodesList.get(v.label).hub = graph.nodesList.get(u.hub.label);
                    graph.nodesList.get(v.label).isUndecided = false;
                    pq.offer(graph.nodesList.get(v.label));
                } else if (newDistance == graph.nodesList.get(v.label).distance && ( !graph.nodesList.get(v.label).hub.equals(u.hub))){
                    v.isUndecided = true;
                    graph.nodesList.get(v.label).isUndecided = true;
                    // create new Node -> it would override old with same label but from different hub... :) OMG WTF
                    pq.offer(new Node(v.label, newDistance, graph.nodesList.get(u.hub.label), u));
                }
            }
        }
//         save undecidedNodes
        for (Node x : graph.nodesList){
            if (x.isUndecided){
                graph.undecidedNodes.add(x.label);
            }
        }
    }
}
