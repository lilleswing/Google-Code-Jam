package lilleswing.gcj.a2008.c1.c;

import com.google.common.collect.Lists;
import lilleswing.gcj.util.Problem;
import lilleswing.gcj.util.datastructures.fenwicktree.ModFenwickTree;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class C extends Problem<Case> {
    final long MOD = 1000000007;

    @Override
    public String solve(Case aCase) {
        final ModFenwickTree fenwickTree = new ModFenwickTree(zeros(aCase.getLimits().size()), MOD);
        for (final Integer limit: aCase.getLimits()) {
            long prev = fenwickTree.getsum(0, limit-1);
            fenwickTree.increase(limit, prev+1); // One is the unity List
        }
        final long numSequences = fenwickTree.getsum(0, Collections.max(aCase.getLimits()));
        return String.valueOf(numSequences);
    }

    public List<Long> zeros(final int length) {
        final List<Long> list = Lists.newArrayListWithCapacity(length);
        for(int i = 0; i < length; i++) {
            list.add(0L);
        }
        return list;
    }

    @Override
    public List<Case> parse(String data) {
        final Scanner sc = new Scanner(data);
        final List<Case> cases = Lists.newArrayList();
        long num_cases = sc.nextLong();
        for (int case_num = 0; case_num < num_cases; case_num++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int X = sc.nextInt();
            int Y = sc.nextInt();
            int Z = sc.nextInt();
            final List<Long> A = Lists.newArrayList();
            for(int i = 0; i < m; i++) {
                A.add(sc.nextLong());
            }
            final Case aCase = new Case(n,m,X,Y,Z,A);
            cases.add(aCase);
        }
        return cases;
    }

    @Override
    public void preCompute() {
    }

    public static void main(String[] args) {
        C c = new C();
        c.main(args[0]);
    }
}
