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
        List<Node> allHubs = new ArrayList<>();

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
                    continue;
                }

                // All-edges --> first two are farm labels, third is length of the road
                if (!isFirst && !foundHubNumber) {
                    Node v1 = allNodes.stream().filter(n -> n.getLabel() == Integer.parseInt(words[0])).findAny().orElse(null);
                    Node v2 = allNodes.stream().filter(n -> n.getLabel() == Integer.parseInt(words[1])).findAny().orElse(null);
                    if (v1 == null)
                        v1 = new Node(Integer.parseInt(words[0]));
                    if (v2 == null)
                        v2 = new Node(Integer.parseInt(words[1]));

                    int len = Integer.parseInt(words[2]);
                    v1.addNeighbour(v2, len);
                    v2.addNeighbour(v1, len);
                    allNodes.add(v1);
                    allNodes.add(v2);
                }

                // List-of-HubFarms
                if (foundHubNumber) {
                    for (String w : words) {
                        Node hub = allNodes.stream().filter(n -> n.getLabel() == Integer.parseInt(w)).findAny().orElse(null);
                        if (hub != null) {
                            hub.setHubFarm(true);
                            allHubs.add(hub); // add to the list of hub farms
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // update each node with the list of hubs and set distance to Ing.Max
        for (Node x : allNodes) {
            for (Node iHub : allHubs) {
                x.getHubDistances().put(iHub.getLabel(), Integer.MAX_VALUE);
            }
        }

        g.setNodes(new HashSet<>(allNodes));
        return g;
    }

}
