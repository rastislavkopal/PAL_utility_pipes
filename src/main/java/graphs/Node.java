package graphs;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Node {

    // map lal neighoburs with the distance
    private Map<Node, Integer> adjacentNodes;
    private boolean isHubFarm = false;
    private Integer label;

    // for djikstra
    private List<Node> shortestPath;
    private Integer distance = Integer.MAX_VALUE;

    // for mst
    private boolean isVisited = false;

    public Node(Integer label) {
        this.label = label;
        this.adjacentNodes = new HashMap<>();
        this.shortestPath = new LinkedList<>();
    }

    public void addNeighbour(Node n, int distance) {
//        if ()
        adjacentNodes.put(n, distance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return label.equals(node.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }
}
