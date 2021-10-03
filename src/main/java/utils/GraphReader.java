package utils;

import graphs.Edge;
import graphs.Graph;
import graphs.Node;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.FileReader;

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

                    g.addVertex(v1);
                    g.addVertex(v2);
                    Edge e1 = new Edge(v2, Integer.parseInt(words[2]));
                    g.addEdge(v1, e1);

                    Edge e2 = new Edge(v1, Integer.parseInt(words[2]));
                    g.addEdge(v2, e2);
                }

                // List-of-HubFarms
                if (foundHubNumber) {
                    for (String w : words) {
                        g.updateToHubFarm(Integer.parseInt(w));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return g;
    }

}
