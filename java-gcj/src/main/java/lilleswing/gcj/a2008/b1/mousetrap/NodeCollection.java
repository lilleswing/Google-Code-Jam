package lilleswing.gcj.a2008.b1.mousetrap;

import com.google.common.collect.Lists;

import java.util.List;

public class NodeCollection {
    private static final int BLOCK_SIZE = 1000;
    public long size;
    public long empty;
    public int index;
    public List<Node> nodes;

    public NodeCollection(long size) {
        this.size = size;
        nodes = Lists.newArrayList();
        long tSize = size;
        while (tSize >= BLOCK_SIZE) {
            final Node node = new Node(BLOCK_SIZE);
            nodes.add(node);
        }
        final Node node = new Node(tSize);
        node.place(0, 1);
        this.index = 1;
        this.empty = size - 1;
        nodes.add(node);
    }

    public int place(int k) {
        int position = (int) ((k + this.index - 1) % empty);
        int distance = position;
        int nodeNum = 0;
        while(distance > nodes.get(nodeNum).empty) {
            distance -= nodes.get(nodeNum).empty;
            nodeNum++;
        }
        nodes.get(nodeNum).place(distance, k);
        empty--;
        this.index = position + 1;
        return this.index;
    }
}
