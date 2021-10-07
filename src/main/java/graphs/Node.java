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
    // store hub-label with distance for each node
    private Map<Integer, Integer> hubDistances = new HashMap<>();
    private List<Node> pathToShortestHub;

    // for mst
    private boolean isVisited = false;

    public Node(Integer label) {
        this.label = label;
        this.adjacentNodes = new HashMap<>();
        this.shortestPath = new LinkedList<>();
        this.pathToShortestHub = new LinkedList<>();
    }

    public void addNeighbour(Node n, int distance) {
//        if ()
        adjacentNodes.put(n, distance);
    }


    // returns pair of closest hub with its distance as value
    public Integer getClosestHub()
    {
        if (this.isHubFarm) {
            return this.getLabel();
        }
        return Collections.min( this.getHubDistances().entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public int getAllAdjacentLen() {
        return this.getAdjacentNodes().values().stream().mapToInt(Integer::intValue).sum();
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
