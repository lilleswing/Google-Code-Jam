package lilleswing.gcj.a2008.r2.a;

import com.google.common.collect.Lists;
import lilleswing.gcj.util.Problem;

import java.util.List;
import java.util.Scanner;

public class A extends Problem<Case> {
    private static final String IMPOSSIBLE = "IMPOSSIBLE";
    @Override
    public String solve(Case aCase) {
        aCase.dp();
        final long flips = aCase.flipsToGoal();
        if (flips == Node.SOFT_MAX) {
            return IMPOSSIBLE;
        }
        return String.valueOf(flips);
    }

    @Override
    public List<Case> parse(String data) {
        final Scanner sc = new Scanner(data);
        final List<Case> cases = Lists.newArrayList();
        long num_cases = sc.nextLong();
        for (int case_num = 0; case_num < num_cases; case_num++) {
            final int m = sc.nextInt();
            final int v = sc.nextInt();
            final List<Node> nodes = Lists.newArrayList();
            nodes.add(null);
            final int numInteriorNodes = (m-1)/2;
            for (int i = 0; i < numInteriorNodes; i++) {
                int g = sc.nextInt();
                int c = sc.nextInt();
                Node n = new Node(g, c);
                nodes.add(n);
            }
            final int numExteriorNodes = (m+1)/2;
            for (int i = 0; i < numExteriorNodes; i++) {
                Node n = new Node(sc.nextInt());
                nodes.add(n);
            }
            final Case aCase = new Case(v, nodes);
            cases.add(aCase);
        }
        return cases;
    }

    @Override
    public void preCompute() {
    }

    public static void main(String[] args) {
        A a = new A();
        a.main(args[0]);
    }
}
