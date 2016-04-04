package lilleswing.gcj.a2014.b1.b;


import com.google.common.base.Function;
import com.google.common.collect.Lists;
import lilleswing.gcj.util.Problem;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.List;
import java.util.Scanner;

/**
 * Dumb Brute Force, only solves small solution
 */
public class B extends Problem<Case> {
    @Override
    public String solve(Case aCase) {
        int count = 0;
        for (int ta = 0; ta < aCase.getA(); ta++) {
            for (int tb = 0; tb < aCase.getB(); tb++) {
                int tc = ta & tb;
                if (tc < aCase.getK()) {
                    count++;
                }
            }
        }
        return String.valueOf(count);
    }

    @Override
    public List<Case> parse(String data) {
        final Scanner sc = new Scanner(data);
        final List<Case> cases = Lists.newArrayList();
        long num_cases = sc.nextLong();
        for (int case_num = 0; case_num < num_cases; case_num++) {
            Case aCase = new Case(sc.nextInt(), sc.nextInt(), sc.nextInt());
            cases.add(aCase);
        }
        return cases;
    }

    @Override
    public void preCompute() {
    }

    public static void main(String[] args) {
        Problem problem = new B();
        problem.main(args[0]);
    }
}

class Case {
    private int a, b, k;
    public Case(int a, int b, int k) {
        this.a = a;
        this.b = b;
        this.k = k;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getK() {
        return k;
    }
}
