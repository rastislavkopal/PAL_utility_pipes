package graphs;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Edge {

    private Node destination;
    private int weight;
    private boolean isIncluded = false;

    public Edge(Node destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return weight == edge.weight &&
                destination.equals(edge.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(destination, weight);
    }
}
