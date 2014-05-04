package lilleswing.gcj.a2014.a1.b;


import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import lilleswing.gcj.util.Problem;
import lilleswing.gcj.util.datastructures.graph.Node;
import lilleswing.gcj.util.datastructures.graph.UndirectedGraph;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class FullBinaryTree extends Problem<Case> {

    @Override
    public String solve(Case aCase) {
        final List<Integer> subTreeSizes = Lists.newArrayList();
        final List<Node> nodes = aCase.getGraph().getNodes();
        for(Node node: nodes) {
            int size = subTreeSize(node, null);
            subTreeSizes.add(size);
        }
        return String.valueOf(nodes.size() - Collections.max(subTreeSizes));
    }

    public int subTreeSize(Node toCheck, Node from) {
        final List<Node> children = toCheck.getToNodes();
        if(children.size() == 1 ||
                children.size() == 2 && from != null && children.contains(from)) { // just the parent || only one child
            return 1; // just this node
        }
        final List<Integer> subTreeSizes = Lists.newArrayList();
        for(Node child: children) {
            if(child == from) {
                continue;
            }
            subTreeSizes.add(subTreeSize(child, toCheck));
        }
        List<Integer> largest = Ordering.natural().greatestOf(subTreeSizes, 2);
        return largest.get(0) + largest.get(1) + 1;
    }

    @Override
    public List<Case> parse(String data) {
        final Scanner sc = new Scanner(data);
        final List<Case> cases = Lists.newArrayList();
        final int numCases = sc.nextInt();
        for (int i = 0; i < numCases; i++) {
            int n = sc.nextInt();
            UndirectedGraph g = new UndirectedGraph(n);
            for(int j = 0; j < n - 1; j++) {
                int from = sc.nextInt() - 1;
                int to = sc.nextInt() - 1;
                g.addEdge(from, to);
            }
            Case aCase = new Case(g);
            cases.add(aCase);
        }
        return cases;
    }

    @Override
    public void preCompute() {
    }

    public static void main(String[] args) {
        Problem problem = new FullBinaryTree();
        problem.main(args[0]);
    }
}

class Case {
    private UndirectedGraph graph;

    public Case(UndirectedGraph g) {
        this.graph = g;
    }

    public UndirectedGraph getGraph() {
        return graph;
    }
}
