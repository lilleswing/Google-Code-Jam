package lilleswing.gcj.util.datastructures.fenwicktree;




import com.google.common.collect.Lists;

import java.util.List;

/**
 *  In this implementation, the tree is represented by a vector<int>.
 * Elements are numbered by 0, 1, ..., n-1.
 * tree[i] is sum of elements with indexes i&(i+1)..i, inclusive.
 * (Note: this is a bit different from what is proposed in Fenwick's article.
 * To see why it makes sense, think about the trailing 1's in binary
 * representation of indexes.)
 * NOTE (LESWING) can turn into an interface with an "add" method to not
 * duplicate logic with ModFenwickTree
 */
public class FenwickTree {
    private long[] tree;
    public FenwickTree(List<Long> inTree) {
        this.tree = new long[inTree.size()];

        for(int i = 0 ; i<inTree.size(); i++)
            increase(i, inTree.get(i));
    }

    // Increases value of i-th element by ''delta''.
    public void increase(int i, long delta) {
        for (; i < (int)tree.length; i |= i + 1)
            tree[i] += delta;
    }

    /**
     * Returns sum of elements with indexes left..right, inclusive
     * (zero-based)
     */
    public long getsum(int left, int right) {
        return sum(right) - sum(left-1); //when left equals 0 the function hopefully returns 0
    }

    private long sum(int ind) {
        long sum = 0;
        while (ind>=0)
        {
            sum += tree[ind];
            ind &= ind + 1;
            ind--;
        }
        return sum;
    }
}
