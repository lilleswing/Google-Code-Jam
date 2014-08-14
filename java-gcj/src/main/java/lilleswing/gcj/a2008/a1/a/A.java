package lilleswing.gcj.a2008.a1.a;

import com.google.common.collect.Lists;
import lilleswing.gcj.util.Problem;

import java.util.List;
import java.util.Scanner;

public class A extends Problem<Case> {
    @Override
    public String solve(Case aCase) {
        return String.valueOf(aCase.minScalerProduct());
    }

    @Override
    public List<Case> parse(String data) {
        final Scanner sc = new Scanner(data);
        final List<Case> cases = Lists.newArrayList();
        long num_cases = sc.nextLong();
        for (int case_num = 0; case_num < num_cases; case_num++) {
            final int n = sc.nextInt();
            final List<Integer> v1 = Lists.newArrayList();
            final List<Integer> v2 = Lists.newArrayList();
            for(int i = 0; i < n; i++) {
                v1.add(sc.nextInt());
            }
            for(int i = 0; i < n; i++) {
                v2.add(sc.nextInt());
            }
            Case aCase = new Case(v1, v2);
            cases.add(aCase);
        }
        return cases;
    }

    @Override
    public void preCompute() {
    }

    public static void main(String[] args) {
        Problem a = new A();
        a.main(args[0]);
    }
}
