package graphs;


import lombok.Data;

import java.util.*;

@Data
public class Graph {

    private int n_farms;      // F .. labeled with names 0,1, ... ,F-1
    private int n_roads;      // R
    private int n_hub_farms;  // H

    private Set<Vertex> vertices;

    public Graph(){
        this.vertices = new HashSet<>();
    }

    public boolean updateToHubFarm(int label) {
        Vertex v = new Vertex(label);
        Vertex found = vertices.stream().filter(v::equals).findAny().orElse(null);
        if (found != null) {
            found.setHubFarm(true);
            return true;
        }

        return false;
    }

    public boolean addVertex(Vertex v) {
        return vertices.add(v);
    }

    public void addEdge(Vertex v, Edge e) {
        Vertex found = vertices.stream().filter(v::equals).findAny().orElse(null);
        if (found != null)
            found.addEdge(e);
    }

    public List<Vertex> getListOfVertices() {
        return new ArrayList(vertices);
    }

}
