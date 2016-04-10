package lilleswing.gcj.a2016.q.d;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.common.collect.Lists;
import lilleswing.gcj.util.Problem;

public class MyProblem extends Problem<Case> {


    @Override
    public String solve(Case aCase) {
        if (aCase.s * aCase.c < aCase.k) {
            return "IMPOSSIBLE";
        }
        List tiles = new ArrayList<>();
        for (int i = 1; i < aCase.k+1; i += aCase.c) {
            long p = 1;
            for (int j = 0; j < aCase.c; j++) {
                p = (p -1) * aCase.k + Math.min(i + j, aCase.k);
                if (p < 0) {
                    throw new RuntimeException("Overflow");
                }
            }
            tiles.add(p);
        }
        return stringList(tiles);
    }

    private String stringList(List<Number> tiles) {
        final StringBuilder sb = new StringBuilder();
        for(final Number tile: tiles) {
            sb.append(" " + tile.longValue());
        }
        return sb.toString().trim();
    }

    @Override
    public List<Case> parse(String data) {
        Scanner sc = new Scanner(data);
        int t = sc.nextInt();
        List<Case> cases = Lists.newArrayList();
        for (int i = 0; i < t; i++) {
            int k = sc.nextInt();
            int c = sc.nextInt();
            int s = sc.nextInt();
            cases.add(new Case(k, c, s));
        }
        return cases;
    }

    @Override
    public void preCompute() {
    }

    public static void main(String[] args) {
        Problem problem = new MyProblem();
        problem.main(args[0]);
    }
}

class Case {
    int k;
    int c;
    int s;

    public Case(int k, int c, int s) {
        this.k = k;
        this.c = c;
        this.s = s;
    }
}
