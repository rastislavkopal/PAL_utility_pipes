package utils;

import graphs.Graph;
import graphs.Node;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GraphReader {

    /*
    -- first line -> F R values

    -- lines specifying a road e.g.
        0 1 4
        first two are farm labels, third is length of the road

    -- Line with single integer H -> number of HUB Farms
    -- Next line contains H integers of labels of Hub Farms

    It holds, 2 ≤ F ≤ 104, 1 ≤ R ≤ 106, 1 ≤ H ≤ F. All road lengths are positive integers less than 1000.
     */
    public Graph readGraphFromFile(String filepath) {

        Graph g = new Graph();
        List<Node> allNodes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            boolean isFirst = true, foundHubNumber = false;

            while ((line = br.readLine()) != null) {
                String[] words = line.split(" ");

                // first-line: save N_farms and N_roads
                if (isFirst) {
                    g.setN_farms(Integer.parseInt(words[0]));
                    g.setN_roads(Integer.parseInt(words[1]));
                    isFirst = false;
                    continue;
                }

                // Number-of-HubFarms
                if (words.length == 1) {
                    foundHubNumber = true;
                    g.setN_hub_farms(Integer.parseInt(words[0]));
                }

                // All-edges --> first two are farm labels, third is length of the road
                if (!isFirst && !foundHubNumber) {
                    Node v1 = new Node(Integer.parseInt(words[0]));
                    Node v2 = new Node(Integer.parseInt(words[1]));
                    int len = Integer.parseInt(words[2]);
                    v1.addNeighbour(v2, len);
                    v2.addNeighbour(v1, len);

                    // if node with label l does not exist yet, ..
                    Node v1old = allNodes.stream().filter(n -> n.getLabel() == v1.getLabel()).findAny().orElse(null);
                    Node v2old = allNodes.stream().filter(n -> n.getLabel() == v2.getLabel()).findAny().orElse(null);
                    if (v1old == null) {
                        allNodes.add(v1);
                    } else { // node alredy exists -> add neighbour
                        v1old.addNeighbour(v2, len);
                    }

                    if (v2old == null) {
                        allNodes.add(v2);
                    } else { // node alredy exists -> add neighbour
                        v2old.addNeighbour(v1, len);
                    }

//                    g.addNode(v1, len);
//                    g.addNode(v2, len);
                }

                // List-of-HubFarms
                if (foundHubNumber) {
                    for (String w : words) {
                        g.updateNodeToHubfarm(Integer.parseInt(w));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        g.setNodes(new HashSet<>(allNodes));
        return g;
    }

}
