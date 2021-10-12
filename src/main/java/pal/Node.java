package pal;

import java.util.Comparator;
import java.util.Objects;

public class Node implements Comparable<Node>, Comparator<Node> {
    public int label;
    public int distance;
    public Node hub;
    public Node parent;
    public boolean isUndecided = false;

    public Node(int label, int distance) {
        this.label = label;
        this.distance = distance;
    }

    public Node(int label, int distance, Node hub, Node parent) {
        this.label = label;
        this.distance = distance;
        this.hub = hub;
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return label == node.label &&
                distance == node.distance &&
                Objects.equals(hub, node.hub) &&
                Objects.equals(parent, node.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, distance, hub, parent);
    }

    @Override
    public int compare(Node o1, Node o2) {
        if (o1.distance < o2.distance)
            return -1;
        if (o1.distance > o2.distance)
            return 1;

        return 0;
    }

    @Override
    public int compareTo(Node o) {
        if (this.distance < o.distance)
            return -1;
        if (this.distance > o.distance)
            return 1;

        return 0;
    }
}
