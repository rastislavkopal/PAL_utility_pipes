package graphs;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Node {

    private int label;
    private List<Edge> edges; // list of adjacent vertices/neighbours
    private boolean isHubFarm = false;
    private boolean isVisited = false;

    public Node(int label, List<Edge> edges) {
        this.label = label;
        this.edges = edges;
    }

    public Node(int label) {
        this.label = label;
        edges = new ArrayList<>();
    }

    public boolean addEdge(Edge e) {
        return this.edges.add(e);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return label == node.label;
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }
}
