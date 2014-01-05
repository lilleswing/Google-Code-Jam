package lilleswing.gcj.a2008.b1.mousetrap;

import com.google.common.collect.Lists;

import java.util.List;

class Node {
    public long size;
    public List<Integer> deck;
    public long empty;

    public Node(long size) {
        this.empty = size;
        this.size = size;
        this.deck = Lists.newArrayListWithExpectedSize((int) size);
    }

    public void place(int index, int value) {
        deck.set(index, value);
        empty--;
    }
}
