package graphs;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
public class Vertex {

    private int label;
    private Set<Edge> edges; // set of adjacent vertices/neighbours
    private boolean isHubFarm = false;

    public Vertex(int label, Set<Edge> edges) {
        this.label = label;
        this.edges = edges;
    }

    public Vertex(int label) {
        this.label = label;
        edges = new HashSet<>();
    }

    public boolean addEdge(Edge e) {
        return this.edges.add(e);
    }

    public List getEdges() {
        return new ArrayList<>(edges);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return label == vertex.label;
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }
}
