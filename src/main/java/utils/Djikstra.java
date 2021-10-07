package utils;

import graphs.Graph;
import graphs.Node;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Setter
@Getter
public class Djikstra {

    private Graph graph;
    private Node mainSource;

    public Djikstra(Graph graph){
        this.graph = graph;
    }

    public Graph calculateShortestPathFromSource(Node source) {
        mainSource = source;
        source.setDistance(0);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            Node currentNode = unsettledNodes.stream().min(Comparator.comparing(Node::getDistance)).orElse(null);
            unsettledNodes.remove(currentNode);
            for (Map.Entry<Node, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return this.graph;
    }

    private void calculateMinimumDistance(Node evaluationNode, Integer edgeWeight, Node sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeight < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeight);
            evaluationNode.getHubDistances().put(mainSource.getLabel(), sourceDistance + edgeWeight); // add per hub
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

}