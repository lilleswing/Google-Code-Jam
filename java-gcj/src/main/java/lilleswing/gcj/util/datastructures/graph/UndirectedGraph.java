package lilleswing.gcj.util.datastructures.graph;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * not very generic, might only be good for FullBinaryTree Problem
 */
public class UndirectedGraph {
    final List<Node> nodes;
    public UndirectedGraph(int numNodes) {
        nodes = Lists.newArrayListWithCapacity(numNodes);
        for(int i = 0; i < numNodes; i++) {
            nodes.add(new Node(i));
        }
    }

    public void addEdge(int from, int to) {
        Node a = nodes.get(from);
        Node b = nodes.get(to);
        a.getFromNodes().add(b);
        a.getToNodes().add(b);
        b.getFromNodes().add(a);
        b.getToNodes().add(a);
    }

    public List<Node> getNodes() {
        return nodes;
    }
}
