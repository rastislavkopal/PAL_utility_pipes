package graphs;


import lombok.Data;

import java.util.*;

@Data
public class Graph {

    private int n_farms;      // F .. labeled with names 0,1, ... ,F-1
    private int n_roads;      // R
    private int n_hub_farms;  // H

    private List<Node> vertices;

    public Graph(){
        this.vertices = new ArrayList<>();
    }

    public boolean updateToHubFarm(int label) {
        Node v = new Node(label);
        Node found = vertices.stream().filter(v::equals).findAny().orElse(null);
        if (found != null) {
            found.setHubFarm(true);
            return true;
        }

        return false;
    }

    public boolean addVertex(Node v) {
        if (vertices.stream().filter(v::equals).findAny().orElse(null) == null)
            return vertices.add(v);
        return false;
    }

    // add one-way edge :: from v->
    public void addEdge(Node v, Edge e) {
        Node found = vertices.stream().filter(v::equals).findAny().orElse(null);
        if (found != null)
            found.addEdge(e);
    }

}
