package graphs;


import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Data
public class Graph {

    private int n_farms;      // F .. labeled with names 0,1, ... ,F-1
    private int n_roads;      // R
    private int n_hub_farms;  // H

    private Set<Vertex> vertices;

    public Graph(){
        this.vertices = new TreeSet<>();
    }


    public boolean addNewVertex(Vertex v) {
        return vertices.add(v);
    }

    public void addEdge(Vertex v, Edge e) {
        Vertex found = vertices.stream().filter(v::equals).findAny().orElse(null);
        if (found != null)
            found.addEdge(e);
    }

}
