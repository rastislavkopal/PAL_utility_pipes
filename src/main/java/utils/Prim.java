package utils;

import graphs.Node;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Prim {

    private List<Node> graph;
    private List<Node> visited;

    public Prim(List<Node> graph){
        this.graph = graph;
        this.visited = new ArrayList<>();
    }

    public List<Node> run() {
        if (this.graph.size() == 0)
            return null;

        // starting edge -> can be random -> set it to visited and save all edges
        visited.add(graph.get(0));
        visited.get(0).setVisited(true);

//        Edge minEdge = findMinEdge();
//
//        while (true) {
//            for (Node n : graph){
//                if (n.getLabel() == minEdge.getDestination().getLabel()) {
//                    visited.add(n);
//                    n.setVisited(true);
//                    break;
//                }
//            }
//            if (!isUnvisited())
//                break;
//
//            minEdge = findMinEdge();
//        }

        return this.graph;
    }

    private boolean isUnvisited(){
        for (Node it : graph) {
            if (!it.isVisited())
                return true;
        }
        return false;
    }
}
