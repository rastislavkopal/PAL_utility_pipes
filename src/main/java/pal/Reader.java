package pal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Reader {

    /*
    -- first line -> F R values

    -- lines specifying a road e.g.
        0 1 4
        first two are farm labels, third is length of the road

    -- Line with single integer H -> number of HUB Farms
    -- Next line contains H integers of labels of Hub Farms
 */
    public Graph readGraphFaster() throws IOException {
        Map<Integer, List<Node>> nodes = new HashMap<>();
        List<Integer> hubs = new ArrayList<>();
        int n_farms = 0, n_roads = 0, n_hubs = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n_farms = Integer.parseInt(st.nextToken());
        n_roads = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n_roads; i++){
                st = new StringTokenizer(br.readLine());
                Integer v1 = Integer.parseInt(st.nextToken());
                Integer v2 = Integer.parseInt(st.nextToken());
                int distance = Integer.parseInt(st.nextToken());

            if (nodes.containsKey(v1)) {
                nodes.get(v1).add(new Node(v2, distance));
            } else {
                List<Node> adjacentList = new ArrayList<>();
                adjacentList.add(new  Node(v2, distance));
                nodes.put(v1, adjacentList);
            }

            // 1 --> 0
            if (nodes.containsKey(v2)) {
                nodes.get(v2).add(new Node(v1, distance));
            } else {
                List<Node> adjacentList = new ArrayList<>();
                adjacentList.add(new  Node(v1, distance));
                nodes.put(v2, adjacentList);
            }
        }

        st = new StringTokenizer(br.readLine());
        n_hubs = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i =0; i < n_hubs; i++){
            hubs.add(Integer.parseInt(st.nextToken()));
        }


        return new Graph(n_farms, n_roads, n_hubs, hubs, nodes);
    }


    public Graph readGraph() throws IOException {
        Map<Integer, List<Node>> nodes = new HashMap<>();
        List<Integer> hubs = new ArrayList<>();
        int n_farms = 0, n_roads = 0, n_hubs = 0;

        boolean isFirst = true, foundHubNumber = false;
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while ((line = br.readLine()) != null) {
            String[] words = line.split(" ");

            // first-line: save N_farms and N_roads
            if (isFirst) {
                n_farms = Integer.parseInt(words[0]);
                n_roads = Integer.parseInt(words[1]);
                isFirst = false;
                continue;
            }

            // Number-of-HubFarms
            if (words.length == 1) {
                foundHubNumber = true;
                n_hubs = Integer.parseInt(words[0]);
                continue;
            }

            // All-edges --> first two are farm labels, third is length of the road
            if (!isFirst && !foundHubNumber) {
                // node with idx already exists --> append new node to it's neighbours
                // 0 --> 1
                int cost = Integer.parseInt(words[2]);
                if (nodes.containsKey(Integer.parseInt(words[0]))) {
                    nodes.get(Integer.parseInt(words[0])).add(new Node(Integer.parseInt(words[1]), cost));
                } else {
                    List<Node> adjacentList = new ArrayList<>();
                    adjacentList.add(new  Node(Integer.parseInt(words[1]), cost));
                    nodes.put(Integer.parseInt(words[0]), adjacentList);
                }

                // 1 --> 0
                if (nodes.containsKey(Integer.parseInt(words[1]))) {
                    nodes.get(Integer.parseInt(words[1])).add(new Node(Integer.parseInt(words[0]), cost));
                } else {
                    List<Node> adjacentList = new ArrayList<>();
                    adjacentList.add(new  Node(Integer.parseInt(words[0]), cost));
                    nodes.put(Integer.parseInt(words[1]), adjacentList);
                }
            }

            // List-of-HubFarms
            if (foundHubNumber) {
                for (String w : words) {
                    hubs.add(Integer.parseInt(w));
                }
            }
        }
        return new Graph(n_farms, n_roads, n_hubs, hubs, nodes);
    }
}
