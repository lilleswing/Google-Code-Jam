package lilleswing.gcj.a2008.b1.croptriangles;

import com.google.common.collect.Lists;

import java.util.List;

class Case {
    public List<Point> trees;

    public Case(long n, long A, long B, long C, long D, long Xo, long Yo, long M) {
        trees = Lists.newArrayList();
        long x = Xo;
        long y = Yo;
        trees.add(new Point(x, y));
        for (int i = 0; i < n - 1; i++) {
            x = (A * x + B) % M;
            y = (C * y + D) % M;
            trees.add(new Point(x, y));
        }
    }

}

class Point {
    public long x;
    public long y;

    public Point(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "Point(" + x + "," + y + ")";
    }
}
