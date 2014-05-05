package lilleswing.gcj.util.datastructures.graph;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Used by UndirectedGraph
 */
public class Node {
    private List<Node> fromNodes;
    private List<Node> toNodes;
    private int index;

    public Node(int index) {
        fromNodes = Lists.newArrayList();
        toNodes = Lists.newArrayList();
        this.index = index;
    }

    public List<Node> getFromNodes() {
        return fromNodes;
    }

    public void setFromNodes(List<Node> fromNodes) {
        this.fromNodes = fromNodes;
    }

    public List<Node> getToNodes() {
        return toNodes;
    }

    public void setToNodes(List<Node> toNodes) {
        this.toNodes = toNodes;
    }
}
