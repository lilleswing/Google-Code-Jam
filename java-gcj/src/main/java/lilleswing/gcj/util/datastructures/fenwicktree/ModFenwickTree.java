package lilleswing.gcj.util.datastructures.fenwicktree;


import java.util.List;

/**
 * In this implementation, the tree is represented by a vector<int>.
 * Elements are numbered by 0, 1, ..., n-1.
 * tree[i] is sum of elements with indexes i&(i+1)..i, inclusive.
 * (Note: this is a bit different from what is proposed in Fenwick's article.
 * To see why it makes sense, think about the trailing 1's in binary
 * representation of indexes.)
 */
public class ModFenwickTree {
    private long[] tree;
    private long mod;

    public ModFenwickTree(List<Long> inTree, final long mod) {
        this.tree = new long[inTree.size()];
        this.mod = mod;

        for (int i = 0; i < inTree.size(); i++)
            increase(i, inTree.get(i));
    }

    // Increases value of i-th element by ''delta''.
    public void increase(int i, long delta) {
        for (; i < (int) tree.length; i |= i + 1)
            tree[i] = modAdd(tree[i], delta);
    }

    /**
     * Returns sum of elements with indexes left..right, inclusive
     * (zero-based)
     */
    public long getsum(int left, int right) {
        return sum(right) - sum(left - 1); //when left equals 0 the function hopefully returns 0
    }

    private long sum(int ind) {
        long sum = 0;
        while (ind >= 0) {
            sum = modAdd(sum, tree[ind]);
            ind &= ind + 1;
            ind--;
        }
        return sum;
    }

    private long modAdd(final long a, final long b) {
        return (a + b) % this.mod;
    }
}
