package lilleswing.gcj.a2008.r2.a;

import java.util.List;

public class Case {
    private final int goal;
    private final List<Node> nodes;

    public Case(final int goal, final List<Node> nodes) {
        this.goal = goal;
        this.nodes = nodes;
    }

    public void dp() {
        for(int i = nodes.size()-1; i >=1; i--) {
            int childIndex1 = i * 2;
            int childIndex2 = i * 2 + 1;
            if (childIndex2 >= nodes.size()) {
                continue;
            }

            Node child1 = nodes.get(childIndex1);
            Node child2 = nodes.get(childIndex2);
            nodes.get(i).setValue(child1, child2);
        }
    }

    public int flipsToGoal() {
        if (goal == 1) {
            return nodes.get(1).flipsTo1;
        }
        return nodes.get(1).flipsTo0;
    }
}
