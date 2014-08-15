package lilleswing.gcj.a2008.r2.a;

public class Node {
    public static final int SOFT_MAX = Integer.MAX_VALUE/4;
    private NodeType nodeType = null;
    private boolean flippable = false;
    int flipsTo0 = SOFT_MAX;
    int flipsTo1 = SOFT_MAX;

    public Node(int nodeType, int flippable) {
        if (nodeType == 1) {
            this.nodeType = NodeType.AND;
        } else {
            this.nodeType = NodeType.OR;
        }

        if (flippable == 1) {
            this.flippable = true;
        } else {
            this.flippable = false;
        }
    }

    public Node(int value) {
        if (value == 0) {
            flipsTo0 = 0;
        }
        if (value == 1) {
            flipsTo1 = 0;
        }
    }

    /**
     * Sets values based on children
     */
    public void setValue(Node a, Node b) {
        if (this.nodeType == null) {
            return;
        }
        this.flipsTo0 = minFlipsTo0(nodeType, a, b);
        this.flipsTo1 = minFlipsTo1(nodeType, a, b);

        if (!this.flippable) {
            return;
        }
        int otherTo0 = minFlipsTo0(NodeType.other(nodeType), a, b) + 1; // this flipped
        this.flipsTo0 = min(this.flipsTo0, otherTo0);
        int otherTo1 = minFlipsTo1(NodeType.other(nodeType), a, b) + 1; // this flipped
        this.flipsTo1 = min(this.flipsTo1, otherTo1);
    }

    private int minFlipsTo1(NodeType nodeType, Node a, Node b) {
        if (nodeType == NodeType.OR) {
            return orFlipsTo1(a, b);
        }
        return andFlipsTo1(a, b);
    }

    private int andFlipsTo1(Node a, Node b) {
        return softMax(a.flipsTo1 + b.flipsTo1);
    }

    private int orFlipsTo1(Node a, Node b) {
        int way1 = a.flipsTo1 + b.flipsTo0;
        int way2 = a.flipsTo0 + b.flipsTo1;
        int way3 = a.flipsTo1 + b.flipsTo1;
        int min1 = min(way1, way2);
        int min2 = min(min1, way3);
        return softMax(min2);
    }

    private int minFlipsTo0(NodeType nodeType, Node a, Node b) {
        if (nodeType == NodeType.AND) {
            return andFlipsTo0(a, b);
        }
        return orFlipsTo0(a, b);
    }

    private int orFlipsTo0(Node a, Node b) {
        int way1 = a.flipsTo0 + b.flipsTo0;
        return softMax(way1);
    }

    private int andFlipsTo0(Node a, Node b) {
        int way1 = a.flipsTo0 + b.flipsTo1;
        int way2 = a.flipsTo1 + b.flipsTo0;
        int way3 = a.flipsTo0 + b.flipsTo0;
        int min1 = min(way1, way2);
        int min2 = min(min1, way3);
        return softMax(min2);
    }

    private int softMax(int a) {
        if (a > SOFT_MAX) {
            return SOFT_MAX;
        }
        return a;
    }

    private int min(int a, int b) {
        if (a < b) {
            return a;
        }
        return b;
    }
}
enum NodeType {
    AND,
    OR;

    public static NodeType other(final NodeType nodeType) {
        if (nodeType == AND) {
            return NodeType.OR;
        }
        return NodeType.AND;
    }
}
