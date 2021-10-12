package pal;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Prim {

    private Graph g;
    private int costSum = 0;
    private Set<Integer> visited = new HashSet<>();

    public Prim(Graph graph) {
        this.g = graph;
    }

    private void prepare(){
        this.costSum = 0;
    }

    public int run(Integer source) {
        this.prepare();

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(g.nodesList.get(source));


        while (!pq.isEmpty()) {
            Node u = pq.poll();

            if (!visited.contains(u.label)) {
                costSum += u.distance;
                visited.add(u.label);
                for (Node n : g.strippedGraph.get(u.label)) {
                    if(!visited.contains(n.label))
                        pq.offer(n);
                }
            }
        }
        return costSum;
    }
}
