package lilleswing.gcj.a2008.c1.c;

import com.google.common.collect.Lists;
import lilleswing.gcj.util.Problem;

import java.util.List;
import java.util.Scanner;

public class C extends Problem<Case> {
    @Override
    public String solve(Case aCase) {
        return String.valueOf(aCase.getSequences());
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
