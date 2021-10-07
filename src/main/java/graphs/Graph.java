package graphs;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Graph {

    private int n_farms;      // F .. labeled with names 0,1, ... ,F-1
    private int n_roads;      // R
    private int n_hub_farms;  // H

    private Set<Node> nodes;

    public Graph(Set<Node> nodes) {
        this.nodes = nodes;
    }

    public Graph(){
        this.nodes = new HashSet<>();
    }

    public boolean addNode(Node n, int distance) {
        // graph already contains the node, so add his neighbours only
        if (nodes.contains(n)) {
            Node hub = nodes.stream().filter(i -> i.equals(n)).findAny().orElse(null);
            if (hub != null)
                hub.addNeighbour(n, distance);
            return true;
        }
        return this.nodes.add(n);
    }

    public boolean updateNodeToHubfarm(int nodeLabel) {
        if (nodes.contains(new Node(nodeLabel))){
            Node hub = nodes.stream().filter(n -> n.getLabel().intValue() == nodeLabel).findAny().orElse(null);
            if (hub != null) {
                hub.setHubFarm(true);
                return true;
            }
        }

        return false;
    }

}
