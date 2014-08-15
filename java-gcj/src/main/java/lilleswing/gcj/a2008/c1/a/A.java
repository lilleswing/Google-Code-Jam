package lilleswing.gcj.a2008.c1.a;

import com.google.common.collect.Lists;
import lilleswing.gcj.util.Problem;

import java.util.List;
import java.util.Scanner;

public class A extends Problem<Case>{
    @Override
    public String solve(Case aCase) {
        return String.valueOf(aCase.minStrokes());
    }

    @Override
    public List<Case> parse(String data) {
        final Scanner sc = new Scanner(data);
        final List<Case> cases = Lists.newArrayList();
        long num_cases = sc.nextLong();
        for (int case_num = 0; case_num < num_cases; case_num++) {
            final int p = sc.nextInt();
            final int k = sc.nextInt();
            final int l = sc.nextInt();
            final List<Integer> frequencies = Lists.newArrayList();
            for(int i = 0; i < l; i++) {
                frequencies.add(sc.nextInt());
            }
            final Case aCase = new Case(p,k,l,frequencies);
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
